package com.amanciodrp.yellotaxi.adapter;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amanciodrp.yellotaxi.R;
import com.amanciodrp.yellotaxi.onboarding.OnBoardItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


/**
 * Created by Jaison
 */


public class OnBoard_Adapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<OnBoardItem> onBoardItems;


    public OnBoard_Adapter(Context mContext, ArrayList<OnBoardItem> items) {
        this.mContext = mContext;
        this.onBoardItems = items;
    }

    @Override
    public int getCount() {
        return onBoardItems.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return view == object;
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.onboard_item, container, false);

        OnBoardItem item=onBoardItems.get(position);

        TextView tv_title= itemView.findViewById(R.id.tv_header);
        tv_title.setText(item.getTitle());

        TextView tv_content= itemView.findViewById(R.id.tv_desc);
        tv_content.setText(item.getDescription());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

}
