package com.gobinda.sample.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Build
import com.gobinda.sample.BuildConfig
import com.gobinda.sample.data.source.UserRepository
import com.gobinda.sample.data.source.UserRepositoryImpl
import com.gobinda.sample.data.source.local.room.UserLocalDataSource
import com.gobinda.sample.data.source.local.room.SampleDatabase
import com.gobinda.sample.data.source.remote.api.UserRemoteDataSource
import com.gobinda.sample.data.source.remote.api.RestApi
import com.gobinda.sample.data.source.remote.api.SampleRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteTasksDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalTasksDataSource


@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SampleDatabase =
        SampleDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideApi(): RestApi = SampleRetrofit.api

    @Singleton
    @Provides
    fun provideResources(@ApplicationContext context: Context): Resources = context.resources

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences(
        BuildConfig.APPLICATION_ID + ".preferences", MODE_PRIVATE
    )

    @Singleton
    @Provides
    fun provideUserRepository(
        local: UserLocalDataSource,
        remote: UserRemoteDataSource
    ): UserRepository = UserRepositoryImpl(local, remote)

}