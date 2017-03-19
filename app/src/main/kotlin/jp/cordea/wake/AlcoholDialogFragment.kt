package jp.cordea.wake

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.EditText
import jp.cordea.wake.viewmodel.AlcoholDialogViewModel

/**
 * Created by Yoshihiro Tanaka on 2017/03/17.
 */
class AlcoholDialogFragment : DialogFragment() {

    private val viewModel by lazy {
        AlcoholDialogViewModel(context)
    }

    companion object {
        fun newInstance(): AlcoholDialogFragment {
            return AlcoholDialogFragment()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_alcohol, null)
        val volumeEditText = view.findViewById(R.id.volume_edit_text) as EditText
        val abvEditText = view.findViewById(R.id.abv_edit_text) as EditText
        return AlertDialog.Builder(context)
                .setTitle(R.string.alcohol_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.dialog_button_ok, { _, _ ->
                    viewModel.setAbv(abvEditText.text)
                    viewModel.setVolume(volumeEditText.text)
                })
                .setNegativeButton(R.string.dialog_button_cancel, { _, _ ->
                })
                .create()
    }

}