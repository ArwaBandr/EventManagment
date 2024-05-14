package com.example.eventmanagment.room

import androidx.test.filters.SmallTest
import com.example.eventmanagment.data.dao.TaskDao
import com.example.eventmanagment.data.database.EventDatabase
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.data.entity.Task
import com.example.eventmanagment.data.entity.TaskType
import com.example.eventmanagment.data.entity.TaskWithTagLists
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class TaskDaoTest {
    @get:Rule
     var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: EventDatabase
    private lateinit var taskDao: TaskDao

   val task = Task(
       taskId = 1,
    title = "title",
    description = "description",
    date = "2022-2-02",
    taskType = TaskType.Cancelled.type,
    timeFrom = "10:20",
    timeTo = "12:10",
    tagName = "Work"
    )
    val task2 = Task(
        taskId = 2,
        title = "title",
        description = "description",
        date = java.time.LocalDate.now().toString(),
        taskType = TaskType.OnGoing.type,
        timeFrom = "10:20",
        timeTo = "12:10",
        tagName = "Work"
    )
    @Before
    fun setup(){
        hiltRule.inject()
        taskDao =database.taskDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertTask()= runTest {

        taskDao.addTask(task)
        val allTasks =taskDao.getAllTask().first()
        assert(allTasks.contains(task))
    }

    @Test
    fun deletTask() = runTest {
        taskDao.addTask(task)
        taskDao.deleteTask(task)
        val allTasks =taskDao.getAllTask().first()
        assert(!allTasks.contains(task))
    }

    @Test
    fun getAllTasks()= runTest {
        val task = Task(
            taskId = 1,
            title = "title",
            description = "description",
            date = "2022-2-02",
            taskType = TaskType.Cancelled.type,
            timeFrom = "10:20",
            timeTo = "12:10",
            tagName = "Work"

        )
        val task2 = Task(
            taskId = 2,
            title = "title",
            description = "description",
            date = java.time.LocalDate.now().toString(),
            taskType = TaskType.Cancelled.type,
            timeFrom = "10:20",
            timeTo = "12:10",
            tagName = "Work"

        )
        taskDao.addTask(task)
        taskDao.addTask(task2)
        val alltest =taskDao.getAllTask().first()
        assert(alltest == listOf(task,task2))

    }

    @Test
    fun upsertTag()= runTest {
        val tag = Tags(
            "Work",
            "color"
        )

        taskDao.upsertTag(tag)
        val allTags =taskDao.getAllTags().first()
        assert(allTags.contains(tag))
    }

    @Test
    fun deleteTag()= runTest {
        val tag = Tags(
            "Work",
            "color"
        )
        taskDao.upsertTag(tag)
        taskDao.deletTag(tag)
        val allTag =taskDao.getAllTags().first()
        assert(allTag.isEmpty())
    }

    @Test
    fun getAllTags()= runTest {
        val tag = Tags(
            "Personal",
            "color"
        )
        val tag2 = Tags(
            "Work",
            "color"
        )

        taskDao.upsertTag(tag)
        taskDao.upsertTag(tag2)
        val getAllTags =taskDao.getAllTags().first()
        assert(getAllTags == listOf(tag,tag2))

    }

    @Test
    fun getTagsWithTask() = runTest {
        val tag = Tags(
            "Personal",
            "color"
        )
        val tag2 = Tags(
            "Work",
            "color"
        )
        val task = Task(
            taskId = 1,
            title = "title",
            description = "description",
            date = java.time.LocalDate.now().toString(),
            taskType = TaskType.Cancelled.type,
            timeFrom = "10:20",
            timeTo = "12:10",
            tagName = "Work"

        )
        val task2 = Task(
            taskId = 2 ,
            title = "title",
            description = "description",
            date = java.time.LocalDate.now().toString(),
            taskType = TaskType.Cancelled.type,
            timeFrom = "10:20",
            timeTo = "12:10",
            tagName = "Work"
        )
        taskDao.upsertTag(tag)
        taskDao.upsertTag(tag2)
        taskDao.addTask(task)
        taskDao.addTask(task2)
        val gettaskWithTags =taskDao.getTagsWithTask("Work").first()
        val expect = listOf(TaskWithTagLists(tag2, listOf(task,task2)))
        assert(gettaskWithTags ==expect)

    }
}