package com.example.anatoly.djinnitest.core.dagger

import com.example.anatoly.djinnitest.dagger.MainSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component()
interface AppComponent {
    fun mainSubComponent(): MainSubComponent

    companion object {
        lateinit var instance: AppComponent
    }
}
