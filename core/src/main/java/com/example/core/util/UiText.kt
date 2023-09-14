package com.example.core.util

import android.content.Context

/**
 * Created by Abdallah Shehata on 9/11/2023.
 */
sealed class UiText {
    data class DynamicString(val msg:String):UiText()
    data class StringResource(val resId:Int):UiText()
    fun asString(context: Context):String{
        return when(this){
            is DynamicString -> msg
            is StringResource -> context.getString(resId)
        }
    }
}