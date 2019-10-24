package bergco.se.mvvm.fragment.task.listtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bergco.se.mvvm.R
import bergco.se.mvvm.utils.ListDiffer
import bergco.se.mvvm.model.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(private val onItemCompleteChangedListener: (Task) -> (Unit)) :
    ListAdapter<Task, TaskViewHolder>(ListDiffer<Task>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = TaskViewHolder(inflater.inflate(R.layout.item_task, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(holder.adapterPosition)
        holder.itemView.apply {
            tv_task_description.text = item.description
            tv_task_title.text = item.title
            tv_task_description.text = item.description
            cb_completed.isChecked = item.isCompleted
            setOnClickListener { cb_completed.isChecked = !cb_completed.isChecked }
            cb_completed.setOnCheckedChangeListener { buttonView, isChecked ->
                item.isCompleted = isChecked
                onItemCompleteChangedListener.invoke(item)
            }
        }
    }

}

class TaskViewHolder(ItemView: View) :
    RecyclerView.ViewHolder(ItemView)