package com.example.markets.baseUtils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

abstract class BaseActivity : AppCompatActivity() {

    abstract fun getViewModel(): BaseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkInternetConnection()
    }

    fun checkInternetConnection() {
        lifecycleScope.launch {
            getViewModel().getNetworkCall().collect {
                when (it) {
                    InternetCheckHelper.NetworkStatus.Available -> {
                        println("Internet is Available")
                    }
                    InternetCheckHelper.NetworkStatus.Unavailable -> {
                        println("Internet is Not Available")
                    }
                }

            }
        }
    }

    fun setUpToolBar(toolbar: Toolbar, toolBarTitle: String, textView: AppCompatTextView? = null) {
        toolbar.setNavigationOnClickListener { onBackPressed() }
        textView?.let {
            textView.text = toolBarTitle
        }
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    fun setupToolBarHeaderImage(appcompactActivity: AppCompatActivity) {
        // TODO LOAD TOOLBAR IMAGE INTO IMAGEVIEW
    }

    // For starting activities based on redirection key and value
    fun navidateTo(redirectionKey: String, redirectionValue: String, bundle: Bundle? = null) {
        // TODO NAVIGATE THROUGH ROUTER
    }


    // For starting activity directly by its class name
    fun<T> navigateTo(activity : Class<T>,bundle: Bundle? = null){
        var intent = Intent(this,activity)
        bundle?.let {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    // load fragment
    fun loadFragment(container : Int,fragment : Fragment,addToBackstack : Boolean = false){
        if(addToBackstack){
            supportFragmentManager?.beginTransaction()
                ?.add(container, fragment)?.commit()
        }else{
            supportFragmentManager?.beginTransaction()
                ?.replace(container, fragment)?.commit()
        }
    }

    // For navigation graph navigations
    fun navigateToFragment(){

    }
}