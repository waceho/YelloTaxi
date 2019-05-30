package com.amanciodrp.yellotaxi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanciodrp.yellotaxi.repository.CustomerInfoRepositoryImpl
import javax.inject.Inject

class CustomerInfoViewModel
@Inject
constructor(private val getCustomerInfoRepository: CustomerInfoRepositoryImpl) : ViewModel() {

    private var customerInfo: MutableLiveData<Map<String, Any>>? = null

    /**
     * get customer info
     * @param customerId String
     * @return MutableLiveData<Map<String, Any>>?
     */
    fun getCustomerInfo(customerId: String): MutableLiveData<Map<String, Any>>? {
        return if (null != customerInfo) customerInfo else getCustomerInfoRepository.getCustomerInfo(customerId)
    }

}