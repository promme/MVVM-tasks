package bergco.se.mvvm.fragment.taskgroup.listtaskgroup.fragment.impl

import bergco.se.mvvm.base.BaseModel
import bergco.se.mvvm.model.TaskGroup
import bergco.se.mvvm.storage.LocalCacheRepository
import bergco.se.mvvm.storage.database.TaskGroupDAO
import org.koin.standalone.inject

class TaskGroupModel : BaseModel() {
    val taskGroupCacheRepository: LocalCacheRepository<TaskGroup> by inject()
    val taskGroupDAO: TaskGroupDAO by inject()

    val observableTaskGroupsAndTasks = taskGroupDAO.observeTaskGroupsAndTasks()
    fun selectTaskGroup(taskGroup: TaskGroup) {
        taskGroupCacheRepository.put(taskGroup)
    }


}