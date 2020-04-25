package com.example.common

import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment(layoutID:Int) : Fragment(layoutID) {

    var uiCommunicator: UiCommunicator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is UiCommunicator) {
            uiCommunicator = context
        } else {
            throw Exception("you must implement uiCommunicator listener ")
        }
    }

    override fun onDetach() {
        super.onDetach()
        uiCommunicator = null
    }
}