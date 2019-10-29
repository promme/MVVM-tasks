package bergco.se.mvvm.fragment.taskgroup.listtaskgroup.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bergco.se.mvvm.R
import bergco.se.mvvm.base.BaseFragment
import bergco.se.mvvm.extensions.goToFragment
import bergco.se.mvvm.extensions.setupToolbar
import bergco.se.mvvm.fragment.task.listtask.fragment.TaskListFragment
import bergco.se.mvvm.fragment.taskgroup.addtaskgroup.AddTaskGroupFragment
import bergco.se.mvvm.fragment.taskgroup.listtaskgroup.adapter.TaskGroupAdapter
import bergco.se.mvvm.fragment.taskgroup.listtaskgroup.fragment.impl.TaskGroupViewModel
import bergco.se.mvvm.model.TaskGroup
import kotlinx.android.synthetic.main.fragment_task_group.*

class TaskGroupFragment : BaseFragment(R.layout.fragment_task_group) {
    private lateinit var taskGroupViewModel: TaskGroupViewModel
    private val onTaskGroupSelected: (TaskGroup) -> Unit =
        { taskgroup ->
            taskGroupViewModel.onTaskGroupSelected(taskgroup)
            context?.goToFragment(TaskListFragment())
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskGroupViewModel = ViewModelProvider(this).get(TaskGroupViewModel::class.java)
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
                    val addTaskGroupFragment = AddTaskGroupFragment()
                    addTaskGroupFragment.show(
                        it.supportFragmentManager,
                        AddTaskGroupFragment::class.java.name
                    )
                }
            }
        }
    }

}