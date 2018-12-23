package com.aigerimbb.android.tasbeeh.ui.main.tasbeeh_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aigerimbb.android.tasbeeh.R
import com.aigerimbb.android.tasbeeh.TasbeehApp
import com.aigerimbb.android.tasbeeh.data.database.entity.Tasbeeh
import com.aigerimbb.android.tasbeeh.databinding.FragmentTasbeehBinding
import com.aigerimbb.android.tasbeeh.ui.base.BaseFragment
import com.aigerimbb.android.tasbeeh.ui.main.MainViewModel
import com.aigerimbb.android.tasbeeh.ui.main.tasbeeh.TasbeehFragment
import com.aigerimbb.android.tasbeeh.ui.main.tasbeeh_list.adapter.TasbeehAdapter
import com.aigerimbb.android.tasbeeh.ui.update.UpdateTasbeehActivity
import com.iko.android.core.extension.showConfirmDialog
import kotlinx.android.synthetic.main.fragment_tasbeeh_list.*

class TasbeehListFragment: BaseFragment<FragmentTasbeehBinding, MainViewModel>(MainViewModel::class.java, R.layout.fragment_tasbeeh_list),
        TasbeehAdapter.Listener{

    private lateinit var adapter: TasbeehAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TasbeehAdapter(this, resources)
        setupView()
        subscribeToLibeData()
    }

    override fun performDependencyInjection() {
        (context!!.applicationContext as TasbeehApp).appComponent.inject(this)
    }

    private fun setupView() {
        rv_tasbeehs.layoutManager = getLinearLayoutManager()
        rv_tasbeehs.itemAnimator = DefaultItemAnimator()
        rv_tasbeehs.adapter = adapter
        btn_add_tasbeeh.setOnClickListener { UpdateTasbeehActivity.start(activity) }
    }

    private fun getLinearLayoutManager(): LinearLayoutManager{
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        return layoutManager
    }

    private fun subscribeToLibeData(){
        viewModel.loadTasbeehs().observe(this, Observer {
            it?.let {
                adapter.insertItems(it.toMutableList())
                viewModel.initList()
            }
        })
    }

    override fun onEditClick(tasbeeh: Tasbeeh) {
        UpdateTasbeehActivity.start(activity, tasbeeh)
    }

    override fun deleteClick(tasbeeh: Tasbeeh) {
        context?.showConfirmDialog(getString(R.string.dialog_confirm_delete),
                onOkListener = {viewModel.deleteTasbeeh(tasbeeh)})
    }

    override fun onTasbeehClick(tasbeeh: Tasbeeh) {
        viewModel.setSelectedTasbeeh(tasbeeh)
        activity?.supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.container, TasbeehFragment(), TasbeehFragment::class.java.canonicalName)
                .commit()
    }
}