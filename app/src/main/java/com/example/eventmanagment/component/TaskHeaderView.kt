package com.example.eventmanagment.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventmanagment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskHeaderView(title: String,onBackClicked: () -> Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier
                .weight(0.18f)
                .padding(7.dp),
            shape = RoundedCornerShape(20),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(16.dp),
            onClick = {
                onBackClicked.invoke()
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = title,
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth()
                .padding(end = 60.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp, color= Color.Black,
            textAlign = TextAlign.Center
        )
    }
}
