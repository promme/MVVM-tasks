package bergco.se.mvvm.utils

import androidx.recyclerview.widget.DiffUtil

class ListDiffer<E> : DiffUtil.ItemCallback<E>() {
    override fun areContentsTheSame(oldItem: E, newItem: E): Boolean =
        oldItem.hashCode() == newItem.hashCode()


    override fun areItemsTheSame(oldItem: E, newItem: E): Boolean =
        oldItem.hashCode() == newItem.hashCode()
}