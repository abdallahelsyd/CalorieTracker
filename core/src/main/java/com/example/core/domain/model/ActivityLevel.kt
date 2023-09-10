package com.example.core.domain.model

/**
 * Created by Abdallah Shehata on 9/9/2023.
 */
sealed class ActivityLevel(val name: String) {

    object Low : ActivityLevel(LOW)
    object Medium : ActivityLevel(MEDIUM)
    object High : ActivityLevel(HIGH)
    companion object {
        private const val LOW="low"
        private const val MEDIUM="medium"
        private const val HIGH="high"
        fun fromString(name: String): ActivityLevel {
            return when (name) {
                LOW -> Low
                MEDIUM -> Medium
                HIGH -> High
                else -> Medium
            }
        }
    }
}
