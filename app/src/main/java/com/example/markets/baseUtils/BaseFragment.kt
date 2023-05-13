package com.example.markets.baseUtils

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    private lateinit var mActivity: AppCompatActivity
    protected lateinit var rootView: View
    private var position = 0
    private var isVisibleToUser = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as AppCompatActivity).let {
            this.mActivity = it
        }
    }
}