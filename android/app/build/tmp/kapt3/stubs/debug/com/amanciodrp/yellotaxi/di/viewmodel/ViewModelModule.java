package com.amanciodrp.yellotaxi.di.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H!\u00a2\u0006\u0002\b\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\'\u00a8\u0006\f"}, d2 = {"Lcom/amanciodrp/yellotaxi/di/viewmodel/ViewModelModule;", "", "()V", "bindViewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "factory", "Lcom/amanciodrp/yellotaxi/di/viewmodel/ViewModelFactory;", "bindViewModelFactory$app_debug", "bindsPhonePopUpViewModel", "Landroidx/lifecycle/ViewModel;", "programsViewModel", "Lcom/amanciodrp/yellotaxi/viewmodel/PhonePopUpViewModel;", "app_debug"})
@dagger.Module()
public abstract class ViewModelModule {
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract androidx.lifecycle.ViewModelProvider.Factory bindViewModelFactory$app_debug(@org.jetbrains.annotations.NotNull()
    com.amanciodrp.yellotaxi.di.viewmodel.ViewModelFactory factory);
    
    @org.jetbrains.annotations.NotNull()
    @ViewModelKey(value = com.amanciodrp.yellotaxi.viewmodel.PhonePopUpViewModel.class)
    @dagger.multibindings.IntoMap()
    @dagger.Binds()
    public abstract androidx.lifecycle.ViewModel bindsPhonePopUpViewModel(@org.jetbrains.annotations.NotNull()
    com.amanciodrp.yellotaxi.viewmodel.PhonePopUpViewModel programsViewModel);
    
    public ViewModelModule() {
        super();
    }
}