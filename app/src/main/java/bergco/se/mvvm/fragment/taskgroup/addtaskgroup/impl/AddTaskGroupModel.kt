package bergco.se.mvvm.fragment.taskgroup.addtaskgroup.impl

import bergco.se.mvvm.base.BaseModel
import bergco.se.mvvm.model.TaskGroup
import bergco.se.mvvm.storage.database.TaskGroupDAO
import org.koin.standalone.inject

class AddTaskGroupModel : BaseModel() {
    suspend fun insertTask(task: TaskGroup) {
        taskGroupDAO.insertTaskGroup(task)
    }

    val taskGroupDAO: TaskGroupDAO by inject()
}