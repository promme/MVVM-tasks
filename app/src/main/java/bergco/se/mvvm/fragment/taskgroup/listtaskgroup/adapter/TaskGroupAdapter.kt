package bergco.se.mvvm.fragment.taskgroup.listtaskgroup.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bergco.se.mvvm.R
import bergco.se.mvvm.model.TaskGroup
import bergco.se.mvvm.model.TaskGroupAndTasks
import bergco.se.mvvm.utils.ListDiffer
import kotlinx.android.synthetic.main.item_task_group.view.*

class TaskGroupAdapter(private val onTaskGroupClicked: (TaskGroup) -> (Unit)) :
    ListAdapter<TaskGroupAndTasks, TaskGroupViewHolder>(ListDiffer<TaskGroupAndTasks>()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskGroupViewHolder {
        return TaskGroupViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_task_group,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskGroupViewHolder, position: Int) {
        val taskGroupAndTasks = getItem(holder.adapterPosition)
        taskGroupAndTasks.taskGroup?.let { taskGroup ->
            holder.itemView.apply {
                tv_task_group_title.text = taskGroup.title
                tv_task_group_description.text = taskGroup.description
                val tasks = taskGroupAndTasks.tasks
                tv_task_group_completed.text =
                    context.getString(
                        R.string.tasks_group_adapter_completed,
                        tasks?.filter { it.isCompleted }?.size ?: 0,
                        tasks?.size ?: 0
                    )
                setOnClickListener { onTaskGroupClicked.invoke(taskGroup) }
            }
        }
    }
}

class TaskGroupViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

}