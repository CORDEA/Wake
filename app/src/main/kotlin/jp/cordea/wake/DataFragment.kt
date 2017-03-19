package jp.cordea.wake

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.cordea.wake.viewmodel.DataViewModel

class DataFragment : Fragment() {

    private val viewModel by lazy {
        DataViewModel(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_data, container, false) ?: return view

        (view.findViewById(R.id.recycler_view) as RecyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DataListItemDecoration(context))
            adapter = viewModel.adapter
        }

        return view
    }

    companion object {

        fun newInstance(): DataFragment {
            return DataFragment()
        }

    }

}
