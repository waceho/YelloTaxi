package com.amanciodrp.yellotaxi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class OnboardItemBinding extends ViewDataBinding {
  @NonNull
  public final TextView tvDesc;

  @NonNull
  public final TextView tvHeader;

  protected OnboardItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView tvDesc, TextView tvHeader) {
    super(_bindingComponent, _root, _localFieldCount);
    this.tvDesc = tvDesc;
    this.tvHeader = tvHeader;
  }

  @NonNull
  public static OnboardItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static OnboardItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<OnboardItemBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.onboard_item, root, attachToRoot, component);
  }

  @NonNull
  public static OnboardItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static OnboardItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<OnboardItemBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.onboard_item, null, false, component);
  }

  public static OnboardItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static OnboardItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (OnboardItemBinding)bind(component, view, com.amanciodrp.yellotaxi.R.layout.onboard_item);
  }
}
