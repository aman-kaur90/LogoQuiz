package com.logo.logoquiz.base

import android.app.Application

open class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        context=this
    }

    companion object{
        lateinit var context: Application
    }
}