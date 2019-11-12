package bergco.se.mvvm.fragment.task.listtask.fragment.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bergco.se.mvvm.model.Task
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {
    val taskListModel = TaskListModel()
    val onItemCompleteChangedListener: (Task) -> (Unit) =
        { task -> viewModelScope.launch { taskListModel.updateTask(task) } }

    fun observeTaskList(id: String): LiveData<List<Task>> {
        return taskListModel.observeTaskList(id)
    }


}