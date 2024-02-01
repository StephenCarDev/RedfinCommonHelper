package com.stephen.commonhelper.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setBinding()
        setContentView(binding.root)
    }

    protected abstract fun setBinding(): T

}