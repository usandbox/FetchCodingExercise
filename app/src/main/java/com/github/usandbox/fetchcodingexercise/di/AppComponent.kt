package com.github.usandbox.fetchcodingexercise.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun viewModelFactory(): ViewModelFactory
}
