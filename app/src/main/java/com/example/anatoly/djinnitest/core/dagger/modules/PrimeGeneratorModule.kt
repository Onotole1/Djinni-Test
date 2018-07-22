package com.example.anatoly.djinnitest.core.dagger.modules

import com.example.anatoly.djinnigenerated.PrimeGeneratorDjinni
import com.example.anatoly.djinnitest.dagger.MainSubComponent
import dagger.Module
import dagger.Provides

/**
 * Date: 21.07.2018
 * Time: 15:33
 *
 * @author anatoly
 */
@Module
class PrimeGeneratorModule {

    @Provides
    @MainSubComponent.MainScope
    fun providePrimeGenerator(): PrimeGeneratorDjinni = PrimeGeneratorDjinni.create()

    companion object {
        init {
            System.loadLibrary("prime")
        }
    }
}