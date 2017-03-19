package jp.cordea.wake

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.EditText
import jp.cordea.wake.viewmodel.ProgressDialogViewModel

/**
 * Created by Yoshihiro Tanaka on 2017/03/17.
 */
class ReferenceDialogFragment : DialogFragment() {

    private val viewModel by lazy {
        ProgressDialogViewModel(context)
    }

    companion object {
        fun newInstance(): ReferenceDialogFragment {
            return ReferenceDialogFragment()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_reference, null)
        val editText = view.findViewById(R.id.edit_text) as EditText
        return AlertDialog.Builder(context)
                .setTitle(R.string.reference_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.dialog_button_ok, { _, _ ->
                    viewModel.setProgress(editText.text)
                })
                .setNegativeButton(R.string.dialog_button_cancel, { _, _ ->
                })
                .create()
    }

}
