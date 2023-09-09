package com.example.calorietracker.ui.navigation

import androidx.navigation.NavController
import com.example.core.util.UiEvent

/**
 * Created by Abdallah Shehata on 9/9/2023.
 */

fun NavController.navigate(event: UiEvent.Navigate){
    this.navigate(event.route)
}