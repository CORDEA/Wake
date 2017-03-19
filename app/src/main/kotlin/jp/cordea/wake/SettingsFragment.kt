package jp.cordea.wake

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import jp.cordea.wake.viewmodel.SettingsViewModel

class SettingsFragment : Fragment() {

    private val viewModel by lazy {
        SettingsViewModel(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_settings, container, false) ?: return view

        (view.findViewById(R.id.reference_cell)).apply {
            setOnClickListener {
                ReferenceDialogFragment
                        .newInstance()
                        .show(fragmentManager, "")
            }
        }

        (view.findViewById(R.id.reference_description) as TextView).apply {
            text = viewModel.referenceDescription
        }

        (view.findViewById(R.id.alcohol_cell)).apply {
            setOnClickListener {
                AlcoholDialogFragment
                        .newInstance()
                        .show(fragmentManager, "")
            }
        }

        (view.findViewById(R.id.alcohol_description) as TextView).apply {
            text = viewModel.alcoholDescription
        }

        return view
    }

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }

}
