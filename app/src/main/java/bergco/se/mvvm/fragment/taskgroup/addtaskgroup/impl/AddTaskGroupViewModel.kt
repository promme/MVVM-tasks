package bergco.se.mvvm.fragment.taskgroup.addtaskgroup.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import bergco.se.mvvm.base.BaseViewModel
import bergco.se.mvvm.model.TaskGroup
import kotlinx.coroutines.launch

class AddTaskGroupViewModel : BaseViewModel() {
    fun insertTaskGroup(taskGroup: TaskGroup) {
        viewModelScope.launch { addTaskGroupModel.insertTask(taskGroup) }
    }

    fun onSaveClicked() {
        if (verifyFields()) {
            insertTaskGroup(
                TaskGroup(
                    taskGroupName.value!!,
                    taskGroupDescription.value!!
                )
            )
            dismissDialog.postValue(true)
        }
    }

    val addTaskGroupModel = AddTaskGroupModel()

    val taskGroupName = MutableLiveData("")
    val taskGroupDescription = MutableLiveData("")
    val taskGroupNameError = MutableLiveData<String>()
    val taskGroupDescriptionError = MutableLiveData<String>()
    val dismissDialog = MutableLiveData(false)

    fun verifyFields(): Boolean {
        var result = true

        if (taskGroupName.value.isNullOrBlank()) {
            taskGroupNameError.postValue("Cant be empty")
            result = false
        }
        if (taskGroupName.value.isNullOrBlank()) {
            taskGroupDescriptionError.postValue("Cant be empty")
            result = false
        }


        return result
    }

}