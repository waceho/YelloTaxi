package com.amanciodrp.yellotaxi.splashscreen
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.amanciodrp.yellotaxi.R
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.amanciodrp.yellotaxi.onboarding.OnBoardingActivity


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

        }

    override fun onResume() {
        super.onResume()

        val show = AnimationUtils.loadAnimation(this, R.anim.slide_left_anim)

        findViewById<View>(R.id.logo_taxi).startAnimation(show)
        findViewById<View>(R.id.logo_text).startAnimation(show)

        show.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
                findViewById<View>(R.id.logo_taxi).setVisibility(View.VISIBLE)
                findViewById<View>(R.id.logo_text).setVisibility(View.VISIBLE)
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                findViewById<View>(R.id.logo_taxi).clearAnimation()
            }
        })

        showTaxiAnimation()

    }

    private fun right_toLeftAnimation(onboadingImage: ImageView) {
        val animation_logo_taxi = AnimationUtils.loadAnimation(baseContext, R.anim.slide_right_to_left_anim)
        val animation_logo_text = AnimationUtils.loadAnimation(baseContext, R.anim.fade_out_anim)

        animation_logo_taxi.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
                onboadingImage.setVisibility(View.VISIBLE)
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {

                //onboadingImage.clearAnimation()
                left_toRightAnimation(findViewById<View>(R.id.taxi))
                findViewById<View>(R.id.logo_taxi).setVisibility(View.VISIBLE)

                // redirect
              //  redirect()

            }

        })

        onboadingImage.startAnimation(animation_logo_taxi)
        findViewById<View>(R.id.logo_text).startAnimation(animation_logo_text)
    }

    private fun left_toRightAnimation(view: View) {
        val animation_taxi = AnimationUtils.loadAnimation(baseContext, R.anim.slide_left_to_right__fast_anim)

        animation_taxi.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
                view.setVisibility(View.VISIBLE)
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
               // view.clearAnimation()

                // redirect
                  redirect()

            }

        })

        view.startAnimation(animation_taxi)
    }

    /**
     * show Taxi logo with animation
     */
    private fun showTaxiAnimation() {
        Handler().postDelayed({
            right_toLeftAnimation(findViewById(R.id.logo_taxi))
            right_toLeftAnimation(findViewById(R.id.logo_text))
        }, 2000)
    }

    private fun redirect() {
        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this, OnBoardingActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 3000)
    }
}
