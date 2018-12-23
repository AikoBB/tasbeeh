package com.aigerimbb.android.tasbeeh

import com.aigerimbb.android.tasbeeh.di.DaggerTasbeehComponent
import com.aigerimbb.android.tasbeeh.di.TasbeehComponent
import com.iko.android.modularapp.application.CoreApp

class TasbeehApp: CoreApp(){

    val appComponent: TasbeehComponent by lazy {
        return@lazy DaggerTasbeehComponent
                .builder()
                .coreComponent(coreComponent)
                .build() as TasbeehComponent
    }
}