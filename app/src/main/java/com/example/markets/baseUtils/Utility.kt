package com.example.markets.baseUtils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import android.widget.LinearLayout
import com.example.markets.R

fun showCommonProgressDialog(context: Context): Dialog {
    val dialog = Dialog(context)
    val view = LayoutInflater.from(context)
        .inflate(R.layout.item_progress_view, LinearLayout(context), false)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.window?.setBackgroundDrawableResource(R.color.transparent_background_color);
    dialog.setContentView(view)
    dialog.setCancelable(false)
    return dialog
}