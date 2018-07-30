package com.example.anatoly.djinnitest

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.example.anatoly.djinnigenerated.PrimeGeneratorDjinni


/**
 * Date: 24.07.2018
 * Time: 10:40
 *
 * @author anatoly
 */
class PrimeNumbersService : IntentService("PrimeNumbersService") {
    private val generator: PrimeGeneratorDjinni
    private var isRun = false

    init {
        System.loadLibrary("prime")

        generator = PrimeGeneratorDjinni.create()
    }

    override fun onHandleIntent(intent: Intent?) {
        if (intent == null || isRun) {
            return
        }

        isRun = true

        val amount = ServiceArgs.deserializeFromIntent(intent).amount

        val result = generatePrimeNumbers(amount)

        MainActivity.BroadcastArgs(result).sendBroadcast(this)

        isRun = false
    }

    private fun generatePrimeNumbers(amount: Int): List<Int> {
        return generator.generatePrime(amount)
    }

    data class ServiceArgs(val amount: Int) {
        fun start(context: Context) {
            context.apply {
                startService(Intent(this, PrimeNumbersService::class.java).apply {
                    putExtra(AMOUNT_KEY, amount)
                })
            }
        }

        companion object {
            private const val AMOUNT_KEY = "amount:key"

            fun deserializeFromIntent(intent: Intent): ServiceArgs {
                return ServiceArgs(intent.getIntExtra(AMOUNT_KEY, 0))
            }
        }
    }
}