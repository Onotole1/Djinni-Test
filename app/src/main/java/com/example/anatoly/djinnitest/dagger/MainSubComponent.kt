package com.example.anatoly.djinnitest.dagger

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.paging.PagedList
import android.os.Handler
import android.os.Looper
import com.example.anatoly.djinnigenerated.PrimeGeneratorDjinni
import com.example.anatoly.djinnitest.MainViewModel
import com.example.anatoly.djinnitest.core.dagger.modules.PrimeGeneratorModule
import com.example.anatoly.djinnitest.paging.NetworkState
import com.example.anatoly.djinnitest.paging.PrimeNumbersDataSource
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Scope

/**
 * Date: 21.07.2018
 * Time: 15:36
 *
 * @author anatoly
 */
@MainSubComponent.MainScope
@Subcomponent(modules = [MainSubComponent.MainModule::class, PrimeGeneratorModule::class])
interface MainSubComponent {
    fun viewModelFactory(): ViewModelProvider.Factory

    @Module
    class MainModule {

        @MainScope
        @Provides
        fun provideMainExecutor(): Executor {
            return object : Executor {
                private val mHandler = Handler(Looper.getMainLooper())

                override fun execute(command: Runnable?) {
                    mHandler.post(command)
                }

            }
        }

        @MainScope
        @Provides
        fun provideStateLiveData() = MutableLiveData<NetworkState?>()

        @MainScope
        @Provides
        fun provideDataSource(primeGenerator: PrimeGeneratorDjinni, stateLiveData: MutableLiveData<NetworkState?>)
                = PrimeNumbersDataSource(primeGenerator, stateLiveData)

        @MainScope
        @Provides
        fun providePagedList(dataSource: PrimeNumbersDataSource, executor: Executor): PagedList<Long> {

            val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(100)
                    .setPrefetchDistance(5)
                    .build()


            return PagedList.Builder(dataSource, config)
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .setNotifyExecutor(executor)
                    .build()
        }

        @MainScope
        @Provides
        fun provideViewModel(pagedList: PagedList<Long>, stateLiveData: MutableLiveData<NetworkState?>): ViewModelProvider.Factory {
            return object :ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return MainViewModel(pagedList, stateLiveData) as T
                }
            }
        }
    }

    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class MainScope
}