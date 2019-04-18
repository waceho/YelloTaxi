package com.amanciodrp.yellotaxi

import android.app.Application
import com.amanciodrp.yellotaxi.di.ApplicationComponent
import com.amanciodrp.yellotaxi.di.ApplicationModule
import com.amanciodrp.yellotaxi.di.DaggerApplicationComponent


class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)

}
