package com.example.onboarding_presentaion.age

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
 * Created by Abdallah Shehata on 9/11/2023.
 */
@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filter:FilterOutDigits
):ViewModel() {
    var age by mutableStateOf("20")
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent=_uiEvent.receiveAsFlow()


    fun onAgeEnter(age:String){
        if (age.length<=3)
            this.age=filter(age)
    }
    fun onNextClicked(){
        viewModelScope.launch {
            val ageNumber=age.toIntOrNull()?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StringRecourse(R.string.error_age_cant_be_empty))
                )
                return@launch
            }
            preferences.saveAge(ageNumber)
            _uiEvent.send(UiEvent.Navigate(Route.HEIGHT))
        }
    }
}