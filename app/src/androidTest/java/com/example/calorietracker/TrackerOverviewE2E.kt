package com.example.calorietracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.calorietracker.repository.TrackerRepositoryFake
import com.example.calorietracker.ui.navigation.Route
import com.example.calorietracker.ui.theme.CalorieTrackerTheme
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.usecase.FilterOutDigits
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.useCase.CalculateMealNutrients
import com.example.tracker_domain.useCase.DeleteTrackedFood
import com.example.tracker_domain.useCase.GetFoodsForDate
import com.example.tracker_domain.useCase.SearchFood
import com.example.tracker_domain.useCase.TrackFood
import com.example.tracker_domain.useCase.TrackerUseCases
import com.example.tracker_presentation.search.SearchScreen
import com.example.tracker_presentation.search.SearchViewModel
import com.example.tracker_presentation.tracker_overview.TrackerOverviewScreen
import com.example.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.roundToInt

/**
 * Created by Abdallah Shehata on 10/4/2023.
 */

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@HiltAndroidTest
class TrackerOverviewE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var fakeRepository: TrackerRepositoryFake
    private lateinit var trackerUseCases: TrackerUseCases
    private lateinit var preferences: Preferences
    private lateinit var trackerOverviewViewModel: TrackerOverviewViewModel
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var navController: NavController

    @Before
    fun setup() {
        preferences = mockk(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        fakeRepository= TrackerRepositoryFake()
        trackerUseCases= TrackerUseCases(
            trackFood = TrackFood(fakeRepository),
            searchFood = SearchFood(fakeRepository),
            getFoodsForDate = GetFoodsForDate(fakeRepository),
            deleteTrackedFood = DeleteTrackedFood(fakeRepository),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
        trackerOverviewViewModel = TrackerOverviewViewModel(
            preferences = preferences,
            trackerUseCases = trackerUseCases
        )
        searchViewModel = SearchViewModel(
            trackerUseCases = trackerUseCases,
            filterOutDigits = FilterOutDigits()
        )

        composeRule.setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController,
                        Route.TRACKER_OVERVIEW,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(
                                onNavigateToSearch = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.SEARCH + "/$mealName" +
                                                "/$day" +
                                                "/$month" +
                                                "/$year"
                                    )
                                },trackerOverviewViewModel
                            )
                        }
                        composable(
                            route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") {
                                    type = NavType.StringType
                                },
                                navArgument("dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument("month") {
                                    type = NavType.IntType
                                },
                                navArgument("year") {
                                    type = NavType.IntType
                                },
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!
                            SearchScreen(
                                scaffoldState = scaffoldState,
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                onNavigateUp = {
                                    navController.navigateUp()
                                },searchViewModel
                            )
                        }
                    }
                }

            }
        }
    }

    @Test
    fun addBreakfast_appearsUnderBreakFast_nutrientsProperlyCalculated(){
        fakeRepository.searchResult= listOf(
            TrackableFood(
                name = "banana",
                imageUrl = null,
                caloriesPer100g = 150,
                proteinPer100g = 5,
                carbsPer100g = 50,
                fatPer100g = 1
            )
        )
        val addedAmount = 150
        val expectedCalories = (1.5f * 150).roundToInt()
        val expectedCarbs = (1.5f * 50).roundToInt()
        val expectedProtein = (1.5f * 5).roundToInt()
        val expectedFat = (1.5f * 1).roundToInt()

        composeRule.onNodeWithText("Add Breakfast")
            .assertDoesNotExist()
        composeRule.onNodeWithContentDescription("Breakfast")
            .performClick()
        composeRule.onNodeWithText("Add Breakfast")
            .assertIsDisplayed()


        composeRule.onNodeWithText("Add Breakfast")
            .performClick()

        assertThat(
            navController.currentDestination?.route?.startsWith(Route.SEARCH)
        ).isTrue()


        composeRule.onNodeWithTag("search_textField")
            .performTextInput("banana")
        composeRule.onNodeWithContentDescription("Search...")
            .performClick()
        composeRule.onNodeWithText("Carbs")
            .performClick()

        composeRule.onNodeWithContentDescription("Amount")
            .performTextInput(addedAmount.toString())

        composeRule.onNodeWithContentDescription("Track")
            .performClick()


        assertThat(navController
            .currentDestination?.route?.startsWith(Route.TRACKER_OVERVIEW)
        ).isTrue()

        composeRule.onAllNodesWithText(expectedCarbs.toString())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedProtein.toStr())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedFat.toStr())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedCalories.toStr())
            .onFirst()
            .assertIsDisplayed()
    }

}