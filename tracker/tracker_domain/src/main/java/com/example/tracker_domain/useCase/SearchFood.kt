package com.example.tracker_domain.useCase

import com.example.tracker_domain.TrackerRepository
import com.example.tracker_domain.model.TrackableFood

/**
 * Created by Abdallah Shehata on 9/23/2023.
 */
class SearchFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {
        if(query.isBlank()) {
            return Result.success(emptyList())
        }
        return repository.searchFood(query.trim(), page, pageSize)
    }
}