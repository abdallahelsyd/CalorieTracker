package com.example.onboarding_presentaion.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.R
import com.example.core.domain.preferences.Preferences
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
class WeightViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {
    var weight by mutableStateOf("70.0")
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent=_uiEvent.receiveAsFlow()


    fun onWeightEntered(weight:String){
        if (weight.length<=5)
            this.weight=weight
    }
    fun onNextClicked(){
        viewModelScope.launch {
            val weightNum=weight.toFloatOrNull()?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_weight_cant_be_empty))
                )
                return@launch
            }
            preferences.saveWeight(weightNum)
            _uiEvent.send(UiEvent.OnFinished)
        }
    }
}