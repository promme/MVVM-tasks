package bergco.se.mvvm.injection

import androidx.room.Room
import bergco.se.mvvm.model.TaskGroup
import bergco.se.mvvm.storage.LocalCacheRepository
import bergco.se.mvvm.storage.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module


val storageModule = module {
    val db = Room.databaseBuilder(
        androidContext(),
        AppDatabase::class.java, "database-name"
    ).build()
    single { db.tasksDao() }
    single { db.tasksGroupDao() }
    single { LocalCacheRepository<TaskGroup>() }
}