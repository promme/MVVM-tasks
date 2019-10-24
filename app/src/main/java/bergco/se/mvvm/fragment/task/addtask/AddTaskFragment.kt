package bergco.se.mvvm.fragment.task.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bergco.se.mvvm.R
import bergco.se.mvvm.extensions.observeText
import bergco.se.mvvm.fragment.task.addtask.impl.AddTaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_task.*

class AddTaskFragment : BottomSheetDialogFragment() {

    lateinit var addTaskViewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        addTaskViewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        btn_add_task_save.setOnClickListener {
            addTaskViewModel.onSaveClicked()
        }
        tiet_add_task_name.observeText(addTaskViewModel.taskName)
        tiet_add_task_desciption.observeText(addTaskViewModel.taskDescription)

        addTaskViewModel.taskNameError.observe(this, Observer<String> { error ->
            tiet_add_task_name.error = error
        })
        addTaskViewModel.taskDescriptionError.observe(this, Observer<String> { error ->
            tiet_add_task_desciption.error = error
        })
        addTaskViewModel.dismissDialog.observe(this, Observer<Boolean> { shouldDissmiss ->
            if (shouldDissmiss) dismiss()
        })
    }


}