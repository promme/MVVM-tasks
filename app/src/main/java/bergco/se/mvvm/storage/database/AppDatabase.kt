package bergco.se.mvvm.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import bergco.se.mvvm.model.Task
import bergco.se.mvvm.model.TaskGroup

@Database(entities = [Task::class, TaskGroup::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDAO
    abstract fun tasksGroupDao(): TaskGroupDAO
}