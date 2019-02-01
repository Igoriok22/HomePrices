package com.example.testapp.view

import android.support.v7.app.AppCompatActivity
import com.example.testapp.R
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Forward
import java.util.*

abstract class BaseActivity : AppCompatActivity(){
    protected val navigatorHolder: NavigatorHolder by inject()

    protected val router: Router by inject()

    protected val navigator = object : SupportAppNavigator(this,
            R.id.container
    ) {

        var lastNavigationEvent = Date().time

        override fun fragmentForward(command: Forward?) {
            if(Date().time - lastNavigationEvent > 500){
                lastNavigationEvent = Date().time
                super.fragmentForward(command)
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}