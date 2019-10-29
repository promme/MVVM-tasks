package bergco.se.mvvm.fragment.taskgroup.listtaskgroup.fragment.impl

import bergco.se.mvvm.extensions.inject
import bergco.se.mvvm.model.TaskGroup
import bergco.se.mvvm.storage.LocalCacheRepository
import bergco.se.mvvm.storage.database.TaskGroupDAO
import bergco.se.mvvm.storage.database.TasksDAO

class TaskGroupModel {
    val taskGroupCacheRepository: LocalCacheRepository<TaskGroup> by inject()
    val taskGroupDAO: TaskGroupDAO by inject()

    val observableTaskGroupsAndTasks = taskGroupDAO.observeTaskGroupsAndTasks()
    fun selectTaskGroup(taskGroup: TaskGroup) {
        taskGroupCacheRepository.put(taskGroup)
    }


}