package com.aigerimbb.android.tasbeeh.data.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.aigerimbb.android.tasbeeh.data.database.entity.Tasbeeh
import com.iko.android.core.database.BaseDao

@Dao
interface TasbeehDao: BaseDao<Tasbeeh> {

    @Query("SELECT * FROM tasbeeh")
    fun getAllAsPaging(): DataSource.Factory<Int, Tasbeeh>

    @Query("SELECT * FROM tasbeeh")
    fun getAllAsLiveData(): LiveData<List<Tasbeeh>>

    @Query("SELECT * FROM tasbeeh")
    fun getAll(): List<Tasbeeh>

    @Query("SELECT * FROM tasbeeh WHERE id = :id")
    fun getTasbeehById(id: Int): Tasbeeh

    @Query("DELETE FROM tasbeeh")
    fun deleteAll()
}