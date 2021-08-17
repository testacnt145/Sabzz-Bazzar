package com.sabzzbazzar

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(val layout:Int, val bindingFactory: (LayoutInflater) -> VB) : AppCompatActivity(layout) {

    var vB: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(null)
        vB = bindingFactory(layoutInflater)
        setContentView(vB?.root)
    }

}