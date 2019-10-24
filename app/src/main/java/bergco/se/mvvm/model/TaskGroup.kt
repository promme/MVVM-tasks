package bergco.se.mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "taskGroup")
data class TaskGroup @JvmOverloads constructor(
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "description") var description: String = "",
    @PrimaryKey @ColumnInfo(name = "taskGroupId") var taskGroupId: String = UUID.randomUUID().toString(),
    @Ignore var completed: String = ""
)
