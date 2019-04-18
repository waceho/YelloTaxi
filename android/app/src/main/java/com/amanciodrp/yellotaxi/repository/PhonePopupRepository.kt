package com.amanciodrp.yellotaxi.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData

interface PhonePopupRepository {

    fun getPhoneCodeNumber(): MutableLiveData<String>

    fun getCountryCodeNumber(): MutableLiveData<String>
}