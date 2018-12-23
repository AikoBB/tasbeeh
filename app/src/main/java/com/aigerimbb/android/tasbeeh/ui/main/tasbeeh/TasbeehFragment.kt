package com.aigerimbb.android.tasbeeh.ui.main.tasbeeh

import com.aigerimbb.android.tasbeeh.R
import com.aigerimbb.android.tasbeeh.TasbeehApp
import com.aigerimbb.android.tasbeeh.databinding.FragmentTasbeehBinding
import com.aigerimbb.android.tasbeeh.ui.base.BaseFragment
import com.aigerimbb.android.tasbeeh.ui.main.MainViewModel

class TasbeehFragment: BaseFragment<FragmentTasbeehBinding, MainViewModel>(MainViewModel::class.java, R.layout.fragment_tasbeeh){

    override fun performDependencyInjection() {
        (context!!.applicationContext as TasbeehApp).appComponent.inject(this)
    }
}