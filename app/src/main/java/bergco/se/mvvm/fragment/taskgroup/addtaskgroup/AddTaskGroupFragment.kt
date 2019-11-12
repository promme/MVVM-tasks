package bergco.se.mvvm.fragment.taskgroup.addtaskgroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import bergco.se.mvvm.R
import bergco.se.mvvm.extensions.lazyViewModel
import bergco.se.mvvm.extensions.observeText
import bergco.se.mvvm.fragment.taskgroup.addtaskgroup.impl.AddTaskGroupViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_task_group.*

class AddTaskGroupFragment : BottomSheetDialogFragment() {

    val addTaskGroupViewModel: AddTaskGroupViewModel by lazyViewModel(this@AddTaskGroupFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_task_group, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        btn_add_task_group_save.setOnClickListener {
            addTaskGroupViewModel.onSaveClicked()
        }
        tiet_add_task_group_name.observeText(addTaskGroupViewModel.taskGroupName)
        tiet_add_task_group_desciption.observeText(addTaskGroupViewModel.taskGroupDescription)

        addTaskGroupViewModel.taskGroupNameError.observe(this, Observer<String> { error ->
            tiet_add_task_group_name.error = error
        })
        addTaskGroupViewModel.taskGroupDescriptionError.observe(this, Observer<String> { error ->
            tiet_add_task_group_desciption.error = error
        })
        addTaskGroupViewModel.dismissDialog.observe(this, Observer<Boolean> { shouldDissmis ->
            if (shouldDissmis) {
                dismiss()
            }
        })
    }
}