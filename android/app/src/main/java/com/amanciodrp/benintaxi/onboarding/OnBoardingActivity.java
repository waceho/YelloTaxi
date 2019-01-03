package com.amanciodrp.benintaxi.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amanciodrp.benintaxi.R;
import com.amanciodrp.benintaxi.adapter.OnBoard_Adapter;
import com.amanciodrp.benintaxi.customeractivity.CustomerLoginActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OnBoardingActivity extends AppCompatActivity {

    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPager onboard_pager;
    private OnBoard_Adapter mAdapter;
    private Button btn_get_started;
    private CircleImageView onboadingImage;
    private CardView onbardCard;

    int previous_pos=0;

    ArrayList<OnBoardItem> onBoardItems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        onboadingImage = findViewById(R.id.onboard_img);
        btn_get_started = findViewById(R.id.btn_get_started);
        onboard_pager = findViewById(R.id.pager_introduction);
        pager_indicator = findViewById(R.id.viewPagerCountDots);
        onbardCard = findViewById(R.id.onboardCard);

        loadData();

        mAdapter = new OnBoard_Adapter(this,onBoardItems);
        onboard_pager.setAdapter(mAdapter);
        onboard_pager.setCurrentItem(0);

        onboard_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                // Change the current position intimation

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.non_selected_item_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.selected_item_dot));

                int pos=position+1;

                if (pos == 1){
                    onboadingImage.setImageResource(R.drawable.onboard_page1);
                    onboardingDownImgAnimation(onboadingImage);
                }

                if (pos == 2){
                    onboadingImage.setImageResource(R.drawable.onboard_page2);
                    onboardingDownImgAnimation(onboadingImage);
                }

                if (pos == 3){
                    onboadingImage.setImageResource(R.drawable.onboard_page3);
                    onboardingDownImgAnimation(onboadingImage);
                }

                if (pos == 4){
                    Animation hide = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_up_end_anim);
                    onboadingImage.startAnimation(hide);
                    onboadingImage.setVisibility(View.GONE);
                    findViewById(R.id.onboarding_back).setBackgroundResource(R.drawable.onboarding_background2);
                    show_animation();

                    onbardCard.setVisibility(View.VISIBLE);
                    show_card_animation(onbardCard);

                }
                else if (previous_pos == dotsCount){
                    findViewById(R.id.onboarding_back).setBackgroundResource(R.drawable.onboarding_background);
                    hide_animation();
                    onbardCard.setVisibility(View.GONE);
                }

                previous_pos=pos;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(OnBoardingActivity.this,"Redirect to wherever you want",Toast.LENGTH_LONG).show();
                startActivity(new Intent(OnBoardingActivity.this, CustomerLoginActivity.class));
            }
        });

        setUiPageViewController();
        resize();

    }

    // Load data into the viewpager

    public void loadData()
    {

        int[] header = {R.string.ob_header1, R.string.ob_header2, R.string.ob_header3, R.string.ob_header3};
        int[] desc = {R.string.ob_desc1, R.string.ob_desc2, R.string.ob_desc3, R.string.ob_header3};
        int[] imageId = {R.drawable.onboard_page1, R.drawable.onboard_page2, R.drawable.onboard_page3, R.drawable.onboard_page3};

        for(int i=0;i<imageId.length;i++)
        {
            OnBoardItem item=new OnBoardItem();
            item.setImageID(imageId[i]);
            if (3 != i) {
                item.setTitle(getResources().getString(header[i]));
                item.setDescription(getResources().getString(desc[i]));
            } else {
                item.setTitle("");
                item.setDescription("");
            }


            onBoardItems.add(item);
        }

        onboadingImage.setImageResource(R.drawable.onboard_page1);
    }

    // Button bottomUp animation

    private void show_animation()
    {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_up_anim);

        btn_get_started.startAnimation(show);

        show.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                btn_get_started.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                btn_get_started.clearAnimation();

            }

        });

    }

    public void show_card_animation(final CardView cardView)
    {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_down_onboarding_card);
        Animation replaceTitle = AnimationUtils.loadAnimation(this, R.anim.slide_up_onboarding_title_anim);

        cardView.startAnimation(show);

        show.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                cardView.clearAnimation();

            }

        });


    }

    // Button Topdown animation

    public void hide_animation()
    {
        Animation hide = AnimationUtils.loadAnimation(this, R.anim.slide_down_anim);
        Animation hideCard = AnimationUtils.loadAnimation(this, R.anim.slide_up_end_anim);

        btn_get_started.startAnimation(hide);
        onbardCard.startAnimation(hideCard);

        hide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                btn_get_started.clearAnimation();
                btn_get_started.setVisibility(View.GONE);

            }

        });
    }

    private void onboardingDownImgAnimation(final CircleImageView onboadingImage){
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_down_onboarding_anim);

        onboadingImage.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                onboadingImage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                onboadingImage.clearAnimation();

            }

        });
    }

    private void onboardingUpImgAnimation(final CircleImageView onboadingImage){
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_up_onboarding_anim);

        onboadingImage.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                onboadingImage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                onboadingImage.clearAnimation();

            }

        });
    }

    // setup the
    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.non_selected_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(10, 0, 10, 0);

            pager_indicator.addView(dots[i], params);
            Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_up_anim);
            dots[i].startAnimation(show);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.selected_item_dot));
    }

    // set image size
    private void resize(){
        double density = getResources().getDisplayMetrics().density;
        if (density >= 4.0) {
            //"xxxhdpi";
            findViewById(R.id.onboard_img).getLayoutParams().height = 600;
            Log.d(OnBoardingActivity.class.getSimpleName(), "xxxhdpi");
        }
        else if (density >= 3.0 && density < 4.0) {
            //xxhdpi  //
            Log.d(OnBoardingActivity.class.getSimpleName(), "xxhdpi");
            findViewById(R.id.onboard_img).getLayoutParams().height = 700;
        }
        else if (density >= 2.0) {
            //xhdpi  // huawei
            Log.d(OnBoardingActivity.class.getSimpleName(), "xhdpi");
            findViewById(R.id.onboard_img).getLayoutParams().height = 850;
            findViewById(R.id.onboardCard).getLayoutParams().height = 1000;

        }
        else if (density >= 1.5 && density < 2.0) {
            //hdpi
            Log.d(OnBoardingActivity.class.getSimpleName(), "hdpi");
            findViewById(R.id.onboard_img).getLayoutParams().height = 750;
        }
        else if (density >= 1.0 && density < 1.5) {
            //mdpi
            Log.d(OnBoardingActivity.class.getSimpleName(), "mdpi");
            findViewById(R.id.onboard_img).getLayoutParams().height = 700;
        }

    }


}
