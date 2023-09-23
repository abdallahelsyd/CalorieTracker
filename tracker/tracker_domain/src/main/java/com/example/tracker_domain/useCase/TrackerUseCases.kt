package com.example.tracker_domain.useCase

/**
 * Created by Abdallah Shehata on 9/23/2023.
 */
data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val getFoodsForDate: GetFoodsForDate,
    val deleteTrackedFood: DeleteTrackedFood,
    val calculateMealNutrients: CalculateMealNutrients
)
