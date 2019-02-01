package com.example.testapp.view.camera

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.SurfaceHolder
import com.example.testapp.R
import com.example.testapp.view.BaseFragment
import com.example.testapp.view.ToolbarDescription
import com.example.testapp.view.ToolbarIcon
import com.example.testapp.viewModel.camera.CameraViewModel
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import kotlinx.android.synthetic.main.camera_layout.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.IOException
import java.util.regex.Pattern

class CameraFragment : BaseFragment(R.layout.camera_layout) {

    override val toolbarDescription: ToolbarDescription = ToolbarDescription(true, ToolbarIcon.HAMBURGER, R.string.camera_title, null)

    private val vm: CameraViewModel by viewModel()

    private var pattern = Pattern.compile("(\\d+(?:\\.\\d+)?)")

    private lateinit var cameraSource: CameraSource

    override fun listenToVm() {
        initCameraSource()
    }

    override fun listenToUi() {

    }

    private fun initCameraSource(){

         val textRecognizer = TextRecognizer.Builder(context).build()

        cameraSource = CameraSource.Builder(context, textRecognizer)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1280, 1024)
                .setAutoFocusEnabled(true)
                .setRequestedFps(2.0f)
                .build()

        if (!textRecognizer.isOperational) {
            Log.w(TAG, "Detector dependencies not loaded yet")
        } else {
            /**
             * Add call back to SurfaceView and check if camera permission is granted.
             * If permission is granted we can start our cameraSource and pass it to surfaceView
             */
            surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceCreated(holder: SurfaceHolder) {
                    try {

                        if (ActivityCompat.checkSelfPermission(context!!,
                                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(activity!!,
                                    arrayOf(Manifest.permission.CAMERA),
                                    requestPermissionID)
                            return
                        }
                        cameraSource.start(surfaceView.holder)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }

                override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

                override fun surfaceDestroyed(holder: SurfaceHolder) {
                    cameraSource.stop()
                }
            })

            //Set the TextRecognizer's Processor.
            textRecognizer.setProcessor(object : Detector.Processor<TextBlock> {
                override fun release() {}

                /**
                 * Detect all the text from camera using TextBlock and the values into a stringBuilder
                 * which will then be set to the textView.
                 */
                override fun receiveDetections(detections: Detector.Detections<TextBlock>) {
                    val items = detections.detectedItems
                    if (items.size() != 0) {

                        textView.post {
                            val stringBuilder = StringBuilder()
                            val item = items.valueAt(0)
                            stringBuilder.append(item.value)
                            textView.text = getDigitalFromString(stringBuilder.toString())
                        }
                    }
                }
            })

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode != requestPermissionID) {
            Log.d(TAG, "Got unexpected permission result: $requestCode")
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            try {
                if (ActivityCompat.checkSelfPermission(context!!, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return
                }
                cameraSource.start(surfaceView.getHolder())
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    private fun getDigitalFromString(text: String): String {
        val matcher = pattern.matcher(text)
        return if (matcher.find()) {
            matcher.group(1)
        } else "Неможливо визначити"
    }

    companion object {
        fun newInstance() = CameraFragment()
        const val TAG = "CameraFragment"
        const val requestPermissionID = 101
    }
}