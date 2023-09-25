package com.example.tracker_presentation.search

import com.example.tracker_domain.model.TrackableFood

/**
 * Created by Abdallah Shehata on 9/25/2023.
 */
data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
