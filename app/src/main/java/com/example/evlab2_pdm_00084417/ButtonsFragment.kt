package com.example.evlab2_pdm_00084417


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class ButtonsFragment : Fragment() {

    lateinit var buttonsHelper: ButtonsHelper
    lateinit var btnPrev:Button


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        buttonsHelper = context as ButtonsHelper

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_buttons, container, false)
        btnPrev = view.findViewById(R.id.btn_prev)
        btnPrev.setOnClickListener {
            buttonsHelper.onPrevClickListener()
        }
        return view
    }


}
