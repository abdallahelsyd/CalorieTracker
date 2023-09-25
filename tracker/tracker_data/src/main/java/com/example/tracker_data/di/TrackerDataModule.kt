package com.example.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.example.tracker_data.local.TrackerDatabase
import com.example.tracker_data.remote.FoodAPI
import com.example.tracker_data.repository.TrackerRepositoryImpl
import com.example.tracker_domain.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton
/**
 * Created by Abdallah Shehata on 9/22/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): FoodAPI {
        return Retrofit.Builder()
            .baseUrl(FoodAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }
    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): TrackerDatabase {
        return Room.databaseBuilder(
            app,
            TrackerDatabase::class.java,
            "tracker_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideTrackerRepository(
        api: FoodAPI,
        db: TrackerDatabase
    ): TrackerRepository {
        return TrackerRepositoryImpl(
            dao = db.dao,
            api = api
        )
    }
}