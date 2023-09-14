package com.example.core.domain.usecase

/**
 * Created by Abdallah Shehata on 9/11/2023.
 */
class FilterOutDigits {
    operator fun invoke(text:String):String{
        return text.filter { it.isDigit() }
    }
}