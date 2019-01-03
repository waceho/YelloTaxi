package com.amanciodrp.benintaxi.splashscreen
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.amanciodrp.benintaxi.R
import android.net.Uri
import android.widget.VideoView
import com.amanciodrp.benintaxi.onboarding.OnBoardingActivity

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {

        // Delayed removal of status and navigation bar
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_fullscreen)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
             val videoToPlay = "https://speed.animaker.com/images/video/JcnrNgdQUFSZlNZ.mp4"
          //  val video = Uri.parse(videoToPlay)
            val videoview = findViewById<View>(R.id.splash_video) as VideoView
            val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.splash_screen_video)
            videoview.setVideoURI(uri)
            videoview.setOnPreparedListener {
                videoview.requestFocus()
                videoview.start()
                it.isLooping = true
            }

            Handler().postDelayed(object : Runnable {
                override fun run() {
                    //Do something after 100ms
                    // Create the text message with a string
                    val intent = Intent(baseContext, OnBoardingActivity::class.java)
                    startActivity(intent)
                }
            }, 3000)

            // Upon interacting with UI controls, delay any scheduled hide()
            // operations to prevent the jarring behavior of controls going away
            // while interacting with the UI.
            //dummy_button.setOnTouchListener(mDelayHideTouchListener)
        }

}
