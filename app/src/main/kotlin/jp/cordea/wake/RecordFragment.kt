package jp.cordea.wake

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import jp.cordea.wake.model.Session
import jp.cordea.wake.view.RecordButton
import jp.cordea.wake.view.SubButton
import jp.cordea.wake.viewmodel.RecordViewModel

class RecordFragment : Fragment() {

    private val viewModel: RecordViewModel by lazy {
        RecordViewModel(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_record, container, false) ?: return view

        val listView = view.findViewById(R.id.list_view) as ListView
        val stopButton = view.findViewById(R.id.stop_button) as SubButton
        val recordButton = view.findViewById(R.id.record_button) as RecordButton

        stopButton.apply {
            onClick = {
                recordButton.stop()
                viewModel.onClickStopButton()
            }
        }

        recordButton.apply {
            onClick = {
                viewModel.onClickRecordButton(isRunning)
            }
            viewModel.onUpdateTimer = {
                progress = (it * 100).toInt()
            }
        }

        viewModel.visibilityStopButton = {
            stopButton.visibility = it
        }

        listView.apply {
            this.adapter = viewModel.adapter
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sessions = viewModel.ratingNeededSessions.toMutableList()
        showRatingDialog(sessions)
    }

    private fun showRatingDialog(sessions: MutableList<Session>) {
        if (sessions.isEmpty()) {
            return
        }
        val session = sessions.first()
        if (session.isNeedRating) {
            RatingDialogFragment
                    .newInstance(session.id)
                    .apply {
                        onDismiss = {
                            showRatingDialog(sessions)
                        }
                    }
                    .show(fragmentManager, "")
            sessions.remove(session)
        }
    }

    companion object {

        fun newInstance(): RecordFragment {
            return RecordFragment()
        }

    }

}
