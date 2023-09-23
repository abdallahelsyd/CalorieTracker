package com.example.tracker_domain.di


import com.example.core.domain.preferences.Preferences
import com.example.tracker_domain.TrackerRepository
import com.example.tracker_domain.useCase.CalculateMealNutrients
import com.example.tracker_domain.useCase.DeleteTrackedFood
import com.example.tracker_domain.useCase.GetFoodsForDate
import com.example.tracker_domain.useCase.SearchFood
import com.example.tracker_domain.useCase.TrackFood
import com.example.tracker_domain.useCase.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
    }
}