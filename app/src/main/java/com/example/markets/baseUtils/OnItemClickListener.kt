package com.example.markets.baseUtils

import android.view.View

class OnItemClickListener(
    private val position: Int,
    private val onItemClickCallback: OnItemClickCallback
) :
    View.OnClickListener {
    override fun onClick(v: View?) {
        onItemClickCallback.onItemClicked(v, position)
    }

    interface OnItemClickCallback {
        fun onItemClicked(view: View?, position: Int)
    }
}