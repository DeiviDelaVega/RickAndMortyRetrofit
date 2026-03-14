package com.example.rickandmortyretrofit.ui

import android.animation.ValueAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.transition.Explode
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rickandmortyretrofit.R
import com.example.rickandmortyretrofit.databinding.ActivitySplashBinding
import com.example.rickandmortyretrofit.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var song: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        startButtonAnimation()
        backgroundSong()
        clickOnButton()
        setActivityAnimation()
    }

    fun startButtonAnimation() {

        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 1100
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE

            addUpdateListener {
                binding.startButton.alpha = it.animatedValue as Float
            }

            start()
        }

    }

    fun backgroundSong() {
        song = MediaPlayer.create(this, R.raw.theme_background)
        song.start()
        song.isLooping = true


    }

    fun clickOnButton() {

        binding.startButton.setOnClickListener {
            songStop()
            val options = ActivityOptions.makeSceneTransitionAnimation(this)
            startActivity(Intent(this, MainActivity::class.java), options.toBundle())
        }

    }

    fun songStop() {
        song.stop()
        finish()
    }

    fun setActivityAnimation() {
        val fadeOut = Explode().apply {
            duration = 4000
        }

        window.exitTransition = fadeOut
    }

}