package com.example.onboarding_presentaion.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.core_ui.LocalSpacing

/**
 * Created by Abdallah Shehata on 9/9/2023.
 */

@Composable
fun ActionButton(
    text:String,
    onClick:()->Unit,
    modifier: Modifier,
    isEnable:Boolean=true,
    textStyle: TextStyle =MaterialTheme.typography.button
){
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnable,
        shape = RoundedCornerShape(100.dp)
    ){
        Text(
            text=text,
            style = textStyle,
            color = MaterialTheme.colors.onPrimary,
            modifier=Modifier.padding(LocalSpacing.current.spaceSmall)
        )
    }
}