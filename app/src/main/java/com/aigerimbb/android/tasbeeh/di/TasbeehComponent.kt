package com.aigerimbb.android.tasbeeh.di

import com.aigerimbb.android.tasbeeh.ui.main.MainActivity
import com.aigerimbb.android.tasbeeh.ui.main.profit.ProfitFragment
import com.aigerimbb.android.tasbeeh.ui.main.tasbeeh.TasbeehFragment
import com.aigerimbb.android.tasbeeh.ui.main.tasbeeh_list.TasbeehListFragment
import com.aigerimbb.android.tasbeeh.ui.update.UpdateTasbeehActivity
import com.iko.android.modularapp.di.component.CoreComponent
import com.iko.android.modularapp.di.viewmodel.ViewModelModule
import dagger.Component

@ApplicationScope
@Component(modules = [TasbeehModule::class,
    ViewModelModule::class,
    RepositoryProvider::class,
    TasbeehViewModelProvider::class],
        dependencies = [CoreComponent::class])
interface TasbeehComponent{
    fun inject(activity: MainActivity)
    fun inject(fragment: TasbeehFragment)
    fun inject(fragment: TasbeehListFragment)
    fun inject(fragment: ProfitFragment)
    fun inject(activity: UpdateTasbeehActivity)
}