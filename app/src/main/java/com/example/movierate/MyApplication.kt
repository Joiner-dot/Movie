package com.example.movierate

import android.app.Application
import com.example.movierate.di.restModule
import com.example.movierate.di.useCasesModule
import com.example.movierate.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.*

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(restModule, useCasesModule, viewModelModule)
        }
    }
}