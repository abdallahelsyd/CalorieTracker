package com.example.calorietracker.repository

import com.example.tracker_domain.TrackerRepository
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.LocalDate
import kotlin.random.Random

/**
 * Created by Abdallah Shehata on 10/11/2023.
 */
class TrackerRepositoryFake: TrackerRepository {
    var shouldReturnError =false
    private val trackedFood= mutableListOf<TrackedFood>()
    var searchResult = listOf<TrackableFood>()
    private val getFoodsForDateFlow= MutableSharedFlow<List<TrackedFood>>(replay = 1)
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return if (shouldReturnError){
            Result.failure(Throwable())
        }else
            Result.success(searchResult)
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        trackedFood.add(food.copy(id = Random.nextInt()))
        getFoodsForDateFlow.emit(trackedFood)
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        trackedFood.remove(food)
        getFoodsForDateFlow.emit(trackedFood)

    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return getFoodsForDateFlow
    }
}