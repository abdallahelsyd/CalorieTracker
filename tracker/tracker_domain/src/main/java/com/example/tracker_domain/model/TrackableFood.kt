package com.example.tracker_domain.model

/**
 * Created by Abdallah Shehata on 9/23/2023.
 */
data class TrackableFood(
    val name: String,
    val imageUrl: String?,
    val caloriesPer100g: Int,
    val carbsPer100g: Int,
    val proteinPer100g: Int,
    val fatPer100g: Int
)
