package com.amanciodrp.yellotaxi.splashscreen;

import java.lang.System;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0014J\b\u0010\b\u001a\u00020\u0004H\u0002\u00a8\u0006\t"}, d2 = {"Lcom/amanciodrp/yellotaxi/splashscreen/FullscreenActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "redirect", "app_debug"})
public final class FullscreenActivity extends androidx.appcompat.app.AppCompatActivity {
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    /**
     * show Taxi logo with animation
     *
     *   private fun showTaxiAnimation() {
     *   Handler().postDelayed({
     *   //  right_toLeftAnimation(findViewById(R.id.logo_taxi))
     *   right_toLeftAnimation(findViewById(R.id.logo_text))
     *   }, 2000)
     *   }
     */
    private final void redirect() {
    }
    
    public FullscreenActivity() {
        super();
    }
}