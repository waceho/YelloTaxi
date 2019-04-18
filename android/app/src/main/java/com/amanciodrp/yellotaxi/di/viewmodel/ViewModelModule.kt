package com.amanciodrp.yellotaxi.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amanciodrp.yellotaxi.viewmodel.PhonePopUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PhonePopUpViewModel::class)
    abstract fun bindsPhonePopUpViewModel(programsViewModel: PhonePopUpViewModel): ViewModel

   }