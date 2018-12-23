package com.aigerimbb.android.tasbeeh.di

import androidx.lifecycle.ViewModel
import com.aigerimbb.android.tasbeeh.ui.main.MainViewModel
import com.aigerimbb.android.tasbeeh.ui.update.UpdateTasbeehViewModel
import com.iko.android.modularapp.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TasbeehViewModelProvider{

    @Binds
    @IntoMap
    @ApplicationScope
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdateTasbeehViewModel::class)
    abstract fun bindUpdateTasbeehViewModel(viewModel: UpdateTasbeehViewModel): ViewModel

}