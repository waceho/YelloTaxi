package com.amanciodrp.yellotaxi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public abstract class ActivityCustomerLoginBinding extends ViewDataBinding {
  @NonNull
  public final ConstraintLayout coordinator;

  @NonNull
  public final EditText email;

  @NonNull
  public final AppCompatButton googleLogin;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final TextInputLayout inputLayoutEmail;

  @NonNull
  public final TextInputLayout inputLayoutPassword;

  @NonNull
  public final MaterialButton login;

  @NonNull
  public final TextView logoText;

  @NonNull
  public final TextView noAccountText;

  @NonNull
  public final EditText password;

  @NonNull
  public final AppCompatButton phoneLogin;

  @NonNull
  public final MaterialButton registration;

  @NonNull
  public final TextView textView;

  protected ActivityCustomerLoginBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ConstraintLayout coordinator, EditText email,
      AppCompatButton googleLogin, ImageView imageView2, TextInputLayout inputLayoutEmail,
      TextInputLayout inputLayoutPassword, MaterialButton login, TextView logoText,
      TextView noAccountText, EditText password, AppCompatButton phoneLogin,
      MaterialButton registration, TextView textView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.coordinator = coordinator;
    this.email = email;
    this.googleLogin = googleLogin;
    this.imageView2 = imageView2;
    this.inputLayoutEmail = inputLayoutEmail;
    this.inputLayoutPassword = inputLayoutPassword;
    this.login = login;
    this.logoText = logoText;
    this.noAccountText = noAccountText;
    this.password = password;
    this.phoneLogin = phoneLogin;
    this.registration = registration;
    this.textView = textView;
  }

  @NonNull
  public static ActivityCustomerLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCustomerLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCustomerLoginBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.activity_customer_login, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityCustomerLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCustomerLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCustomerLoginBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.activity_customer_login, null, false, component);
  }

  public static ActivityCustomerLoginBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityCustomerLoginBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityCustomerLoginBinding)bind(component, view, com.amanciodrp.yellotaxi.R.layout.activity_customer_login);
  }
}
