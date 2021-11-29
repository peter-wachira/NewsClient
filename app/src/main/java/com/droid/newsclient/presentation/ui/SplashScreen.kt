package com.droid.newsclient.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.droid.newsapiclient.R
import com.droid.newsapiclient.databinding.ActivitySplashScreenBinding
import android.content.Intent
import com.droid.newsclient.data.util.extensions.navigateTo


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
//        finish()
    }

}