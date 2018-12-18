package com.example.framgianguyentuananhe.movie.di.component

import com.example.framgianguyentuananhe.movie.di.module.AppModule
import com.example.framgianguyentuananhe.movie.screen.home.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

}
