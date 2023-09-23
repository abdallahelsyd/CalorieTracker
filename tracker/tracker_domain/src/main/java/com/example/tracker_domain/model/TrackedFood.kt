package com.example.tracker_domain.model

import java.time.LocalDate

/**
 * Created by Abdallah Shehata on 9/23/2023.
 */
data class TrackedFood(
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val mealType: MealType,
    val amount: Int,
    val date: LocalDate,
    val calories: Int,
    val id: Int? = null
)
