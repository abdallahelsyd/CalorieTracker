package com.example.core.util

/**
 * Created by Abdallah Shehata on 9/9/2023.
 */
sealed class UiEvent {
    data class Navigate(val route:String): UiEvent()
    object NavigateUp:UiEvent()
}