package com.aigerimbb.android.tasbeeh.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aigerimbb.android.tasbeeh.R
import com.aigerimbb.android.tasbeeh.TasbeehApp
import com.aigerimbb.android.tasbeeh.databinding.ActivityTasbeehMainBinding
import com.aigerimbb.android.tasbeeh.ui.base.BaseActivity
import com.aigerimbb.android.tasbeeh.ui.main.profit.ProfitFragment
import com.aigerimbb.android.tasbeeh.ui.main.tasbeeh.TasbeehFragment
import com.aigerimbb.android.tasbeeh.ui.main.tasbeeh_list.TasbeehListFragment
import kotlinx.android.synthetic.main.activity_tasbeeh_main.*

class MainActivity: BaseActivity<ActivityTasbeehMainBinding, MainViewModel>(MainViewModel::class.java, R.layout.activity_tasbeeh_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomNavigation()
        showFragment(TasbeehFragment(), TasbeehFragment::class.java.canonicalName)
    }

    override fun performDependencyInjection() {
        (applicationContext as TasbeehApp).appComponent.inject(this)
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.container, fragment, tag)
                .commit()
    }

    private fun setupBottomNavigation() {
        bottom_navigation.selectedItemId = R.id.menu_tasbeeh
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_tasbeeh ->showFragment(TasbeehFragment(), TasbeehFragment::class.java.canonicalName)
                R.id.menu_tasbeeh_list -> showFragment(TasbeehListFragment(), TasbeehListFragment::class.java.canonicalName)
                R.id.menu_profit -> showFragment(ProfitFragment(), ProfitFragment::class.java.canonicalName)
            }
            true
        }
    }

}