package com.aigerimbb.android.tasbeeh.ui.update

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.aigerimbb.android.tasbeeh.R
import com.aigerimbb.android.tasbeeh.TasbeehApp
import com.aigerimbb.android.tasbeeh.data.database.entity.Tasbeeh
import com.aigerimbb.android.tasbeeh.databinding.ActivityUpdateTasbeehBinding
import com.aigerimbb.android.tasbeeh.event.Event
import com.aigerimbb.android.tasbeeh.ui.base.BaseActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.iko.android.core.extension.showMessage
import kotlinx.android.synthetic.main.activity_update_tasbeeh.*

class UpdateTasbeehActivity: BaseActivity<ActivityUpdateTasbeehBinding, UpdateTasbeehViewModel>(UpdateTasbeehViewModel::class.java, R.layout.activity_update_tasbeeh){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initTasbeeh(intent.getParcelableExtra(Tasbeeh::class.java.canonicalName))
        setupToolbar(getString(R.string.toolbar_edit_insert))
        setupViews()
        subscribeLiveData()
    }

    private fun setupViews() {
        btn_save.setOnClickListener { viewModel.updateTasbeeh(viewModel.tasbeeh.get()!!) }
        addFocusListener(tasbeeh_name, layout_tasbeeh_name)
        addFocusListener(tasbeeh_meaning, layout_tasbeeh_meaning)
        addFocusListener(tasbeeh_count, layout_tasbeeh_count)
    }

    private fun addFocusListener(view: TextInputEditText, layout: TextInputLayout){
        view.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) layout.error = null
        }
    }

    private fun subscribeLiveData() {
        viewModel.uiTrigger.observe(this, Observer {
            when(it){
                is Event.InvalidTasbeehName -> layout_tasbeeh_name.error = it.warning
                is Event.InvalidTasbeehMeaning -> layout_tasbeeh_meaning.error = it.warning
                is Event.InvalidTasbeehMaxCount -> layout_tasbeeh_count.error = it.warning
                is Event.Notification -> showMessage(it.message)
            }
        })
    }

    override fun performDependencyInjection() {
        (applicationContext as TasbeehApp).appComponent.inject(this)
    }

    companion object {

        fun start(activity: Activity?, tasbeeh: Tasbeeh? = null) {
            activity?.startActivity(
                    Intent(activity, UpdateTasbeehActivity::class.java)
                            .putExtra(Tasbeeh::class.java.canonicalName, tasbeeh)
            )
        }
    }
}