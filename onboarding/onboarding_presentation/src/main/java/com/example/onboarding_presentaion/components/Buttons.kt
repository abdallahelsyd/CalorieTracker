package com.example.onboarding_presentaion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

@Composable
fun SelectableButton(
    text: String,
    isSelected: Boolean,
    color: Color,
    selectedTextColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .border(
                width = 2.dp,
                color = color,
                shape = RoundedCornerShape(100.dp)
            )
            .background(
                color = if (isSelected) color else Color.Transparent,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable {
                onClick()
            }
            .padding(LocalSpacing.current.spaceMedium)
    ) {
        Text(
            text = text,
            style = textStyle,
            color = if(isSelected) selectedTextColor else color,
        )
    }
}