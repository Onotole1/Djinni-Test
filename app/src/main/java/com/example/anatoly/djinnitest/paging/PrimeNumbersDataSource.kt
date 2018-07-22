package com.example.anatoly.djinnitest.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PositionalDataSource
import com.example.anatoly.djinnigenerated.PrimeGeneratorDjinni

/**
 * Date: 21.07.2018
 * Time: 23:11
 *
 * @author anatoly
 */
class PrimeNumbersDataSource(private val mPrimeGenerator: PrimeGeneratorDjinni,
                             private val mStateLiveData: MutableLiveData<NetworkState?>): PositionalDataSource<Long>() {
    var result: List<Long>? = null
    var amount: Long = 0

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Long>) {
        mStateLiveData.postValue(NetworkState.LOADING)

        val prime = mPrimeGenerator.generatePrime(amount + params.loadSize.toLong())

        amount += params.loadSize

        val removed = prime.toMutableList().apply {
            result?.let {
                removeAll(it)
            }
        }


        callback.onResult(removed)

        result = prime

        mStateLiveData.postValue(NetworkState.LOADED)
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Long>) {
        mStateLiveData.postValue(NetworkState.LOADING)

        val prime = mPrimeGenerator.generatePrime(params.pageSize.toLong()).apply {
            result = this
        }

        amount += params.pageSize

        callback.onResult(prime, 0)

        mStateLiveData.postValue(NetworkState.LOADED)
    }
}