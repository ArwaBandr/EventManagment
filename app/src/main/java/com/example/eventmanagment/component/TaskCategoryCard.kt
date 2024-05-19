package com.example.eventmanagment.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventmanagment.R
import com.example.eventmanagment.data.entity.TaskType


@Composable
fun TaskCategeryCard(
    height: Dp = 180.dp,
    title: String,
    tintColor: Color,
    totalTasksInCategory: String,
    onClick: () -> Unit,
    image: @Composable () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clickable { onClick.invoke() }
        .shadow(elevation = 22.dp, shape = RoundedCornerShape(16.dp), spotColor = tintColor),
        colors = CardDefaults.cardColors(containerColor = tintColor)) {
        Box(modifier = Modifier.fillMaxWidth().height(height).padding(10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(tintColor.copy(alpha =0.30f )),
                modifier = Modifier.align(Alignment.TopEnd).size(64.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow___right_small),
                contentDescription = "",
                modifier = Modifier.align(Alignment.TopEnd).padding(vertical = 5.dp, horizontal = 5.dp)
            )
            Column(
                modifier = Modifier.fillMaxHeight().padding(vertical = 10.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                image.invoke()
                Text(
                    text = title,
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${totalTasksInCategory} tasks")
            }
        }
    }
}