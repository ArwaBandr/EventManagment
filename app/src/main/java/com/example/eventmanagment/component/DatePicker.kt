package com.example.eventmanagment.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.eventmanagment.ui.theme.Purple40
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun DatePickerr(navController: NavHostController,onBackPress: () -> Boolean) {
    //navController: NavHostController
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    Column(modifier = Modifier.background(Color.White)) {
        //DaysOfWeekTitle(daysOfWeek = daysOfWeek()) // Use the title here

        WeekCalendar(
            dayContent = {
                var dayes = it.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                val dayesShort = dayes.substring(0, 2).uppercase()

                Text(
                    text = dayesShort.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            })

        HorizontalCalendar(
            state = state,
            dayContent = { day ->
                Day(day, isSelected = selectedDate == day.date) { day ->
                    selectedDate = if (selectedDate == day.date) null else day.date
                }

            }
        )
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Button(onClick = {
                navController.previousBackStackEntry?.savedStateHandle?.set<String>(
                    "selectedDate",
                    selectedDate.toString()
                )
                onBackPress.invoke()
            }, modifier = Modifier.clip(RoundedCornerShape(12.dp))) {
                Text(text = "Save", color = Color.White)
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = {  onBackPress.invoke()}) {
                Text(text = "Cancel", color = Color.White)
            }
        }

    }
}

@Composable
fun Day(day: CalendarDay, isSelected: Boolean, onClick: (CalendarDay) -> Unit) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(color = if (isSelected) Purple40 else Color.Transparent)
            .clickable(
                enabled = day.position == DayPosition.MonthDate,
                onClick = { onClick(day) }
            ), // This is important for square sizing!
        contentAlignment = Alignment.Center
    ) {
        Text(text = day.date.dayOfMonth.toString())
    }
}

//@Composable
//@Preview(showBackground = true)
//fun DatePickerPreview() {
//    DatePickerr()
//}