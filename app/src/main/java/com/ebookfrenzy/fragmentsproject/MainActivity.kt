package com.ebookfrenzy.fragmentsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import java.text.NumberFormat

class MainActivity : FragmentActivity(), FragmentUSD.FragmentUSDListener,
    FragmentEuro.FragmentEuroListener,
    FragmentRuble.FragmentRubleListener, FragmentRupee.FragmentRupeeListener {

    private val fragmentUSD = FragmentUSD()
    private val fragmentEuro = FragmentEuro()
    private val fragmentRuble = FragmentRuble()
    private val fragmentRupee = FragmentRupee()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_usd, fragmentUSD)
            .replace(R.id.container_euro, fragmentEuro)
            .replace(R.id.container_rupee, fragmentRupee)
            .replace(R.id.container_ruble, fragmentRuble).commit()
    }

    override fun onInputUSDSent(input: Float) {
        val newEuro = input * 0.92
        val newRupee = input * 71.68
        val newRuble = input * 65.43
        fragmentEuro.updateEditText(newEuro.toFloat())
        fragmentRupee.updateEditText(newRupee.toFloat())
        fragmentRuble.updateEditText(newRuble.toFloat())
    }

    override fun onInputEuroSent(input: Float) {
        val newUSD = input * 1.09
        val newRupee = input * 77.91
        val newRuble = input * 71.19
        fragmentUSD.updateEditText(newUSD.toFloat())
        fragmentRupee.updateEditText(newRupee.toFloat())
        fragmentRuble.updateEditText(newRuble.toFloat())
    }

    override fun onInputRubleSent(input: Float) {
        val newUSD = input * 0.015
        val newEuro = input * 0.014
        val newRupee = input * 1.10
        fragmentUSD.updateEditText(newUSD.toFloat())
        fragmentEuro.updateEditText(newEuro.toFloat())
        fragmentRupee.updateEditText(newRupee.toFloat())

    }

    override fun onInputRupeeSent(input: Float) {
        val newUSD = input * 0.014
        val newEuro = input * 0.013
        val newRuble = input * 0.91
        fragmentUSD.updateEditText(newUSD.toFloat())
        fragmentEuro.updateEditText(newEuro.toFloat())
        fragmentRuble.updateEditText(newRuble.toFloat())
    }

}
