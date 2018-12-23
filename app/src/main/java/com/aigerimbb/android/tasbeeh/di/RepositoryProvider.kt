package com.aigerimbb.android.tasbeeh.di

import com.aigerimbb.android.tasbeeh.domain.repository.TasbeehRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryProvider {

    @Provides
    @ApplicationScope
    fun provideTasbeehRepository(dataSource: TasbeehRepository.Database): TasbeehRepository = dataSource

}