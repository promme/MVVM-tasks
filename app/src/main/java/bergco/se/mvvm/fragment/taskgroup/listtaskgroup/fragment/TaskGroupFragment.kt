package bergco.se.mvvm.fragment.taskgroup.listtaskgroup.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import bergco.se.mvvm.R
import bergco.se.mvvm.base.BaseFragment
import bergco.se.mvvm.extensions.goToFragment
import bergco.se.mvvm.extensions.lazyActivityBoundViewModel
import bergco.se.mvvm.extensions.lazyViewModel
import bergco.se.mvvm.extensions.setupToolbar
import bergco.se.mvvm.fragment.task.listtask.fragment.TaskListFragment
import bergco.se.mvvm.fragment.taskgroup.addtaskgroup.AddTaskGroupFragment
import bergco.se.mvvm.fragment.taskgroup.listtaskgroup.adapter.TaskGroupAdapter
import bergco.se.mvvm.fragment.taskgroup.listtaskgroup.fragment.impl.TaskGroupViewModel
import bergco.se.mvvm.model.TaskGroup
import bergco.se.mvvm.sharedviewmodels.TaskGroupSharedViewModel
import kotlinx.android.synthetic.main.fragment_task_group.*
import timber.log.Timber

class TaskGroupFragment : BaseFragment(R.layout.fragment_task_group) {
    val taskGroupViewModel: TaskGroupViewModel by lazyViewModel()
    val taskGroupSharedViewModel: TaskGroupSharedViewModel by lazyActivityBoundViewModel()

    private val onTaskGroupSelected: (TaskGroup) -> Unit =
        { taskgroup ->
            taskGroupSharedViewModel.onTaskGroupSelected(taskgroup)
            context?.goToFragment(TaskListFragment(), true)
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskGroupAdapter = TaskGroupAdapter(onTaskGroupSelected)
        rv_task_group_list.adapter = taskGroupAdapter
        taskGroupViewModel.observableTaskGroupsAndTasks.observe(
            this,
            Observer {
                taskGroupAdapter.submitList(it.toMutableList())
            })
    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            it.setupToolbar {
                setToolbarTitle(getString(R.string.fragment_task_group_title))
                setupFab(ContextCompat.getDrawable(it, R.drawable.ic_add)) {
                    Timber.d("***OLLE onclicked called")
                    val addTaskGroupFragment = AddTaskGroupFragment()
                    addTaskGroupFragment.show(
                        it.supportFragmentManager,
                        addTaskGroupFragment.tag
                    )

                }
            }
        }
    }

}