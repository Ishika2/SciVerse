package com.example.sciverse

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.view.animation.Animation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.sciverse.CustomAuthentication.CustomSigninActivity

//import com.bumptech.glide.Glide;

class SplashScreen : AppCompatActivity() {
//    private lateinit var iconAnimation: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val content = findViewById<View>(android.R.id.content)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            content.viewTreeObserver.addOnDrawListener { false }
        }
        callNextScreen()
    }
    private fun callNextScreen(){
        // on below line we are calling
        // handler to run a task
        // for specific time interval
        Handler().postDelayed({
            // on below line we are
            // creating a new intent
            val i = Intent(
                this,
                CustomSigninActivity::class.java
            )
            // on below line we are
            // starting a new activity.
            startActivity(i)

            // on the below line we are finishing
            // our current activity.
            finish()
        }, 1000)
    }
}