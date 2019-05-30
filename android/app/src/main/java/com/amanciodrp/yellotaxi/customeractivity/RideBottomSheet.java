package com.amanciodrp.yellotaxi.customeractivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amanciodrp.yellotaxi.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.Nullable;

public class RideBottomSheet extends BottomSheetDialogFragment {

    public static RideBottomSheet newInstance() {
        return new RideBottomSheet();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_customer_drive_bottom_sheet, container,
                false);

        // get the views and attach the listener

        return view;

    }

}
