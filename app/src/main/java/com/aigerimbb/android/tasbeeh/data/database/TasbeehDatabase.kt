package com.aigerimbb.android.tasbeeh.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aigerimbb.android.tasbeeh.data.database.dao.TasbeehDao
import com.aigerimbb.android.tasbeeh.data.database.entity.Tasbeeh
import java.io.FileOutputStream

@Database(entities = [Tasbeeh::class], version = 2, exportSchema = false)
abstract class TasbeehDatabase: RoomDatabase(){

    abstract fun tasbeehDao(): TasbeehDao

    companion object {

        private var instance: TasbeehDatabase? = null
        private const val DB_NAME = "MyTasbeeh.db"

        @Synchronized
        fun getInstance(context: Context): TasbeehDatabase{
            if (instance == null){
                val dbFile = context.getDatabasePath(DB_NAME)
                if(!dbFile.exists()) copyDatabase(context)
                instance = Room.databaseBuilder(context.applicationContext, TasbeehDatabase::class.java, DB_NAME )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .addMigrations()
                        .build()
            }
            return instance!!
        }

        private fun copyDatabase(context: Context){
            try {
                val inputStream = context.assets.open(DB_NAME)
                val outFileName = "/data/data/com.aigerimbb.android.tasbeeh/databases/$DB_NAME"
                val out = FileOutputStream(outFileName)
                val buff = ByteArray(1024)
                var length = 0
                while ({length = inputStream.read(buff);length}() > 0) {
                    out.write(buff, 0, length)
                }
                out.flush()
                out.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}