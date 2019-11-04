package com.example.testapp.utils

import com.orhanobut.hawk.Hawk

class HawkStorage {
    inline fun <reified T> put(value: T, key: String? = null) {
        if (key == null)
            Hawk.put(T::class.java.canonicalName, value)
        else
            Hawk.put(key, value)
    }

    inline fun <reified T> get(key: String? = null, default: T? = null): T {
        if(default != null)
        {
            return if (key == null)
                Hawk.get<T>(T::class.java.canonicalName, default)
            else
                Hawk.get<T>(key, default)
        }
        else
        {
            return if (key == null)
                Hawk.get<T>(T::class.java.canonicalName)
            else
                Hawk.get<T>(key)
        }

    }

    inline fun <reified T> remove(key: String? = null): Boolean {
        return if (key == null)
            Hawk.delete(T::class.java.canonicalName)
        else
            Hawk.delete(key)
    }

    inline fun <reified T> exists(key: String? = null): Boolean {
        return if (key == null)
            Hawk.contains(T::class.java.canonicalName)
        else
            Hawk.contains(key)
    }

    fun dropStorage(): Boolean = Hawk.deleteAll()

    companion object {
        const val DATES = "dates"
    }
}