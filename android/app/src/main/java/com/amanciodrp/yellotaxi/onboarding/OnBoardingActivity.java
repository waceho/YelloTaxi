package com.amanciodrp.yellotaxi.onboarding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amanciodrp.yellotaxi.R;
import com.amanciodrp.yellotaxi.adapter.OnBoard_Adapter;
import com.amanciodrp.yellotaxi.customeractivity.CustomerLoginActivity;
import com.amanciodrp.yellotaxi.databinding.ActivityOnBoardingBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OnBoardingActivity extends AppCompatActivity {

    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPager onboard_pager;
    private OnBoard_Adapter mAdapter;
    private Button btn_get_started;
    private CardView onbardCard;
    private ActivityOnBoardingBinding binding;
    private boolean anim;

    int previous_pos = 0;

    ArrayList<OnBoardItem> onBoardItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        binding = bind();
        btn_get_started = findViewById(R.id.btn_get_started);
        onboard_pager = findViewById(R.id.pager_introduction);
        pager_indicator = findViewById(R.id.viewPagerCountDots);
        onbardCard = findViewById(R.id.onboardCard);

        loadData();

        mAdapter = new OnBoard_Adapter(this, onBoardItems);
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

                int pos = position + 1;

                if (pos == 1)
                    onboardingImgAnimation1(binding.onboardImg, R.drawable.onboard_page1);

                if (pos == 2) {
                    onboardingImgAnimation1(binding.onboardImg, R.drawable.onboard_page2);

                }

                if (pos == 3) {
                    binding.onboardImg.setImageResource(R.drawable.onboard_page3);
                }

                if (pos == 4) {
                    Animation hide = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_out_anim);
                   // binding.onboardImg.startAnimation(hide);
                    binding.onboardImg.setVisibility(View.GONE);
                    show_animation();

                    onbardCard.setVisibility(View.VISIBLE);
                    show_card_animation(onbardCard);

                } else if (previous_pos == dotsCount) {
                    hide_animation();
                    onbardCard.setVisibility(View.GONE);
                }

                previous_pos = pos;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("onboarding", String.valueOf(state));
            }
        });

        btn_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingActivity.this, CustomerLoginActivity.class));
            }
        });

        setUiPageViewController();
        resize();
    }

    private ActivityOnBoardingBinding bind(){
        return DataBindingUtil.setContentView(this, R.layout.activity_on_boarding);
    }

    // Load data into the viewpager

    public void loadData() {

        int[] header = {R.string.ob_header1, R.string.ob_header2, R.string.ob_header3, R.string.ob_header3};
        int[] desc = {R.string.ob_desc1, R.string.ob_desc2, R.string.ob_desc3};
        int[] imageId = {R.drawable.onboard_page1, R.drawable.onboard_page2, R.drawable.onboard_page3, R.drawable.onboard_page3};

        for (int i = 0; i < imageId.length; i++) {
            OnBoardItem item = new OnBoardItem();
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

        binding.onboardImg.setImageResource(R.drawable.onboard_page1);
    }

    // Button bottomUp animation

    private void show_animation() {
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

    public void show_card_animation(final CardView cardView) {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_down_onboarding_card);

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

    public void hide_animation() {
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

    private void onboardingImgAnimation1(final AppCompatImageView onboadingImage, final int resImg) {
        final Animation in = AnimationUtils.makeInAnimation(this, true);
        final Animation out = AnimationUtils.makeOutAnimation(this, true);

        onboadingImage.startAnimation(in);

        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                binding.onboardImg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.onboardImg.setImageResource(resImg);
                binding.onboardImg.clearAnimation();

            }

        });
    }

    private void onboardingImgAnimation2(final AppCompatImageView onboadingImage) {
        final Animation in = AnimationUtils.makeInAnimation(this, true);
        final Animation out = AnimationUtils.makeOutAnimation(this, true);

        onboadingImage.startAnimation(in);

        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                binding.onboardImg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                binding.onboardImg.clearAnimation();
                onboadingImage.startAnimation(in);

                in.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        binding.onboardImg.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        binding.onboardImg.clearAnimation();

                    }

                });


            }

        });
    }

    private void onboardingOutImgAnimation(final AppCompatImageView onboadingImage , final int resImg) {
        Animation out = AnimationUtils.makeOutAnimation(this, true);

        onboadingImage.startAnimation(out);

        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                binding.onboardImg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                binding.onboardImg.clearAnimation();
                binding.onboardImg.setImageResource(resImg);


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
    private void resize() {
        double density = getResources().getDisplayMetrics().density;
        if (density >= 4.0) {
            //"xxxhdpi";
            binding.onboardImg.getLayoutParams().height = 600;
            Log.d(OnBoardingActivity.class.getSimpleName(), "xxxhdpi");
        } else if (density >= 3.0 && density < 4.0) {
            //xxhdpi  //
            Log.d(OnBoardingActivity.class.getSimpleName(), "xxhdpi");
            binding.onboardImg.getLayoutParams().height = 700;
        } else if (density >= 2.0) {
            //xhdpi  // huawei
            Log.d(OnBoardingActivity.class.getSimpleName(), "xhdpi");
            binding.onboardImg.getLayoutParams().height = 550;
            findViewById(R.id.onboardCard).getLayoutParams().height = 900;

        } else if (density >= 1.5 && density < 2.0) {
            //hdpi
            Log.d(OnBoardingActivity.class.getSimpleName(), "hdpi");
            binding.onboardImg.getLayoutParams().height = 550;
        } else if (density >= 1.0 && density < 1.5) {
            //mdpi
            Log.d(OnBoardingActivity.class.getSimpleName(), "mdpi");
            binding.onboardImg.getLayoutParams().height = 550;
        }

    }


}
