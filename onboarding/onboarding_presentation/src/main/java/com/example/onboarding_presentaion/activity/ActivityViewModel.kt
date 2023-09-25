package com.example.onboarding_presentaion.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.preferences.Preferences
import com.example.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abdallah Shehata on 9/10/2023.
 */
@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {
    var selectedActivityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onActivityLevelSelected(activityLevel: ActivityLevel) {
        selectedActivityLevel = activityLevel
    }

    fun onNextClicked() {
        viewModelScope.launch {
            preferences.saveActivityLevel(selectedActivityLevel)
            _uiEvent.send(UiEvent.OnFinished)
        }
    }
}