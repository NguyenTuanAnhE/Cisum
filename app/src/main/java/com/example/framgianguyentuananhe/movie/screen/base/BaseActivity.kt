package com.example.framgianguyentuananhe.movie.screen.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar

abstract class BaseActivity : AppCompatActivity() {

    var mProgressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayout())
        mProgressBar = ProgressBar(this)
        initView()
        initComponents()
    }

    abstract fun getContentLayout(): Int

    abstract fun initView()

    abstract fun initComponents()
}
