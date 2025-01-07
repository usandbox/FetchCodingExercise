package com.github.usandbox.fetchcodingexercise

import android.app.Application
import com.github.usandbox.fetchcodingexercise.di.AppComponent
import com.github.usandbox.fetchcodingexercise.di.DaggerAppComponent

class FetchApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}
