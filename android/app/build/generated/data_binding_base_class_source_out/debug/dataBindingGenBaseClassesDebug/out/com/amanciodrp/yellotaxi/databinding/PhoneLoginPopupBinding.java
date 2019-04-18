package com.amanciodrp.yellotaxi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amanciodrp.yellotaxi.viewmodel.PhonePopUpViewModel;
import com.google.android.material.textfield.TextInputLayout;

public abstract class PhoneLoginPopupBinding extends ViewDataBinding {
  @NonNull
  public final EditText edCode;

  @NonNull
  public final TextInputLayout inputLayoutCode;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView txtCode;

  @NonNull
  public final AppCompatButton valider;

  @Bindable
  protected PhonePopUpViewModel mUserPhone;

  protected PhoneLoginPopupBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, EditText edCode, TextInputLayout inputLayoutCode, TextView textView4,
      TextView txtCode, AppCompatButton valider) {
    super(_bindingComponent, _root, _localFieldCount);
    this.edCode = edCode;
    this.inputLayoutCode = inputLayoutCode;
    this.textView4 = textView4;
    this.txtCode = txtCode;
    this.valider = valider;
  }

  public abstract void setUserPhone(@Nullable PhonePopUpViewModel userPhone);

  @Nullable
  public PhonePopUpViewModel getUserPhone() {
    return mUserPhone;
  }

  @NonNull
  public static PhoneLoginPopupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static PhoneLoginPopupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<PhoneLoginPopupBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.phone_login_popup, root, attachToRoot, component);
  }

  @NonNull
  public static PhoneLoginPopupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static PhoneLoginPopupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<PhoneLoginPopupBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.phone_login_popup, null, false, component);
  }

  public static PhoneLoginPopupBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static PhoneLoginPopupBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (PhoneLoginPopupBinding)bind(component, view, com.amanciodrp.yellotaxi.R.layout.phone_login_popup);
  }
}
