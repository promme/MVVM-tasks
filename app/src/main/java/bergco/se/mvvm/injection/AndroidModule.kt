package bergco.se.mvvm.injection

import android.content.Context
import org.koin.dsl.module.module

fun androidModule(context: Context) = module { single { context } }