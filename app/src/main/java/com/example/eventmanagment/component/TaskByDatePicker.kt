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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventmanagment.R
import com.example.eventmanagment.presntation.screens.task.FilterTasksViewModel
import com.example.eventmanagment.ui.theme.Navy
import com.example.eventmanagment.ui.theme.Purple40
import com.example.eventmanagment.ui.theme.textcolor
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun TaskByDatePicker(filterTasksViewModel: FilterTasksViewModel) {
    val currentDate = remember { LocalDate.now() }
    val currentMonth = remember { YearMonth.now() }
    val startDate = remember { currentMonth.minusMonths(100).atStartOfMonth() }
    val endDate = remember { currentMonth.plusMonths(100).atEndOfMonth() }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val state = rememberWeekCalendarState(
        startDate = startDate,
        endDate = endDate,
        firstVisibleWeekDate = currentDate,
        firstDayOfWeek = firstDayOfWeek
    )

        var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

        WeekCalendar(
            state = state,
            weekHeader = {
                Column {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Task",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(start = 4.dp),
                            color = textcolor,
                            fontWeight = FontWeight.Bold
                        )
                        Row(modifier = Modifier.padding(5.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.calendar),
                                contentDescription = "",
                                modifier = Modifier.padding(end = 2.dp, top = 3.dp)
                            )
                            Text(
                                text = "${
                                    it.days.first().date.yearMonth.month.getDisplayName(
                                        TextStyle.FULL,
                                        Locale.getDefault()
                                    )
                                } ${state.lastVisibleWeek.days.first().date.year}",
                                color = Color.Gray
                            )
                        }

                    }}
               },
            dayContent = { day ->
                Day(day, isSelected = selectedDate == day.date) { day ->
                    selectedDate = if (selectedDate == day) null else day
                    filterTasksViewModel.filterTaskByDate(day.toString())
                }

            }
        )
    }



@Composable
fun Day(day: WeekDay, isSelected: Boolean,onClick: (LocalDate) -> Unit) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(color = if (isSelected) Purple40 else Color.Transparent)
            .clickable(
                // enabled = day.position == DayPosition.MonthDate,
                onClick = {  onClick.invoke(day.date)}
            ), // This is important for square sizing!
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // val daysOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.THURSDAY)
            var dayes = day.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            val dayesShort = dayes.substring(0, 2).uppercase()

            Text(
                text = dayesShort.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = day.date.dayOfMonth.toString(), textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}
//
//@Composable
//fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
//    Row(modifier = Modifier.fillMaxWidth()) {
//        for (dayOfWeek in daysOfWeek) {
//            Text(
//                modifier = Modifier.weight(1f),
//                textAlign = TextAlign.Center,
//                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
//            )
//        }
//    }
//}

//@Composable
//@Preview(showBackground = true)
//fun TaskDatePickerPreview() {
//    TaskByDatePicker()
//}