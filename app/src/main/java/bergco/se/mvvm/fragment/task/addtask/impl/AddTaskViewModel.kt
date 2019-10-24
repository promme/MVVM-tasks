package bergco.se.mvvm.fragment.task.addtask.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import bergco.se.mvvm.base.BaseViewModel
import bergco.se.mvvm.extensions.inject
import bergco.se.mvvm.model.Task
import bergco.se.mvvm.model.TaskGroup
import bergco.se.mvvm.storage.LocalCacheRepository
import kotlinx.coroutines.launch

class AddTaskViewModel : BaseViewModel() {
    fun insertTask(task: Task) {
        viewModelScope.launch { addTaskModel.insertTask(task) }
    }

    val taskGroupLocalCacheRepository: LocalCacheRepository<TaskGroup> by inject()
    val addTaskModel = AddTaskModel()

    fun onSaveClicked() {
        if (verifyFields()) {
            insertTask(
                Task(
                    taskGroupLocalCacheRepository.get().taskGroupId,
                    taskName.value!!,
                    taskDescription.value!!,
                    false
                )
            )
            dismissDialog.postValue(true)
        }
    }

    val taskName = MutableLiveData("")
    val taskDescription = MutableLiveData("")
    val taskNameError = MutableLiveData<String>()
    val taskDescriptionError = MutableLiveData<String>()
    val dismissDialog = MutableLiveData(false)

    fun verifyFields(): Boolean {
        var result = true

        if (taskName.value.isNullOrBlank()) {
            taskNameError.postValue("Cant be empty")
            result = false
        }
        if (taskDescription.value.isNullOrBlank()) {
            taskDescriptionError.postValue("Cant be empty")
            result = false
        }


        return result
    }

}