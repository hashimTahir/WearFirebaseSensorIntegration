package com.hashim.myapplication

import android.app.Application
import timber.log.Timber

class BaseApplication : Application() {
    val hTag ="HashimTimberTag"

    override fun onCreate() {
        super.onCreate()
        hInitTimber()
    }

    private fun hInitTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    super.log(priority, String.format(hTag, tag), message, t)
                }
            })
        }
    }
}