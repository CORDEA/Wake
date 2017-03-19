package jp.cordea.wake

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.RatingBar
import jp.cordea.wake.viewmodel.RatingDialogViewModel

/**
 * Created by Yoshihiro Tanaka on 2017/03/19.
 */
class RatingDialogFragment(private val sessionId: Long) : DialogFragment() {

    var onDismiss = { }

    private val viewModel = RatingDialogViewModel(sessionId)

    companion object {
        fun newInstance(sessionId: Long): RatingDialogFragment {
            return RatingDialogFragment(sessionId)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_rating, null)
        val ratingBar = view.findViewById(R.id.rating_bar) as RatingBar
        return AlertDialog.Builder(context)
                .setTitle(context.resources.getString(R.string.rating_dialog_title).format(sessionId))
                .setView(view)
                .setPositiveButton(R.string.dialog_button_ok, { _, _ ->
                    viewModel.rating = ratingBar.rating
                    onDismiss()
                })
                .setNegativeButton(R.string.dialog_button_later, { _, _ ->
                    onDismiss()
                })
                .setOnDismissListener {
                    onDismiss()
                }
                .create()
    }

}