package com.aigerimbb.android.tasbeeh.di

import android.content.Context
import com.aigerimbb.android.tasbeeh.data.database.TasbeehDatabase
import dagger.Module
import dagger.Provides

@Module
class TasbeehModule {

    @ApplicationScope
    @Provides
    internal fun provideDatabase(context: Context) = TasbeehDatabase.getInstance(context)

}