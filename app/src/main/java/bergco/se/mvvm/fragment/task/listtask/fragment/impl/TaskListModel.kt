package bergco.se.mvvm.fragment.task.listtask.fragment.impl

import bergco.se.mvvm.storage.database.TasksDAO
import bergco.se.mvvm.model.Task
import bergco.se.mvvm.extensions.inject
import bergco.se.mvvm.model.TaskGroup
import bergco.se.mvvm.storage.LocalCacheRepository

class TaskListModel {
    val taskGroupCacheRepository: LocalCacheRepository<TaskGroup> by inject()

    val tasksDao: TasksDAO by inject()

    suspend fun updateTask(task: Task) {
        tasksDao.updateTask(task)
    }

    val observableTaskList = tasksDao.observeTasksByGroupId(taskGroupCacheRepository.get().taskGroupId)

}