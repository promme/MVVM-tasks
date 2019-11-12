package bergco.se.mvvm.fragment.taskgroup.listtaskgroup.fragment.impl

import bergco.se.mvvm.base.BaseModel
import bergco.se.mvvm.storage.database.TaskGroupDAO
import org.koin.standalone.inject

class TaskGroupModel : BaseModel() {
    val taskGroupDAO: TaskGroupDAO by inject()

    val observableTaskGroupsAndTasks = taskGroupDAO.observeTaskGroupsAndTasks()
}