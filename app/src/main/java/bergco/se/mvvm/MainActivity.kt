package bergco.se.mvvm

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import bergco.se.mvvm.base.BaseActivity
import bergco.se.mvvm.base.BaseFragment
import bergco.se.mvvm.extensions.gone
import bergco.se.mvvm.extensions.inTransaction
import bergco.se.mvvm.extensions.showIfElseGone
import bergco.se.mvvm.extensions.visible
import bergco.se.mvvm.fragment.taskgroup.listtaskgroup.fragment.TaskGroupFragment
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToFragment(TaskGroupFragment())
    }

    fun goToFragment(fragment: BaseFragment, addToBackstack: Boolean = false) {
        supportFragmentManager.inTransaction {
            if (addToBackstack) addToBackStack(fragment::javaClass.name)
            replace(
                main_content.id,
                fragment,
                fragment::javaClass.name
            )
        }
    }

    fun setToolbarVisibility(visibility: Int) {
        app_bar.visibility = visibility
    }

    fun setToolbarTitle(title: String) {
        tv_toolbar_title.text = title
    }

    fun setupFab(icon: Drawable?, action: (() -> (Unit))? = null) {
        fab.apply {
            visible()
            setImageDrawable(icon)
            setOnClickListener {
                Timber.d("***OLLE fab clicked")
                action?.invoke()
            }
        }
    }

    fun setToolbarLeftIcon(icon: Drawable?, action: (() -> (Unit))? = null) {
        iv_toolbar_left_icon.apply {
            setImageDrawable(icon)
            setOnClickListener { action?.invoke() }
        }
    }

    fun setToolbarRightIcon(icon: Drawable?, action: (() -> (Unit))? = null) {
        iv_toolbar_right_icon.apply {
            setImageDrawable(icon)
            setOnClickListener { action?.invoke() }
            showIfElseGone(icon != null)
        }
    }

    fun resetToolbar() {
        fab.gone()
        setToolbarVisibility(View.VISIBLE)
        setToolbarLeftIcon(null)
        setToolbarRightIcon(null)
        setToolbarTitle("")
    }
}
