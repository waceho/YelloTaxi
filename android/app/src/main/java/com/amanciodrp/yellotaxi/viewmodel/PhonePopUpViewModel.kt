package com.amanciodrp.yellotaxi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanciodrp.yellotaxi.repository.PhonePopUpRepositoryNumberImpl

class PhonePopUpViewModel constructor(private val getPhonePupRepository : PhonePopUpRepositoryNumberImpl): ViewModel() {

    lateinit var userPhoneNomber: MutableLiveData<String>

    // init phone number
    fun init() { userPhoneNomber = getPhonePupRepository.getPhoneCodeNumber() }

    // get phone number
    fun getPhoneNumber() : MutableLiveData<String>{
        if (null != userPhoneNomber.value)
            return userPhoneNomber
        else
            return getPhonePupRepository.getPhoneCodeNumber()
    }

}