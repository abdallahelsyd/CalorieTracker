package com.example.core.util

/**
 * Created by Abdallah Shehata on 9/9/2023.
 */
sealed class UiEvent {
    object OnFinished: UiEvent()
    object NavigateUp:UiEvent()
    data class ShowSnackBar(val msg:UiText):UiEvent()
}