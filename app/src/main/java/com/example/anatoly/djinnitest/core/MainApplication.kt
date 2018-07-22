package com.example.anatoly.djinnitest.core

import android.app.Application
import com.example.anatoly.djinnitest.core.dagger.AppComponent
import com.example.anatoly.djinnitest.core.dagger.DaggerAppComponent

/**
 * Date: 22.07.2018
 * Time: 15:02
 *
 * @author anatoly
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppComponent.instance = DaggerAppComponent.builder()
                .build()
    }
}