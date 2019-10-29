package bergco.se.mvvm

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import bergco.se.mvvm.model.Task
import bergco.se.mvvm.model.TaskGroup
import bergco.se.mvvm.storage.database.AppDatabase
import bergco.se.mvvm.storage.database.TaskGroupDAO
import bergco.se.mvvm.storage.database.TasksDAO
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TaskGroupDAOTest {
    private lateinit var tasksDAO: TasksDAO
    private lateinit var taskGroupDAO: TaskGroupDAO
    private lateinit var db: AppDatabase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        tasksDAO = db.tasksDao()
        taskGroupDAO = db.tasksGroupDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun testTaskGroupAndTasks() {
        runBlocking {
            taskGroupDAO.insertTaskGroup(
                TaskGroup(
                    title = "test",
                    description = "test",
                    taskGroupId = "test"
                )
            )
            tasksDAO.insertTask(
                Task(
                    title = "test",
                    description = "test",
                    groupId = "test"
                )
            )
            tasksDAO.insertTask(
                Task(
                    title = "test",
                    description = "test",
                    groupId = "test"
                )
            )
        }
        taskGroupDAO.observeTaskGroupsAndTasks()
            .observeForever {
                assertEquals(1, it.size)
                assertEquals(2, it.first().tasks!!.size)
            }
    }
}