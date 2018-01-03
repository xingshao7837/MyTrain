package com.example.administrator.mytrain.kotlin

import android.os.Bundle
import com.example.administrator.mytrain.BaseActivity
import com.example.administrator.mytrain.R
import kotlinx.android.synthetic.main.activity_kotlin_main.*

class KotlinMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.activity_kotlin_main)
        text.text="我的第一个kotlin"
    }
}
