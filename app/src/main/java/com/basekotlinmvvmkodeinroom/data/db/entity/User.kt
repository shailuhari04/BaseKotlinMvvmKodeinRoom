package com.basekotlinmvvmkodeinroom.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.basekotlinmvvmkodeinroom.data.db.Converters
import com.basekotlinmvvmkodeinroom.utilities.Constants

@Entity( tableName = "UserInfo" )
data class User(
    @PrimaryKey(autoGenerate = false)
    var id:Int = Constants.DEFAULT_USER_ID,
    val name:String? = null,
    var userName:String? = null,
    var password:String? = null,
    val email:String? = null,
    val birthDate:String? = null,
    var phoneNumber:String? = null,
    var language: String? = null,
    val gender:String? = null,
    val profession:String? = null,
    val totalTaps:String? = null,
    var signUpMethod:String? = null,
    val imageUrl:String? = null,
    var userInterestString:String = "",
    @TypeConverters(Converters::class)
    var userInterests:List<String> = listOf()
)