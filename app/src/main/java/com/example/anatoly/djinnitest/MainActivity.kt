package com.example.anatoly.djinnitest

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import com.example.anatoly.djinnitest.core.dagger.AppComponent
import com.example.anatoly.djinnitest.paging.PrimeNumbersAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = AppComponent.instance.mainSubComponent().viewModelFactory()

        val viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

        val adapter = PrimeNumbersAdapter {
            //do nothing
        }.apply {
            submitList(viewModel.pagedList)
        }

        viewModel.stateLiveData.observe(this, Observer {
            adapter.setNetworkState(it)
        })

        recycler.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}
