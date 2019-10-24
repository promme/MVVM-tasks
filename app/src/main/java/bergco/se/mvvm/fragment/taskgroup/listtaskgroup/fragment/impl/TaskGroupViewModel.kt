package bergco.se.mvvm.fragment.taskgroup.listtaskgroup.fragment.impl

import bergco.se.mvvm.base.BaseViewModel
import bergco.se.mvvm.model.TaskGroup

class TaskGroupViewModel : BaseViewModel() {
    val taskGroupModel = TaskGroupModel()
    val observableTaskGroupsAndTasks = taskGroupModel.observableTaskGroupsAndTasks

    fun onTaskGroupSelected(taskGroup: TaskGroup) {
        taskGroupModel.selectTaskGroup(taskGroup)
    }
}