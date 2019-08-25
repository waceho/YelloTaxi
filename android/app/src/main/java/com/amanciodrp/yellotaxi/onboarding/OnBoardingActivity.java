package com.amanciodrp.yellotaxi.onboarding;

import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amanciodrp.yellotaxi.R;
import com.amanciodrp.yellotaxi.adapter.OnBoardAdapter;
import com.amanciodrp.yellotaxi.customeractivity.CustomerLoginActivity;
import com.amanciodrp.yellotaxi.databinding.ActivityOnBoardingBinding;
import com.amanciodrp.yellotaxi.driveractivity.DriverLoginActivity;
import com.amanciodrp.yellotaxi.model.DefaultUseSettings;
import com.amanciodrp.yellotaxi.utils.BulletListUtils;
import com.amanciodrp.yellotaxi.utils.SharedPrefsObject;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {

    private String TAG = OnBoardingActivity.class.getSimpleName();

    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private CustomViewPager onboard_pager;
    private OnBoardAdapter mAdapter;
    private CardView onbardCard;
    private ActivityOnBoardingBinding binder;
    private DefaultUseSettings defaultUseSettings = new DefaultUseSettings();

    int previousPos = 0;

    ArrayList<OnBoardItem> onBoardItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = DataBindingUtil.setContentView(this, R.layout.activity_on_boarding);

        onboard_pager = findViewById(R.id.pager_introduction);
        pager_indicator = findViewById(R.id.viewPagerCountDots);
        onbardCard = findViewById(R.id.onboardCard);

        loadData();

        mAdapter = new OnBoardAdapter(this, onBoardItems);
        onboard_pager.setAdapter(mAdapter);
        onboard_pager.setCurrentItem(0);

        onboard_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

                if (arg0 == 2)
                    onboard_pager.setAllowedSwipeDirection(SwipeDirection.left);
                else
                    onboard_pager.setAllowedSwipeDirection(SwipeDirection.all);
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
                    onboardingImgAnimation1(binder.onboardImg, R.drawable.onboard_page1);

                if (pos == 2) {
                    // make hide animation if come from position 3
                    if (previousPos == 3) {
                        hide_animation();
                    }
                    binder.onboardImg.setVisibility(View.VISIBLE);
                    onbardCard.setVisibility(View.GONE);
                    onboardingImgAnimation1(binder.onboardImg, R.drawable.onboard_page2);
                }

                if (pos == 3) {
                    binder.onboardImg.setVisibility(View.GONE);
                    btnGetStartedAnimation();
                    onbardCard.setVisibility(View.VISIBLE);
                    show_card_animation(onbardCard);
                    binder.onboard3RL.setVisibility(View.VISIBLE);
                    binder.onboard4RL.setVisibility(View.GONE);
                    binder.btnGetStarted.setText(getString(R.string.next));
                }

                if (pos == 4) {
                    Animation fadeIn = new AlphaAnimation(0, 1);
                    fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
                    fadeIn.setDuration(1000);
                    binder.onboardCard.startAnimation(fadeIn);
                    binder.btnGetStarted.setText(getString(R.string.ok));
                }

                previousPos = pos;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // set btnGetStarted and chipgroup onclickListener
        chipsWidgetOnclickListener();
        // set UiPageViewController
        setUiPageViewController();
    }

    private void chipsWidgetOnclickListener() {
        binder.btnGetStarted.setOnClickListener(v -> lockLastOnboardingView());
        binder.chipgroup.setOnCheckedChangeListener((chipGroup, i) -> setMode());
    }

    private void setMode() {
        boolean status = binder.conducteur.isChecked() || binder.passager.isChecked();
        binder.btnGetStarted.setEnabled(status);
        // set mode
        String mode = !binder.conducteur.isChecked() ? getString(R.string.passager) : getString(R.string.chauffeur);
        defaultUseSettings.setMode(mode);
    }

    private void saveUserDefaullMode() {
        defaultUseSettings.setShowOnboarding(true);
        SharedPrefsObject.saveObjectToSharedPreference(getBaseContext(), DefaultUseSettings.class.getSimpleName(), DefaultUseSettings.class.getSimpleName(), defaultUseSettings);
    }

    // hide last onboarding page
    private void lockLastOnboardingView() {
        if (previousPos == 3) {
            // set swipe direction
            onboard_pager.setAllowedSwipeDirection(SwipeDirection.all);
            // set arrow scroll left to right
            onboard_pager.arrowScroll(View.LAYOUT_DIRECTION_LTR);
            // set current item index
            onboard_pager.setCurrentItem(3);
            // hide onboarding 3 content
            binder.onboard3RL.setVisibility(View.GONE);
            // show onboarding 4 content
            binder.onboard4RL.setVisibility(View.VISIBLE);
            // set Card title text
            binder.title.setText(getString(R.string.last_onboarding_title));
            // hide subtitle if diver mode
            binder.subtitle.setVisibility(defaultUseSettings.getMode().equals(getString(R.string.passager)) ? View.VISIBLE : View.GONE);
            binder.subtitle.setText(getString(R.string.last_onboarding_subtitle));
            // set bullet list
            setBulledList();

        } else {
            // save user default conf
            saveUserDefaullMode();
            // launch Customer Login
            if (defaultUseSettings.getMode().equals(getString(R.string.passager))) {
                startActivity(new Intent(OnBoardingActivity.this, CustomerLoginActivity.class));
                finish();
            } else {
                startActivity(new Intent(OnBoardingActivity.this, DriverLoginActivity.class));
                finish();
            }
        }
    }

    private void setBulledList() {
        CharSequence bulletedList = defaultUseSettings.getMode().equals(getString(R.string.passager)) ?
                BulletListUtils.makeBulletList(20,
                        getString(R.string.lastOnboardingExplanation_1),
                        getString(R.string.lastOnboardingExplanation_2),
                        getString(R.string.lastOnboardingExplanation_3),
                        getString(R.string.lastOnboardingExplanation_4)) :

                BulletListUtils.makeBulletList(20,

                        getString(R.string.lastOnboardingDriveExplanation_2),
                        getString(R.string.lastOnboardingDriveExplanation_3),
                        getString(R.string.lastOnboardingDriveExplanation_4),
                        getString(R.string.lastOnboardingDriveExplanation_5));

        binder.act1.setText(bulletedList);

    }

    // Load data into the viewpager
    public void loadData() {

        int[] header = {R.string.ob_header1, R.string.ob_header2, R.string.ob_header3, R.string.ob_header3};
        int[] desc = {R.string.ob_desc1, R.string.ob_desc2, R.string.ob_desc3};
        int[] imageId = {R.drawable.onboard_page1, R.drawable.onboard_page2, R.drawable.onboard_page3, R.drawable.onboard_page3};

        for (int i = 0; i < imageId.length; i++) {
            OnBoardItem item = new OnBoardItem();
            item.setImageID(imageId[i]);
            if (2 == i || 3 == i) {
                item.setTitle("");
                item.setDescription("");
            } else {
                item.setTitle(getResources().getString(header[i]));
                item.setDescription(getResources().getString(desc[i]));
            }

            onBoardItems.add(item);
        }

        binder.onboardImg.setImageResource(R.drawable.onboard_page1);
    }

    // Button bottomUp animation
    private void btnGetStartedAnimation() {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_up_anim);
        binder.btnGetStarted.setVisibility(View.VISIBLE);
        binder.btnGetStarted.startAnimation(show);

        show.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binder.btnGetStarted.clearAnimation();
            }

        });
    }

    /**
     * @param cardView
     */
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

    // hide animation
    private void hide_animation() {
        Animation hide = AnimationUtils.loadAnimation(this, R.anim.slide_down_anim);
        Animation hideCard = AnimationUtils.loadAnimation(this, R.anim.slide_up_end_anim);

        binder.btnGetStarted.startAnimation(hide);
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

                binder.btnGetStarted.clearAnimation();
                binder.btnGetStarted.setVisibility(View.GONE);

            }

        });
    }

    /**
     * @param onboadingImage
     * @param resImg
     */
    private void onboardingImgAnimation1(final AppCompatImageView onboadingImage, final int resImg) {
        binder.onboardImg.setImageResource(resImg);
        final Animation in = AnimationUtils.makeInAnimation(this, true);
        final Animation out = AnimationUtils.makeOutAnimation(this, true);

        onboadingImage.startAnimation(in);

        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                binder.onboardImg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binder.onboardImg.clearAnimation();
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

}
