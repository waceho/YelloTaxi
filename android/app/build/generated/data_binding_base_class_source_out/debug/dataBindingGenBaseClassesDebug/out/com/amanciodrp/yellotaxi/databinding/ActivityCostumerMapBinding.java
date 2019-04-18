package com.amanciodrp.yellotaxi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public abstract class ActivityCostumerMapBinding extends ViewDataBinding {
  @NonNull
  public final RadioButton UberBlack;

  @NonNull
  public final RadioButton UberX;

  @NonNull
  public final RadioButton UberXl;

  @NonNull
  public final FloatingActionButton account;

  @NonNull
  public final TextView driverCar;

  @NonNull
  public final LinearLayout driverInfo;

  @NonNull
  public final TextView driverName;

  @NonNull
  public final TextView driverPhone;

  @NonNull
  public final ImageView driverProfileImage;

  @NonNull
  public final TextView end;

  @NonNull
  public final FloatingActionButton history;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final FloatingActionButton logout;

  @NonNull
  public final FloatingActionMenu menuYellow;

  @NonNull
  public final com.google.android.material.floatingactionbutton.FloatingActionButton myLocation;

  @NonNull
  public final FloatingActionButton payement;

  @NonNull
  public final RadioGroup radioGroup;

  @NonNull
  public final RatingBar ratingBar;

  @NonNull
  public final Button request;

  @NonNull
  public final TextView start;

  protected ActivityCostumerMapBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RadioButton UberBlack, RadioButton UberX, RadioButton UberXl,
      FloatingActionButton account, TextView driverCar, LinearLayout driverInfo,
      TextView driverName, TextView driverPhone, ImageView driverProfileImage, TextView end,
      FloatingActionButton history, LinearLayout linearLayout, FloatingActionButton logout,
      FloatingActionMenu menuYellow,
      com.google.android.material.floatingactionbutton.FloatingActionButton myLocation,
      FloatingActionButton payement, RadioGroup radioGroup, RatingBar ratingBar, Button request,
      TextView start) {
    super(_bindingComponent, _root, _localFieldCount);
    this.UberBlack = UberBlack;
    this.UberX = UberX;
    this.UberXl = UberXl;
    this.account = account;
    this.driverCar = driverCar;
    this.driverInfo = driverInfo;
    this.driverName = driverName;
    this.driverPhone = driverPhone;
    this.driverProfileImage = driverProfileImage;
    this.end = end;
    this.history = history;
    this.linearLayout = linearLayout;
    this.logout = logout;
    this.menuYellow = menuYellow;
    this.myLocation = myLocation;
    this.payement = payement;
    this.radioGroup = radioGroup;
    this.ratingBar = ratingBar;
    this.request = request;
    this.start = start;
  }

  @NonNull
  public static ActivityCostumerMapBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCostumerMapBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCostumerMapBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.activity_costumer_map, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityCostumerMapBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCostumerMapBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCostumerMapBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.activity_costumer_map, null, false, component);
  }

  public static ActivityCostumerMapBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityCostumerMapBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityCostumerMapBinding)bind(component, view, com.amanciodrp.yellotaxi.R.layout.activity_costumer_map);
  }
}
