package com.aigerimbb.android.tasbeeh.ui.update

import android.content.res.Resources
import androidx.databinding.ObservableField
import com.aigerimbb.android.tasbeeh.R
import com.aigerimbb.android.tasbeeh.data.database.entity.Tasbeeh
import com.aigerimbb.android.tasbeeh.domain.usecase.*
import com.aigerimbb.android.tasbeeh.event.Event
import com.iko.android.modularapp.base.CoreViewModel
import javax.inject.Inject

class UpdateTasbeehViewModel @Inject constructor(
        private val updateOrInsertTasbeeh: UpdateOrInsertTasbeeh,
        private val resources: Resources): CoreViewModel<Event>(){

    val tasbeeh: ObservableField<Tasbeeh> = ObservableField()

    fun initTasbeeh(tasbeeh: Tasbeeh?) {
        this.tasbeeh.set(tasbeeh ?: Tasbeeh())
    }

    fun updateTasbeeh(tasbeeh: Tasbeeh){
        if (validateInputs(tasbeeh)){
            updateOrInsertTasbeeh.run(UpdateOrInsertTasbeeh.Params(tasbeeh))
            uiTrigger.value = Event.Notification(resources.getString(R.string.saved_successfully))
        }
    }

    private fun validateInputs(tasbeeh: Tasbeeh): Boolean{
        if(tasbeeh.name.isNullOrEmpty())
            uiTrigger.value = Event.InvalidTasbeehName(resources.getString(R.string.can_not_be_empty, resources.getString(R.string.tasbeeh)))
        if(tasbeeh.maxCount == 0)
            uiTrigger.value = Event.InvalidTasbeehMaxCount(resources.getString(R.string.can_not_be_empty, resources.getString(R.string.tasbeeh_max_number)))
        if(tasbeeh.meaning.isNullOrEmpty())
            uiTrigger.value = Event.InvalidTasbeehMeaning(resources.getString(R.string.can_not_be_empty, resources.getString(R.string.tasbeeh_meaning)))
        return !tasbeeh.name.isNullOrEmpty()
                && tasbeeh.maxCount != null && tasbeeh.maxCount!! >= 0
                && !tasbeeh.meaning.isNullOrEmpty()
    }

}