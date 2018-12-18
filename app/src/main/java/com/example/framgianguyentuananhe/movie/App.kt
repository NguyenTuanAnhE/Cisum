package com.example.framgianguyentuananhe.movie

import android.app.Application
import com.example.framgianguyentuananhe.movie.di.component.AppComponent
import com.example.framgianguyentuananhe.movie.di.component.DaggerAppComponent
import com.example.framgianguyentuananhe.movie.di.module.AppModule

class App : Application() {

    companion object {
         var mAppComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()

        mAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
