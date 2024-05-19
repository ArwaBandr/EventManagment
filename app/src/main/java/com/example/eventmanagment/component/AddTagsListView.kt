package com.example.eventmanagment.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.ui.theme.PrimaryColor

@Composable
fun AddTagsListView(list: List<Tags>, onTagClick: (Tags) -> Unit) {

    Column(modifier = Modifier
        .wrapContentSize()
        .padding(end = 20.dp, start = 20.dp)) {
        Text(
            text = "Tags",
            style = TextStyle(color = Color.Gray, fontSize = 16.sp)
        )
        LazyRow(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(10.dp)
            , horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(list) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(
                            Color(
                                it.color.toIntOrNull() ?: Color.Gray.toArgb()
                            ).copy(alpha = 0.20f)
                        )
                        .clip(
                            RoundedCornerShape(20.dp)
                        )
                        .clickable {
                            onTagClick.invoke(it)
                        }


                ) {
                    Text(
                        text = it.name,
                        Modifier.padding(3.dp),
                        color = Color(it.color.toIntOrNull() ?: Color.Gray.toArgb())
                    )
                }
            }


        }
        Text(
            text = "+ Add new tag",
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clickable {

                },
            color = PrimaryColor,
            textAlign = TextAlign.Center
        )
    }
}

//@Composable
//@Preview
//fun pp() {
//    AddTagsListView()
//}