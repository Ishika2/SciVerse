package com.example.sciverse

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.view.animation.Animation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
//import com.bumptech.glide.Glide;

class SplashScreen : AppCompatActivity() {
//    private lateinit var iconAnimation: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

//        iconAnimation = findViewById(R.id.gif_image)
//        Glide.with(this).load(R.drawable.android).into(imageView)
//        val iconImage = findViewById<ImageView>(R.id.icon_image).apply {
//            setBackgroundResource(R.drawable.icon_animation)
//            iconAnimation = background as AnimationDrawable
//        }
//
//        iconImage.setOnClickListener({iconAnimation.start()})

    }
}