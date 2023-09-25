package com.example.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calorietracker.ui.navigation.navigate
import com.example.calorietracker.ui.theme.CalorieTrackerTheme
import com.example.core.navigation.Route
import com.example.onboarding_presentaion.activity.ActivityScreen
import com.example.onboarding_presentaion.age.AgeScreen
import com.example.onboarding_presentaion.gender.GenderScreen
import com.example.onboarding_presentaion.goal.GoalScreen
import com.example.onboarding_presentaion.height.HeightScreen
import com.example.onboarding_presentaion.nutrient_goal.NutrientGoalScreen
import com.example.onboarding_presentaion.weight.WeightScreen
import com.example.onboarding_presentaion.welcome.WelcomeScreen
import com.example.tracker_presentation.tracker_overview.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            CalorieTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController,
                        Route.WELCOME,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.GOAL) {
                            GoalScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.SEARCH) {

                        }

                    }
                }
            }
        }
    }
}
