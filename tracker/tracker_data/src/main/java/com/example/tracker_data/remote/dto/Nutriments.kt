package com.example.tracker_data.remote.dto


import com.squareup.moshi.Json
/**
 * Created by Abdallah Shehata on 9/22/2023.
 */
data class Nutriments(
    @field:Json(name = "carbohydrates_100g")
    val carbohydrates100g: Double,
    @field:Json(name = "energy-kcal_100g")
    val energyKcal100g: Double,
    @field:Json(name = "fat_100g")
    val fat100g: Double,
    @field:Json(name = "proteins_100g")
    val proteins100g: Double
)