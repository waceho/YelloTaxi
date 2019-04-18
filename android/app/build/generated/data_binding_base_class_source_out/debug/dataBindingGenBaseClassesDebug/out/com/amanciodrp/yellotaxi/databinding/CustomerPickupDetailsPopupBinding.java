package com.amanciodrp.yellotaxi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class CustomerPickupDetailsPopupBinding extends ViewDataBinding {
  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageView imgCustomer;

  @NonNull
  public final TextView textView9;

  @NonNull
  public final TextView txtAdressValue;

  @NonNull
  public final TextView txtDestination;

  @NonNull
  public final TextView txtName;

  @NonNull
  public final TextView txtNameValue;

  @NonNull
  public final TextView txtPhone;

  @NonNull
  public final TextView txtPrice;

  @NonNull
  public final TextView txtTarifValue;

  @NonNull
  public final AppCompatButton valider;

  protected CustomerPickupDetailsPopupBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageView imageView3, ImageView imgCustomer, TextView textView9,
      TextView txtAdressValue, TextView txtDestination, TextView txtName, TextView txtNameValue,
      TextView txtPhone, TextView txtPrice, TextView txtTarifValue, AppCompatButton valider) {
    super(_bindingComponent, _root, _localFieldCount);
    this.imageView3 = imageView3;
    this.imgCustomer = imgCustomer;
    this.textView9 = textView9;
    this.txtAdressValue = txtAdressValue;
    this.txtDestination = txtDestination;
    this.txtName = txtName;
    this.txtNameValue = txtNameValue;
    this.txtPhone = txtPhone;
    this.txtPrice = txtPrice;
    this.txtTarifValue = txtTarifValue;
    this.valider = valider;
  }

  @NonNull
  public static CustomerPickupDetailsPopupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CustomerPickupDetailsPopupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CustomerPickupDetailsPopupBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.customer_pickup_details_popup, root, attachToRoot, component);
  }

  @NonNull
  public static CustomerPickupDetailsPopupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CustomerPickupDetailsPopupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CustomerPickupDetailsPopupBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.customer_pickup_details_popup, null, false, component);
  }

  public static CustomerPickupDetailsPopupBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static CustomerPickupDetailsPopupBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (CustomerPickupDetailsPopupBinding)bind(component, view, com.amanciodrp.yellotaxi.R.layout.customer_pickup_details_popup);
  }
}
