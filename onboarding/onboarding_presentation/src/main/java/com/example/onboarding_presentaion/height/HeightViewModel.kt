package com.example.onboarding_presentaion.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.R
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.usecase.FilterOutDigits
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abdallah Shehata on 9/14/2023.
 */
@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filter: FilterOutDigits
): ViewModel() {
    var height by mutableStateOf("170")
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent=_uiEvent.receiveAsFlow()


    fun onHeightEntered(height:String){
        if (height.length<=3)
            this.height=filter(height)
    }
    fun onNextClicked(){
        viewModelScope.launch {
            val heightNum=height.toIntOrNull()?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_height_cant_be_empty))
                )
                return@launch
            }
            preferences.saveHeight(heightNum)
            _uiEvent.send(UiEvent.Navigate(Route.WEIGHT))
        }
    }
}