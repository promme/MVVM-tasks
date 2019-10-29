package bergco.se.mvvm.fragment.task.addtask.impl

import bergco.se.mvvm.base.BaseModel
import bergco.se.mvvm.model.Task
import bergco.se.mvvm.storage.database.TasksDAO
import org.koin.standalone.inject

class AddTaskModel : BaseModel() {
    suspend fun insertTask(task: Task) {
        tasksDao.insertTask(task)
    }

    val tasksDao: TasksDAO by inject()
}