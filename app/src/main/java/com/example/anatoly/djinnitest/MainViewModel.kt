package com.example.anatoly.djinnitest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.example.anatoly.djinnitest.paging.NetworkState

/**
 * Date: 21.07.2018
 * Time: 15:27
 *
 * @author anatoly
 */
class MainViewModel(val pagedList: PagedList<Long>, val stateLiveData: LiveData<NetworkState?>) : ViewModel()