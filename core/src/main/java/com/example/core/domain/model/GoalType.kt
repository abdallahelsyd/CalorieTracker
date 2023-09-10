package com.example.core.domain.model

/**
 * Created by Abdallah Shehata on 9/9/2023.
 */
sealed class GoalType(val name: String) {

    object LoseWeight : GoalType(LOSE_WEIGHT)
    object KeepWeight : GoalType(KEEP_WEIGHT)
    object GainWeight : GoalType(GAIN_WEIGHT)
    companion object {
        private const val LOSE_WEIGHT="lose_weight"
        private const val KEEP_WEIGHT="keep_weight"
        private const val GAIN_WEIGHT="gain_weight"
        fun fromString(name: String): GoalType {
            return when (name) {
                LOSE_WEIGHT -> LoseWeight
                KEEP_WEIGHT -> KeepWeight
                GAIN_WEIGHT -> GainWeight
                else -> KeepWeight
            }
        }
    }
}
