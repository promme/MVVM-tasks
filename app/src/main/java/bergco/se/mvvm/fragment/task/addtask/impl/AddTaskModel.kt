package bergco.se.mvvm.fragment.task.addtask.impl

import bergco.se.mvvm.storage.database.TasksDAO
import bergco.se.mvvm.extensions.inject
import bergco.se.mvvm.model.Task

class AddTaskModel {
    suspend fun insertTask(task: Task) {
        tasksDao.insertTask(task)
    }

    val tasksDao: TasksDAO by inject()
}