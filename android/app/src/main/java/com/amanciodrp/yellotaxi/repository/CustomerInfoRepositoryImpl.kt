package com.amanciodrp.yellotaxi.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CustomerInfoRepositoryImpl : CustomerInfoRepository {

    /**
     *
     * @param customerId String
     * @return MutableLiveData<Map<String, Any>>?
     */
    override fun getCustomerInfo(customerId: String): MutableLiveData<Map<String, Any>>? {
        val mCustomerDatabase = FirebaseDatabase.getInstance().reference.child("Users").child("Customers").child(customerId)
        var map: MutableLiveData<Map<String, Any>>? = null
        mCustomerDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.childrenCount > 0) map = dataSnapshot.value as MutableLiveData<Map<String, Any>>?
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // not implemented
            }
        })
        return map
    }
}
