package com.bove.martin.popcorn.Commons

import android.app.Application

/**
 * Created by Martín Bove on 07-Aug-20.
 * E-mail: mbove77@gmail.com
 */
class MyApp: Application() {
    companion object {
        lateinit var instance:MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}