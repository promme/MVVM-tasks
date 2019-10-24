package bergco.se.mvvm.storage.database

import androidx.lifecycle.LiveData
import androidx.room.*
import bergco.se.mvvm.model.TaskGroup
import bergco.se.mvvm.model.TaskGroupAndTasks

/**
 * Data Access Object for the tasks table.
 */
@Dao
interface TaskGroupDAO {
    @Query("SELECT * FROM taskGroup")
    suspend fun getTasks(): List<TaskGroup>

    @Query("SELECT * FROM taskGroup")
    fun observeTaskGroups(): LiveData<List<TaskGroup>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskGroup(task: TaskGroup)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: TaskGroup): Int

    @Transaction
    @Query("SELECT * FROM taskGroup")
    fun observeTaskGroupsAndTasks(): LiveData<List<TaskGroupAndTasks>>

}
