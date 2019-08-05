package com.basekotlinmvvmkodeinroom.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.basekotlinmvvmkodeinroom.data.db.entity.User
import com.basekotlinmvvmkodeinroom.utilities.Constants.Companion.DEFAULT_USER_ID


@Dao
interface UserDao{
    @Query("SELECT * FROM UserInfo WHERE id = :userId")
    fun getUser(userId:Int = DEFAULT_USER_ID): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

}