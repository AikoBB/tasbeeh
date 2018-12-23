package com.aigerimbb.android.tasbeeh.ui.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.aigerimbb.android.tasbeeh.BR
import com.iko.android.modularapp.base.CoreActivity
import com.iko.android.modularapp.base.CoreViewModel

abstract class BaseActivity<DataBinding : ViewDataBinding, ViewModel : CoreViewModel<*>>
(viewModelClass: Class<ViewModel>, @LayoutRes private var layoutId: Int)
    : CoreActivity<DataBinding, ViewModel>(viewModelClass, layoutId) {


    override fun performVariableDataBinding() {
        getUi().setVariable(BR.viewModel, viewModel)
    }
}