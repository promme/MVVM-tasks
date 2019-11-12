package bergco.se.mvvm.extensions

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
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

fun Context.goToFragment(fragment: BaseFragment, addToBackstack: Boolean = false) {
    (this as MainActivity).goToFragment(fragment, addToBackstack)
}

inline fun Context.setupToolbar(func: MainActivity.() -> Unit) {
    val mainActivty = this as MainActivity
    mainActivty.resetToolbar()
    mainActivty.func()
}

inline fun <reified T : ViewModel> Fragment.lazyViewModel(lifecycleOwner: ViewModelStoreOwner = this) =
    lazy {
        ViewModelProvider(lifecycleOwner).get(T::class.java)
    }


inline fun <reified T : ViewModel> Fragment.lazyActivityBoundViewModel() =
    lazy {
        activity?.run { ViewModelProvider(this).get(T::class.java) }
            ?: throw IllegalStateException("Invalid Activity")
    }
