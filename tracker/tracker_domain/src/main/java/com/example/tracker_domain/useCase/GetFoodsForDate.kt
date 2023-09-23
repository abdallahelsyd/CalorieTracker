package com.example.tracker_domain.useCase

import com.example.tracker_domain.TrackerRepository
import com.example.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
/**
 * Created by Abdallah Shehata on 9/23/2023.
 */
class GetFoodsForDate(
    private val repository: TrackerRepository
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(date)
    }
}