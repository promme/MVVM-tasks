package bergco.se.mvvm.fragment.task.listtask.fragment.impl

import androidx.lifecycle.LiveData
import bergco.se.mvvm.base.BaseModel
import bergco.se.mvvm.model.Task
import bergco.se.mvvm.storage.database.TasksDAO
import org.koin.standalone.inject

class TaskListModel : BaseModel() {
    val tasksDao: TasksDAO by inject()

    suspend fun updateTask(task: Task) {
        tasksDao.updateTask(task)
    }

    fun observeTaskList(id: String): LiveData<List<Task>> {
        return tasksDao.observeTasksByGroupId(id)
    }

}