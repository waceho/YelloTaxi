package com.amanciodrp.yellotaxi.di

import android.content.Context
import com.amanciodrp.yellotaxi.AndroidApplication
import com.amanciodrp.yellotaxi.repository.CustomerInfoRepository
import com.amanciodrp.yellotaxi.repository.CustomerInfoRepositoryImpl
import com.amanciodrp.yellotaxi.repository.PhonePopUpRepositoryNumberImpl
import com.amanciodrp.yellotaxi.repository.PhonePopupRepository
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providePopUpPhoneNumber(phoneData: PhonePopUpRepositoryNumberImpl): PhonePopupRepository = phoneData

    @Provides
    @Singleton
    fun providecustomerInfo(customerInfoData: CustomerInfoRepositoryImpl): CustomerInfoRepository = customerInfoData

}
