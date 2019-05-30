package com.amanciodrp.yellotaxi.repository

import androidx.lifecycle.MutableLiveData

interface CustomerInfoRepository {

    /**
     *
     * @param customerId String
     * @return MutableLiveData<Map<String, Any>>?
     */
    fun getCustomerInfo(customerId: String): MutableLiveData<Map<String, Any>>?
}