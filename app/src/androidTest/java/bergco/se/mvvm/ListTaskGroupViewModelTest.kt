package bergco.se.mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import bergco.se.mvvm.fragment.taskgroup.listtaskgroup.fragment.impl.TaskGroupViewModel
import bergco.se.mvvm.model.TaskGroup
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ListTaskGroupViewModelTest {
    val taskGroupViewModel = TaskGroupViewModel()
    val taskGroupCacheRepository = taskGroupViewModel.taskGroupModel.taskGroupCacheRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun testCanSelectTaskGroup() {
        val taskGroup = TaskGroup("123", "123")
        taskGroupViewModel.onTaskGroupSelected(taskGroup)
        assertEquals(taskGroup, taskGroupCacheRepository.get())
    }
}
