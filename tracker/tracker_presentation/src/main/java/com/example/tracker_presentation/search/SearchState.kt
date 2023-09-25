package com.plcoding.tracker_presentation.search

import com.example.tracker_presentation.search.TrackableFoodUiState

/**
 * Created by Abdallah Shehata on 9/25/2023.
 */
data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList()
)