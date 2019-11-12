package bergco.se.mvvm.fragment.taskgroup.listtaskgroup.fragment.impl

import bergco.se.mvvm.base.BaseViewModel

class TaskGroupViewModel : BaseViewModel() {
    val taskGroupModel = TaskGroupModel()
    val observableTaskGroupsAndTasks = taskGroupModel.observableTaskGroupsAndTasks
}