package bergco.se.mvvm.extensions

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import bergco.se.mvvm.MainActivity
import bergco.se.mvvm.base.BaseFragment

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun View.setHeight(height: Int) {
    layoutParams = this.layoutParams.apply { this.height = height }
}

fun Context.goToFragment(fragment: BaseFragment) {
    (this as MainActivity).goToFragment(fragment)
}

inline fun Context.setupToolbar(func: MainActivity.() -> Unit) {
    val mainActivty = this as MainActivity
    mainActivty.resetToolbar()
    mainActivty.func()
}