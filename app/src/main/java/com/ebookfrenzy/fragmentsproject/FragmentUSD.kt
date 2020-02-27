package com.ebookfrenzy.fragmentsproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.lang.ClassCastException
import kotlinx.android.synthetic.main.fragment_usd.*
import java.text.NumberFormat
import java.util.*

class FragmentUSD : Fragment() {

    var activityCallback: FragmentUSDListener? = null
    var editText: EditText? = null

    //Sends data to activity to process before sending to other fragments
    interface FragmentUSDListener {
        fun onInputUSDSent(input: Float)
    }

    //failsafe in case listener was not implemented
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallback = context as FragmentUSDListener
        } catch (e: ClassCastException){
            throw ClassCastException(context.toString() + "must implement FragmentUSDListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        activityCallback = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_usd, container, false)
        editText = view.findViewById(R.id.edit_text)

        val button:Button = view.findViewById(R.id.button_convert)

        button.setOnClickListener { v: View -> buttonClicked(v) }

        return view
    }

    //updates edittext on information retrieval
    fun updateEditText(newFloat: Float) {
        val formatter:NumberFormat = NumberFormat.getCurrencyInstance()
        formatter.maximumFractionDigits = 2
        formatter.currency = Currency.getInstance("USD")
        editText?.setText(formatter.format(newFloat).toString())
    }

    private fun buttonClicked(view:View){
        activityCallback?.onInputUSDSent(edit_text.text.toString().removePrefix("$").replace(",", "").toFloat())

    }

}