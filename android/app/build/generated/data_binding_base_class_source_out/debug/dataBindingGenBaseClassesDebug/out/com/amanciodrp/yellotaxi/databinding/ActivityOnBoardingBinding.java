package com.amanciodrp.yellotaxi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amanciodrp.yellotaxi.onboarding.CustomViewPager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public abstract class ActivityOnBoardingBinding extends ViewDataBinding {
  @NonNull
  public final TextView act1;

  @NonNull
  public final MaterialButton btnGetStarted;

  @NonNull
  public final TextView cardTitle;

  @NonNull
  public final ChipGroup chipgroup;

  @NonNull
  public final LinearLayout chipsLayout;

  @NonNull
  public final Chip conducteur;

  @NonNull
  public final ConstraintLayout coordinator;

  @NonNull
  public final LinearLayout llFooter;

  @NonNull
  public final RelativeLayout onboard3RL;

  @NonNull
  public final RelativeLayout onboard4RL;

  @NonNull
  public final MaterialCardView onboardCard;

  @NonNull
  public final AppCompatImageView onboardImg;

  @NonNull
  public final TextView optionMsg;

  @NonNull
  public final CustomViewPager pagerIntroduction;

  @NonNull
  public final Chip passager;

  @NonNull
  public final TextView subtitle;

  @NonNull
  public final TextView title;

  @NonNull
  public final LinearLayout viewPagerCountDots;

  protected ActivityOnBoardingBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView act1, MaterialButton btnGetStarted, TextView cardTitle,
      ChipGroup chipgroup, LinearLayout chipsLayout, Chip conducteur, ConstraintLayout coordinator,
      LinearLayout llFooter, RelativeLayout onboard3RL, RelativeLayout onboard4RL,
      MaterialCardView onboardCard, AppCompatImageView onboardImg, TextView optionMsg,
      CustomViewPager pagerIntroduction, Chip passager, TextView subtitle, TextView title,
      LinearLayout viewPagerCountDots) {
    super(_bindingComponent, _root, _localFieldCount);
    this.act1 = act1;
    this.btnGetStarted = btnGetStarted;
    this.cardTitle = cardTitle;
    this.chipgroup = chipgroup;
    this.chipsLayout = chipsLayout;
    this.conducteur = conducteur;
    this.coordinator = coordinator;
    this.llFooter = llFooter;
    this.onboard3RL = onboard3RL;
    this.onboard4RL = onboard4RL;
    this.onboardCard = onboardCard;
    this.onboardImg = onboardImg;
    this.optionMsg = optionMsg;
    this.pagerIntroduction = pagerIntroduction;
    this.passager = passager;
    this.subtitle = subtitle;
    this.title = title;
    this.viewPagerCountDots = viewPagerCountDots;
  }

  @NonNull
  public static ActivityOnBoardingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityOnBoardingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityOnBoardingBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.activity_on_boarding, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityOnBoardingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityOnBoardingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityOnBoardingBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.activity_on_boarding, null, false, component);
  }

  public static ActivityOnBoardingBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityOnBoardingBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityOnBoardingBinding)bind(component, view, com.amanciodrp.yellotaxi.R.layout.activity_on_boarding);
  }
}
