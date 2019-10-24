package bergco.se.mvvm.fragment.taskgroup.addtaskgroup.impl

import bergco.se.mvvm.extensions.inject
import bergco.se.mvvm.model.TaskGroup
import bergco.se.mvvm.storage.database.TaskGroupDAO

class AddTaskGroupModel {
    suspend fun insertTask(task: TaskGroup) {
        taskGroupDAO.insertTaskGroup(task)
    }

    val taskGroupDAO: TaskGroupDAO by inject()
}