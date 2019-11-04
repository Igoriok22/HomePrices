package com.example.testapp.view.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapp.R
import com.example.testapp.domain.model.Product
import kotlinx.android.synthetic.main.dialog_view.*

class AddNewProductDialog : DialogFragment() {

    private var listener: DialogClickListener? = null

    interface DialogClickListener {
        fun onAcceptClick(product: Product)
    }

    fun setOnClickListener(listener: DialogClickListener) {
        this.listener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val customView = inflater.inflate(R.layout.dialog_view, container, false)
        return customView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accept_bt.setOnClickListener {
            dismiss()
            listener?.onAcceptClick(Product(name_field.text.toString(), number_field.text.toString().toDouble(), price_field.text.toString().toDouble()))
        }
    }

    companion object {
        fun newInstance() = AddNewProductDialog()
    }
}