package com.example.core.domain.model

/**
 * Created by Abdallah Shehata on 9/9/2023.
 */
sealed class Gender(val name: String) {

    object Male : Gender(MALE)
    object Female : Gender(FEMALE)
    companion object {
        private const val MALE = "male"
        private const val FEMALE = "female"
        fun fromString(name: String): Gender {
            return when (name) {
                MALE -> Male
                FEMALE -> Female
                else -> Female
            }
        }
    }
}
