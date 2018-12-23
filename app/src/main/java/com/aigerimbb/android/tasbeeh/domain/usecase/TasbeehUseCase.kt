package com.aigerimbb.android.tasbeeh.domain.usecase

import androidx.lifecycle.LiveData
import com.aigerimbb.android.tasbeeh.data.database.entity.Tasbeeh
import com.aigerimbb.android.tasbeeh.domain.repository.TasbeehRepository
import javax.inject.Inject

class GetTasbeehListAsLive @Inject constructor(private val tasbeehRepository: TasbeehRepository): UseCase<LiveData<List<Tasbeeh>>, UseCase.None>(){
    override fun run(params: None) = tasbeehRepository.tasbeehsAsLiveData()
}

class GetTasbeehList @Inject constructor(private val tasbeehRepository: TasbeehRepository): UseCase<List<Tasbeeh>, UseCase.None>(){
    override fun run(params: None) = tasbeehRepository.tasbeehList()
}

class GetTasbeehById @Inject constructor(private val tasbeehRepository: TasbeehRepository): UseCase<Tasbeeh, GetTasbeehById.Params>(){

    override fun run(params: Params) = tasbeehRepository.tasbeehById(params.id)
    data class Params(val id: Int)
}

class DeleteTasbeeh @Inject constructor(private val tasbeehRepository: TasbeehRepository): UseCase<Unit, DeleteTasbeeh.Params>(){

    override fun run(params: Params) {
        tasbeehRepository.deleteTasbeeh(params.tasbeeh)
    }
    data class Params(val tasbeeh: Tasbeeh)
}

class UpdateOrInsertTasbeeh @Inject constructor(private val tasbeehRepository: TasbeehRepository): UseCase<Unit, UpdateOrInsertTasbeeh.Params>(){

    override fun run(params: Params) {
        tasbeehRepository.updateOrInsertTasbeeh(params.tasbeeh)
    }

    data class Params(val tasbeeh: Tasbeeh)
}