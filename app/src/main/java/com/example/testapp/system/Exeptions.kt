package com.example.testapp.system

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message = "No connectivity exception"
}
