package com.example.testapp.view.dashboard

import com.example.testapp.R
import com.example.testapp.view.BaseFragment
import com.example.testapp.view.ToolbarDescription
import com.example.testapp.view.ToolbarIcon
import com.example.testapp.viewModel.dashboard.DashboardViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment: BaseFragment(R.layout.dashboard_layout) {

    override val toolbarDescription: ToolbarDescription = ToolbarDescription(true, ToolbarIcon.HAMBURGER, R.string.dashboard_title, null)

    private val vm: DashboardViewModel by viewModel()

    override fun listenToVm() {

    }

    override fun listenToUi() {

    }

    companion object {
        fun newInstance() = DashboardFragment()
    }
}