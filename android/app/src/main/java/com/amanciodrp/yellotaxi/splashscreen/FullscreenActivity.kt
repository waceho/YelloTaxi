package com.amanciodrp.yellotaxi.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SharedMemory
import android.view.View
import com.amanciodrp.yellotaxi.R
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.amanciodrp.yellotaxi.customeractivity.CustomerLoginActivity
import com.amanciodrp.yellotaxi.driverActivity.DriverLoginActivity
import com.amanciodrp.yellotaxi.model.DefaultUseSettings
import com.amanciodrp.yellotaxi.onboarding.OnBoardingActivity
import com.amanciodrp.yellotaxi.utils.SharedPrefsObject


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
    }

    override fun onResume() {
        super.onResume()

        val show = AnimationUtils.loadAnimation(this, R.anim.slide_left_anim)

        findViewById<View>(R.id.logo_text).startAnimation(show)

        show.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
                findViewById<View>(R.id.logo_text).setVisibility(View.VISIBLE)
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                redirect()
            }
        })

    }

    /**
     * show Taxi logo with animation

    private fun showTaxiAnimation() {
    Handler().postDelayed({
    //  right_toLeftAnimation(findViewById(R.id.logo_taxi))
    right_toLeftAnimation(findViewById(R.id.logo_text))
    }, 2000)
    }
     */

    private fun redirect() {
        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            val defaultValue: DefaultUseSettings? = SharedPrefsObject.getSavedObjectFromPreference(baseContext,
                    DefaultUseSettings::class.simpleName,
                    DefaultUseSettings::class.simpleName,
                    DefaultUseSettings::class.java)

            when {
                defaultValue?.mode.equals(getString(R.string.passager)) -> startActivity(Intent(this, CustomerLoginActivity::class.java))
                defaultValue?.mode.equals(getString(R.string.chauffeur)) -> startActivity(Intent(this, DriverLoginActivity::class.java))
                else -> startActivity(Intent(this, OnBoardingActivity::class.java))
            }
            finish()

        }, 1000)
    }
}
