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

class FragmentEuro : Fragment() {

    var activityCallback: FragmentEuroListener? = null
    var editText: EditText? = null

    //Allows for data to be sent to other fragments
    interface FragmentEuroListener {
        fun onInputEuroSent(input: Float)
    }

    //failsafe in case listener was not implemented
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallback = context as FragmentEuroListener
        } catch (e: ClassCastException){
            throw ClassCastException(context.toString() + "must implement FragmentEuroListener")
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
        val view = inflater.inflate(R.layout.fragment_euro, container, false)
        editText = view.findViewById(R.id.edit_text)

        val button:Button = view.findViewById(R.id.button_convert)

        button.setOnClickListener { v: View -> buttonClicked(v) }

        return view
    }

    //updates edittext in fragment
    fun updateEditText(newFloat: Float) {
        val formatter = NumberFormat.getCurrencyInstance()
        formatter.maximumFractionDigits = 2
        formatter.currency = Currency.getInstance("EUR")
        editText?.setText(formatter.format(newFloat).toString())
    }

    private fun buttonClicked(view:View){
        activityCallback?.onInputEuroSent(edit_text.text.toString().removePrefix("€").replace(",", "").toFloat())

    }

}