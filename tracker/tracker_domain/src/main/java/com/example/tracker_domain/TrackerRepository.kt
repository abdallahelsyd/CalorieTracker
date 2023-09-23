package com.example.tracker_domain

import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * Created by Abdallah Shehata on 9/23/2023.
 */
interface TrackerRepository {
    suspend fun searchFood(
        query:String,
        page: Int,
        pageSize: Int,
    ):Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>>
}