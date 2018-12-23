package com.aigerimbb.android.tasbeeh.ui.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.aigerimbb.android.tasbeeh.BR
import com.iko.android.modularapp.base.CoreFragment
import com.iko.android.modularapp.base.CoreViewModel

abstract class BaseFragment<DataBinding : ViewDataBinding, ViewModel : CoreViewModel<*>>
(viewModelClass: Class<ViewModel>, @LayoutRes private var layoutId: Int)
    : CoreFragment<DataBinding, ViewModel>(viewModelClass, layoutId) {

    override fun performVariableDataBinding() {
        getUi().setVariable(BR.viewModel, viewModel)
    }

}