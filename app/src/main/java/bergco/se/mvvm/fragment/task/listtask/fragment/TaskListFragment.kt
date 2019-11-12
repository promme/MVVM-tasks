package bergco.se.mvvm.fragment.task.listtask.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import bergco.se.mvvm.R
import bergco.se.mvvm.base.BaseFragment
import bergco.se.mvvm.extensions.lazyActivityBoundViewModel
import bergco.se.mvvm.extensions.lazyViewModel
import bergco.se.mvvm.extensions.setupToolbar
import bergco.se.mvvm.fragment.task.addtask.AddTaskFragment
import bergco.se.mvvm.fragment.task.listtask.adapter.TaskAdapter
import bergco.se.mvvm.fragment.task.listtask.fragment.impl.TaskListViewModel
import bergco.se.mvvm.fragment.taskgroup.addtaskgroup.AddTaskGroupFragment
import bergco.se.mvvm.model.Task
import bergco.se.mvvm.sharedviewmodels.TaskGroupSharedViewModel
import kotlinx.android.synthetic.main.fragment_task.*
import timber.log.Timber

class TaskListFragment : BaseFragment(R.layout.fragment_task) {
    val taskListViewModel: TaskListViewModel by lazyViewModel()
    val taskGroupSharedViewModel: TaskGroupSharedViewModel by lazyActivityBoundViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskAdapter = TaskAdapter(taskListViewModel.onItemCompleteChangedListener)
        rv_tasks.adapter = taskAdapter

        taskGroupSharedViewModel.taskGroupIdLiveData.observe(this, Observer<String> {
            setupTaskListObserver(taskAdapter, it)
        })
    }

    private fun setupTaskListObserver(taskAdapter: TaskAdapter, id: String) {
        taskListViewModel.observeTaskList(id).observe(
            this, Observer<List<Task>> {
                //Only update when items are added/removed
                if (it.size != taskAdapter.itemCount) {
                    taskAdapter.submitList(it.toMutableList())
                }
            })
    }

    override fun onResume() {
        super.onResume()
        Timber.d("***OLLE onresume")
        activity?.let {
            it.setupToolbar {
                setToolbarTitle(getString(R.string.task_fragment_toolbar_title))
                setToolbarLeftIcon(ContextCompat.getDrawable(it, R.drawable.ic_arrow_back)) {
                    onBackPressed()
                }
                setupFab(ContextCompat.getDrawable(it, R.drawable.ic_add)) {
                    Timber.d("***OLLE onclicked called")
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
