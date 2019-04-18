package com.amanciodrp.yellotaxi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class DefaultDialogPopupBinding extends ViewDataBinding {
  @NonNull
  public final TextView msg;

  @NonNull
  public final AppCompatButton valider;

  protected DefaultDialogPopupBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView msg, AppCompatButton valider) {
    super(_bindingComponent, _root, _localFieldCount);
    this.msg = msg;
    this.valider = valider;
  }

  @NonNull
  public static DefaultDialogPopupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static DefaultDialogPopupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<DefaultDialogPopupBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.default_dialog_popup, root, attachToRoot, component);
  }

  @NonNull
  public static DefaultDialogPopupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static DefaultDialogPopupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<DefaultDialogPopupBinding>inflate(inflater, com.amanciodrp.yellotaxi.R.layout.default_dialog_popup, null, false, component);
  }

  public static DefaultDialogPopupBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static DefaultDialogPopupBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (DefaultDialogPopupBinding)bind(component, view, com.amanciodrp.yellotaxi.R.layout.default_dialog_popup);
  }
}
