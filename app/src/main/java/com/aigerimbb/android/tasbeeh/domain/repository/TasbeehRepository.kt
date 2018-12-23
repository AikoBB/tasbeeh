package com.aigerimbb.android.tasbeeh.domain.repository

import androidx.lifecycle.LiveData
import com.aigerimbb.android.tasbeeh.data.database.TasbeehDatabase
import com.aigerimbb.android.tasbeeh.data.database.entity.Tasbeeh
import javax.inject.Inject

interface TasbeehRepository {

    fun tasbeehsAsLiveData(): LiveData<List<Tasbeeh>>
    fun tasbeehList(): List<Tasbeeh>
    fun updateOrInsertTasbeeh(tasbeeh: Tasbeeh)
    fun deleteTasbeeh(tasbeeh: Tasbeeh)
    fun tasbeehById(id: Int): Tasbeeh

    class Database @Inject constructor(private val db: TasbeehDatabase) : TasbeehRepository {

        override fun tasbeehsAsLiveData(): LiveData<List<Tasbeeh>> {
            return db.tasbeehDao().getAllAsLiveData()
        }

        override fun tasbeehList(): List<Tasbeeh> {
            return db.tasbeehDao().getAll()
        }

        override fun updateOrInsertTasbeeh(tasbeeh: Tasbeeh) {
            val tasbeehInDab =  if(tasbeeh.number == null) null
                                else db.tasbeehDao().getTasbeehById(tasbeeh.number!!)
            when(tasbeehInDab != null){
                true ->  db.tasbeehDao().update(tasbeeh)
                else ->  db.tasbeehDao().insert(tasbeeh)
            }
        }

        override fun deleteTasbeeh(tasbeeh: Tasbeeh) {
            db.tasbeehDao().delete(tasbeeh)
        }

        override fun tasbeehById(id: Int): Tasbeeh {
            return db.tasbeehDao().getTasbeehById(id)
        }
    }
}