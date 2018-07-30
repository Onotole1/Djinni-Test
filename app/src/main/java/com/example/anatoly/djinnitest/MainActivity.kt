package com.example.anatoly.djinnitest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val mAdapter = PrimeNumbersAdapter()

    private val mBroadcastReceiver = PrimeNumbersReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.apply {
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        if (savedInstanceState == null) {
            showLoadingState()

            PrimeNumbersService.ServiceArgs(AMOUNT).start(this@MainActivity)
        }
    }

    override fun onResume() {
        super.onResume()

        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,
                IntentFilter(BroadcastArgs.ACTION))
    }

    override fun onPause() {
        super.onPause()

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        mAdapter.items?.let {
            outState?.putIntegerArrayList(RESULT_KEY, ArrayList(it))
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.apply {
            val result = getIntegerArrayList(RESULT_KEY) as? List<Int>
            if (result != null) {
                showDataState(result)
            }
        }
    }

    private fun showLoadingState() {
        recycler.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun showDataState(values: List<Int>) {
        recycler.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

        mAdapter.items = values
    }

    inner class PrimeNumbersReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                BroadcastArgs.deserializeFromIntent(it)?.numbers?.let {
                    this@MainActivity.showDataState(it)
                }
            }
        }
    }

    data class BroadcastArgs(val numbers: List<Int>) {
        fun sendBroadcast(context: Context) {
            val intent = Intent().apply {
                putExtra(PRIME_NUMBERS_KEY, ArrayList(numbers))
                action = ACTION
            }

            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        }

        companion object {
            const val PRIME_NUMBERS_KEY = "primeNumbers:key"
            const val ACTION = "primeNumbers:action"

            fun deserializeFromIntent(intent: Intent): BroadcastArgs? {
                return (intent.getIntegerArrayListExtra(PRIME_NUMBERS_KEY) as? List<Int>)?.let {
                    BroadcastArgs(it)
                }
            }
        }
    }

    companion object {
        const val RESULT_KEY = "result:key"
        const val AMOUNT = 30000
    }
}
