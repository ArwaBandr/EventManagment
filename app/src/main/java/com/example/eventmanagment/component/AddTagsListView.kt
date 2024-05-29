package com.example.eventmanagment.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.eventmanagment.R
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.getIconName
import com.example.eventmanagment.iconByName
import com.example.eventmanagment.presntation.navigation.Screens
import com.example.eventmanagment.ui.theme.PrimaryColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddTagsListView(
    list: List<Tags>,
    navController: NavHostController,
    onTagClick: (MutableSet<Tags>) -> Unit
) {
    val selectedItems = remember { mutableSetOf<Tags>() }

    Column(
        modifier = Modifier
            .wrapContentSize()
           // .padding(end = 20.dp, start = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.tags),
            modifier = Modifier
                .padding(5.dp),
            style = TextStyle(color = Color.Gray, fontSize = 16.sp)
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )
            {
            list.forEach {
                val isSelected = selectedItems.contains(it)
                Box(
                    modifier = Modifier
                        //.wrapContentSize()
                        .background(
                            Color(
                                it.color.toIntOrNull() ?: Color.Gray.toArgb()
                            ).copy(alpha = 0.20f),
                            shape = RoundedCornerShape(20.dp)
                        )
//                        .clip(
//                            RoundedCornerShape(20.dp)
//                        )
                        .padding(vertical = 2.dp, horizontal = 8.dp)
                        .clickable {
                            if (isSelected) {
                                selectedItems.remove(it)
                            } else {
                                selectedItems.add(it)
                            }
                            onTagClick.invoke(selectedItems)
                        }


                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                    Text(
                        text = it.name,
                        Modifier.padding(8.dp),
                        color = Color(it.color.toIntOrNull() ?: Color.Gray.toArgb())
                    )
                    val icon = iconByName(it.iconName)
                    Icon(imageVector = icon, contentDescription = "")
                }}
            }


    }
    Text(
        text = stringResource(id = R.string.add_new_tag),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable {
                navController.navigate(Screens.MainApp.TagDailog.rout)
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