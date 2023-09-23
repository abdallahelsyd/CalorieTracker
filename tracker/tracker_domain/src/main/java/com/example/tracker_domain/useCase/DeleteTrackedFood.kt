package com.example.tracker_domain.useCase

import com.example.tracker_domain.TrackerRepository
import com.example.tracker_domain.model.TrackedFood

/**
 * Created by Abdallah Shehata on 9/23/2023.
 */
class DeleteTrackedFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}