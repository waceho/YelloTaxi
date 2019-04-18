package com.amanciodrp.yellotaxi.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.amanciodrp.yellotaxi.utils.UtilityKit

class PhonePopUpRepositoryNumberImpl (private val mContext: Context) : PhonePopupRepository {

    override fun getCountryCodeNumber(): MutableLiveData<String> { return UtilityKit.getCountryZipCode(mContext) }

    override fun getPhoneCodeNumber() : MutableLiveData<String> { return UtilityKit.getPhoneNumber(mContext) }
}