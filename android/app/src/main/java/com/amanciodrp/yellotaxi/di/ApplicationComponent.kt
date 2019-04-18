package com.amanciodrp.yellotaxi.di


import com.amanciodrp.yellotaxi.AndroidApplication
import dagger.Component
import com.amanciodrp.yellotaxi.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(application: AndroidApplication)

}
