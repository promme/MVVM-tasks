package bergco.se.mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import bergco.se.mvvm.fragment.taskgroup.addtaskgroup.impl.AddTaskGroupViewModel
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

class AddTaskGroupViewModelTest {
    val addTaskGroupViewModel = AddTaskGroupViewModel()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun testNameCantBeEmpty() {
        addTaskGroupViewModel.taskGroupDescription.postValue("Non empty value")
        addTaskGroupViewModel.onSaveClicked()

        assertEquals("Cant be empty", addTaskGroupViewModel.taskGroupNameError.value)
    }

    @Test
    fun testDescriptionCantBeEmpty() {
        addTaskGroupViewModel.taskGroupName.postValue("Non empty value")
        addTaskGroupViewModel.onSaveClicked()

        assertEquals("Cant be empty", addTaskGroupViewModel.taskGroupDescriptionError.value)
    }

    @Test
    fun testDialogDismissed() {
        addTaskGroupViewModel.taskGroupName.postValue("Non empty value")
        addTaskGroupViewModel.taskGroupDescription.postValue("Non empty value")
        addTaskGroupViewModel.onSaveClicked()

        assertEquals(true, addTaskGroupViewModel.dismissDialog.value)
    }
}
