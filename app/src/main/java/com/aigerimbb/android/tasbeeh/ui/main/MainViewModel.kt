package com.aigerimbb.android.tasbeeh.ui.main

import androidx.databinding.ObservableField
import com.aigerimbb.android.tasbeeh.data.database.entity.Tasbeeh
import com.aigerimbb.android.tasbeeh.domain.usecase.DeleteTasbeeh
import com.aigerimbb.android.tasbeeh.domain.usecase.GetTasbeehList
import com.aigerimbb.android.tasbeeh.domain.usecase.GetTasbeehListAsLive
import com.aigerimbb.android.tasbeeh.domain.usecase.UseCase
import com.aigerimbb.android.tasbeeh.event.Event
import com.iko.android.modularapp.base.CoreViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val tasbeehsLive: GetTasbeehListAsLive,
        private val getTasbeehList: GetTasbeehList,
        private val deleteTasbeeh: DeleteTasbeeh): CoreViewModel<Event>(){

    val selectedTasbeeh = ObservableField<Tasbeeh>()
    var selectedIndex = 0
    val counter = ObservableField<Int>()
    val tasbeehList = mutableListOf<Tasbeeh>()

   init{
        initList()
    }

    fun initList(){
        tasbeehList.clear()
        tasbeehList.addAll(getTasbeehList.run(UseCase.None()))
        setSelectedTasbeeh(tasbeehList[selectedIndex])
    }

    fun loadTasbeehs() = tasbeehsLive.run(UseCase.None())
    fun deleteTasbeeh(tasbeeh: Tasbeeh) = deleteTasbeeh.run(DeleteTasbeeh.Params(tasbeeh))

    fun setSelectedTasbeeh(tasbeeh: Tasbeeh){
        selectedTasbeeh.set(tasbeeh)
        counter.set(tasbeeh.maxCount)
        selectedIndex = tasbeehList.indexOf(tasbeeh)
    }

    fun decreaseCounter(){
        var currentCounterState = counter.get() ?: 0
        currentCounterState = when(currentCounterState == 0){
            true -> selectedTasbeeh.get()!!.maxCount ?: 0
            else -> --currentCounterState
        }
        counter.set(currentCounterState)
    }

    fun onNextClick(){
        when{
            ++selectedIndex >= tasbeehList.size -> setSelectedTasbeeh(tasbeehList[0])
            else -> setSelectedTasbeeh(tasbeehList[selectedIndex])
        }
    }

    fun onPrevClick(){
        when{
            --selectedIndex < 0 -> setSelectedTasbeeh(tasbeehList[tasbeehList.size - 1])
            else -> setSelectedTasbeeh(tasbeehList[selectedIndex])
        }
    }
}
