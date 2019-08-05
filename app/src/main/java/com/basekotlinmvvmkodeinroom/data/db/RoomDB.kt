package com.basekotlinmvvmkodeinroom.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.basekotlinmvvmkodeinroom.data.db.dao.UserDao
import com.basekotlinmvvmkodeinroom.data.db.entity.User
import com.commonsware.cwac.saferoom.SafeHelperFactory

@Database(
    entities = [User::class] ,
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RoomDB : RoomDatabase(){
    abstract fun userDao(): UserDao
    companion object {
        @Volatile private var instance: RoomDB? = null
        private val LOCK = Any()
        const val DATABASE_NAME = "room_db.db"
        val password = "room_db"
        val factory = SafeHelperFactory(password.toCharArray())
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }
        private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext, RoomDB::class.java, DATABASE_NAME)
        .openHelperFactory(factory)
        .fallbackToDestructiveMigration()
        .build()
    }
}