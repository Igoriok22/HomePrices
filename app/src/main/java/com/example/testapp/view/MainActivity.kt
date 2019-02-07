package com.example.testapp.view

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.testapp.R
import com.example.testapp.Screens
import com.example.testapp.viewModel.main.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router.newRootScreen(Screens.Dashboard())
        listenToMenu()
    }

    private fun listenToMenu() {
       nav_dashboard.onItemClick { vm.goToDashboard() }
       nav_camera.onItemClick { vm.goToCamera() }
        nav_shopping_list.onItemClick { vm.goToShoppingList() }
    }

    fun enableDrawer(){
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    fun disableDrawer(){
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        super.setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun View.onItemClick(listener: (View) -> Unit) = setOnClickListener {
        drawer_layout.closeDrawers()
        listener(it)
    }
}
