package com.aigerimbb.android.tasbeeh.data.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tasbeeh")
@Parcelize
data class Tasbeeh(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var number: Int? = null,
        var name: String? = null,
        @ColumnInfo(name = "name_tr")
        var meaning: String? = null,
        var content: String? = null,
        @ColumnInfo(name = "max_count")
        var maxCount: Int? = 100
): Parcelable