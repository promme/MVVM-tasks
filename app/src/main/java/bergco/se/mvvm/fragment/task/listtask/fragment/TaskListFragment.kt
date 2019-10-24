package bergco.se.mvvm.fragment.task.listtask.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bergco.se.mvvm.R
import bergco.se.mvvm.base.BaseFragment
import bergco.se.mvvm.extensions.setupToolbar
import bergco.se.mvvm.fragment.task.addtask.AddTaskFragment
import bergco.se.mvvm.fragment.task.listtask.adapter.TaskAdapter
import bergco.se.mvvm.fragment.task.listtask.fragment.impl.TaskListViewModel
import bergco.se.mvvm.fragment.taskgroup.addtaskgroup.AddTaskGroupFragment
import bergco.se.mvvm.model.Task
import kotlinx.android.synthetic.main.fragment_task.*

class TaskListFragment : BaseFragment(R.layout.fragment_task) {
    private lateinit var taskListViewModel:
            TaskListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskListViewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskAdapter = TaskAdapter(taskListViewModel.onItemCompleteChangedListener)
        rv_tasks.adapter = taskAdapter
        taskListViewModel.observableTaskList.observe(
            this, Observer<List<Task>> {
                //Only update when items are added/removed
                if (it.size != taskAdapter.itemCount) {
                    taskAdapter.submitList(it.toMutableList())
                }
            })

    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            it.setupToolbar {
                setToolbarTitle(getString(R.string.task_fragment_toolbar_title))
                setToolbarLeftIcon(ContextCompat.getDrawable(it, R.drawable.ic_arrow_back)) {
                    onBackPressed()
                }
                setupFab(ContextCompat.getDrawable(it, R.drawable.ic_add)) {
                    val addTaskFragment = AddTaskFragment()
                    addTaskFragment.show(
                        it.supportFragmentManager,
                        AddTaskGroupFragment::class.java.name
                    )
                }
            }
        }
    }
}
