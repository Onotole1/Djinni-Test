package com.example.anatoly.djinnitest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.anatoly.djinnigenerated.PrimeGeneratorDjinni
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val primes = PrimeGeneratorDjinni.create().generatePrime(100)

        Log.d("Primes:", primes.joinToString())
    }

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("prime")
        }
    }
}
