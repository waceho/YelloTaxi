package com.amanciodrp.yellotaxi

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amanciodrp.yellotaxi.model.CustomerRequest
import com.amanciodrp.yellotaxi.support.CustomerRequestDetailsInterface
import kotlinx.android.synthetic.main.activity_driver_map.*
import kotlinx.android.synthetic.main.activity_driver_map.customerDestination
import kotlinx.android.synthetic.main.activity_driver_map.customerName
import kotlinx.android.synthetic.main.activity_driver_map.rideStatus
import kotlinx.android.synthetic.main.fragment_customer_request_dialog.*

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    CustomerRequestDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 *
 */
class CustomerRequestDialogFragment : BottomSheetDialogFragment() {
    private lateinit var mListener: CustomerRequestDetailsInterface
    private lateinit var mCustomerRequest: CustomerRequest
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customer_request_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_customerDestination_MBS.setText(mCustomerRequest.destination)
        tv_customerName_MBS.setText(mCustomerRequest.name)
        tv_customerPhone_MBS.setText(mCustomerRequest.phone)
        rideStatus_MBS.setOnClickListener { mListener.onRideStatusBtnClick() }
    }

    fun setOnCustomerRequestListener(listener: CustomerRequestDetailsInterface){
        mListener = listener
    }

    companion object {

        // TODO: Customize parameters
        fun newInstance(customerRequest: CustomerRequest): CustomerRequestDialogFragment =
                CustomerRequestDialogFragment().apply {
                    arguments = Bundle().apply {
                    mCustomerRequest = customerRequest
                    }
                }

    }
}
