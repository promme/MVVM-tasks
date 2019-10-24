package bergco.se.mvvm.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData


fun TextView.setTextWatcher(
    beforeTextChanged: ((text: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null,
    onTextChanged: ((text: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null,
    afterTextChanged: ((s: Editable?) -> Unit)? = null
) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            beforeTextChanged?.invoke(text, start, count, after)
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged?.invoke(text, start, before, count)
        }

        override fun afterTextChanged(s: Editable?) {
            afterTextChanged?.invoke(s)
        }
    })
}

fun TextView.observeText(mutableLiveData: MutableLiveData<String>) {
    setTextWatcher(afterTextChanged = { text -> mutableLiveData.postValue(text.toString()) })
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.showIfElseGone(boolean: Boolean) {
    if (boolean) {
        visible()
    } else {
        gone()
    }
}