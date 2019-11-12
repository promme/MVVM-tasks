package bergco.se.mvvm.sharedviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bergco.se.mvvm.base.BaseViewModel
import bergco.se.mvvm.model.TaskGroup

class TaskGroupSharedViewModel : BaseViewModel() {
    private val _taskGroupIdLiveData = MutableLiveData<String>()
    val taskGroupIdLiveData: LiveData<String> = _taskGroupIdLiveData

    fun onTaskGroupSelected(taskgroup: TaskGroup) {
        _taskGroupIdLiveData.postValue(taskgroup.taskGroupId)
    }
}