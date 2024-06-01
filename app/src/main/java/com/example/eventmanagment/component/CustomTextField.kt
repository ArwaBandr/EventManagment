package com.example.eventmanagment.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    label: String,
    textColor: Color,
    value: MutableState<String>,
    trailingIcon: @Composable() (() -> Unit)? = null,
    isReadOnly: Boolean
) {
    Column(modifier = modifier
        .padding(vertical = 12.dp)
        ){
        Text(
            text = label,
            Modifier.padding(end = 20.dp, start = 20.dp),
            style = TextStyle(color = textColor, fontSize = 16.sp)
        )
        TextField( value =value.value, onValueChange = {value.value=it}, modifier = Modifier
            .padding(horizontal = 6.dp)
            .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray,
                disabledContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                cursorColor = textColor,
                focusedIndicatorColor = textColor,
                focusedLabelColor = textColor
            ),
            readOnly = isReadOnly,
        trailingIcon ={trailingIcon?.invoke()},)
    }
}
//if (trailingIcon ==null) false else true