package com.example.framgianguyentuananhe.movie

import android.app.Application
import com.example.framgianguyentuananhe.movie.di.appModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this,listOf(appModule))
    }
}
