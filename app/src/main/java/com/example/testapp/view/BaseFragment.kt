package com.example.testapp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.*
import com.example.testapp.R
import com.example.testapp.utils.extensions.parentActivity
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Router

abstract class BaseFragment(private val layoutId: Int) : Fragment() {

    val router: Router by inject()

    open val isDrawerEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    abstract val toolbarDescription: ToolbarDescription

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (!isDrawerEnabled && toolbarDescription.toolbarIcon == ToolbarIcon.ARROW && item?.itemId == android.R.id.home) {
            router.exit()
        }
        return super.onOptionsItemSelected(item)
    }

    private var menuRes: Int? = null

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menuRes?.let { inflater?.inflate(it, menu) }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupToobar(toolbarDescription: ToolbarDescription) {
        if (parentActivity is MainActivity) {
            val activity = parentActivity as MainActivity
            if (isDrawerEnabled) activity.enableDrawer() else activity.disableDrawer()
        }
        if (!toolbarDescription.hasToolbar) return
        val toolbar = view!!.findViewById<Toolbar>(R.id.toolbar)
        toolbarDescription.titleRes?.let { toolbar.setTitle(it) }
                ?: toolbarDescription.titleStr?.let { toolbar.setTitle(it) }
        menuRes = toolbarDescription.menuRes
        parentActivity?.apply {
            setSupportActionBar(toolbar)
            when (toolbarDescription.toolbarIcon) {
                ToolbarIcon.ARROW -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                }
                ToolbarIcon.HAMBURGER -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                }
            }
            if (!isDrawerEnabled) {
                toolbar.setNavigationOnClickListener { router.exit() }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToobar(toolbarDescription)
        listenToVm()
        listenToUi()
    }

    abstract fun listenToVm()

    abstract fun listenToUi()

}

enum class ToolbarIcon {
    NONE,
    ARROW,
    HAMBURGER,
    CROSS
}

data class ToolbarDescription(
        val hasToolbar: Boolean,
        val toolbarIcon: ToolbarIcon = ToolbarIcon.NONE,
        val titleRes: Int? = null,
        val menuRes: Int? = null,
        val titleStr: String? = null
)