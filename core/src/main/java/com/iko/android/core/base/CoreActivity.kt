package com.iko.android.modularapp.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


/**
 * Created by Aigerim on 7/31/2018.
 */
abstract class CoreActivity<out T : ViewDataBinding, V : CoreViewModel<*>>(private var viewModelClass: Class<V>,
                                                                           @LayoutRes private var layoutId: Int) : AppCompatActivity() {
    private lateinit var ui: T
    protected lateinit var viewModel: V

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(viewModelClass)
        performDataBinding()
    }

    abstract fun performDependencyInjection()

    private fun performDataBinding() {
        setContentView(layoutId)
        ui = DataBindingUtil.setContentView(this, layoutId)
        performVariableDataBinding()
        ui.executePendingBindings()
    }

    abstract fun performVariableDataBinding()

    protected fun setupToolbar(title: String? = null) {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setNavigationOnClickListener { finish() }
        toolbar?.setTitleTextColor(Color.WHITE)
        title?.let { supportActionBar?.title = it }
    }

    fun getUi(): T = ui

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}