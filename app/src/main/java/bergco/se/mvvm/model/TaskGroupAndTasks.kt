package bergco.se.mvvm.model

import androidx.room.Embedded
import androidx.room.Relation

class TaskGroupAndTasks {
    @Embedded
    var taskGroup: TaskGroup? = null
    @Relation(parentColumn = "taskGroupId",
    entityColumn = "groupId")
    var tasks: List<Task>? = null
}