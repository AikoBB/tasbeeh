package com.aigerimbb.android.tasbeeh.ui.main.profit

import com.aigerimbb.android.tasbeeh.R
import com.aigerimbb.android.tasbeeh.TasbeehApp
import com.aigerimbb.android.tasbeeh.databinding.FragmentProfitBinding
import com.aigerimbb.android.tasbeeh.ui.base.BaseFragment
import com.aigerimbb.android.tasbeeh.ui.main.MainViewModel

class ProfitFragment: BaseFragment<FragmentProfitBinding, MainViewModel>(MainViewModel::class.java, R.layout.fragment_profit){

    override fun performDependencyInjection() {
        (context!!.applicationContext as TasbeehApp).appComponent.inject(this)
    }
}