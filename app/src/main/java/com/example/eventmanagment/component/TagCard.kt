package com.example.eventmanagment.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventmanagment.iconByName

@Composable
fun TagCard(
    tagName: String,
    tagIcon: String,
    tagNumber: String,
    tagColor: Color,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                onClick.invoke()
            },
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(
            containerColor = tagColor.copy(0.2f)
        )
    )
    {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(65.dp)
                    .background(color = tagColor, RoundedCornerShape(20)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = iconByName(tagIcon) ,
                    contentDescription = "",
                    tint = Color.White
                )
            }

            Text(text = tagName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(
                text = tagNumber, color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }

}