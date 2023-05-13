package com.example.markets.baseUtils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.markets.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_sms_location_permission_rationale.*

class SMSLocationPermissionRationaleBottomsheet(
    val showSMSRationale: Boolean,
    val showLocationRationale: Boolean,
    val iBottomsheetOptionsClickedListener: IBottomsheetOptionsClickedListener
): BottomSheetDialogFragment() {

    interface IBottomsheetOptionsClickedListener {
        fun onAllowButtonClicked()
        fun onDoItLaterClicked()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_sms_location_permission_rationale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(showSMSRationale){
            cl_sms.visibility = View.VISIBLE
        }
        if(showLocationRationale){
            cl_location.visibility = View.VISIBLE
        }

        tv_allow.setOnClickListener {
            dismiss()
            iBottomsheetOptionsClickedListener.onAllowButtonClicked()
        }

        tv_do_it_later.setOnClickListener {
            dismiss()
            iBottomsheetOptionsClickedListener.onDoItLaterClicked()
        }
    }

    companion object {
        fun newInstance(
            showSMSRationale: Boolean,
            showLocationRationale: Boolean,
            iBottomsheetOptionsClickedListener: IBottomsheetOptionsClickedListener
        ): SMSLocationPermissionRationaleBottomsheet {
            return SMSLocationPermissionRationaleBottomsheet(
                showSMSRationale,
                showLocationRationale,
                iBottomsheetOptionsClickedListener
            )
        }
    }
}