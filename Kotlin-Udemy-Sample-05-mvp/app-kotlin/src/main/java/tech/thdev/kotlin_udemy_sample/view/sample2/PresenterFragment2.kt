package tech.thdev.kotlin_udemy_sample.view.sample2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tech.thdev.kotlin_udemy_sample.R
import tech.thdev.kotlin_udemy_sample.view.sample2.presenter.Contract2
import tech.thdev.kotlin_udemy_sample.view.sample2.presenter.Presenter2

class PresenterFragment2 : Fragment(), Contract2.View {
    private val TAG: String = "PresenterFragment2"

    private val recyclerView by lazy {
        view?.findViewById<RecyclerView>(R.id.recycler_view)
    }

    private val fab by lazy {
        requireActivity().findViewById<FloatingActionButton>(R.id.fab)
    }

    companion object {
        fun getInstance() = PresenterFragment2()
    }

    private var presenter2: Contract2.Presenter? = null
    private var presenterAdapter2: PresenterAdapter2? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        presenter2 = Presenter2()
        presenter2?.view = this

        presenterAdapter2 = PresenterAdapter2(requireContext())
        recyclerView?.adapter = presenterAdapter2
        presenter2?.getItems(0)

        fab.setOnClickListener {
            Log.d(TAG, "ddddddddd")
            presenter2?.getItems(presenterAdapter2?.getItemCount() ?: 0)
        }
    }

    override fun addItem(index: Int) {
       presenterAdapter2?.addItem(index)
    }

    override fun adapterNotify() {
        presenterAdapter2?.notifyDataSetChanged()
    }
}