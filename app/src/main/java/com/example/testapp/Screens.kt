package com.example.testapp

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.example.testapp.view.MainActivity
import com.example.testapp.view.camera.CameraFragment
import com.example.testapp.view.dashboard.DashboardFragment
import com.example.testapp.view.dialog.AddNewProductDialog
import com.example.testapp.view.purchasesarchive.PurchasesArchiveFragment
import com.example.testapp.view.shoppinglist.ShoppingListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    abstract class BaseScreen protected constructor() : SupportAppScreen() {
        init {
            screenKey = javaClass.simpleName
        }
    }

    class Auth : BaseScreen() {
        override fun getActivityIntent(context: Context?): Intent {
            return Intent(context!!, MainActivity::class.java)
        }
    }

    class Dashboard : BaseScreen() {
        override fun getFragment(): Fragment = DashboardFragment.newInstance()
    }

    class Camera : BaseScreen() {
        override fun getFragment(): Fragment = CameraFragment.newInstance()
    }

    class PurchasesArchive : BaseScreen() {
        override fun getFragment(): Fragment = PurchasesArchiveFragment.newInstance()
    }

    class ShoppingList(private val date: String) : BaseScreen() {
        override fun getFragment(): Fragment = ShoppingListFragment.newInstance(date)
    }
}