package bergco.se.mvvm

import android.app.Application
import bergco.se.mvvm.injection.androidModule
import bergco.se.mvvm.injection.storageModule
import bergco.se.mvvm.timber.LinkingDebugTree
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class TaskApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(LinkingDebugTree())
        }

        startKoin(this@TaskApplication, listOf(androidModule(this), storageModule))
    }
}