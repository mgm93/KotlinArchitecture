package com.mgm.s1_room.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.mgm.s1_room.utils.Constants

/**
 * Created by Majid-Golmoradi on 7/12/2022.
 * Email: golmoradi.majid@gmail.com
 */

@Entity(tableName = Constants.USER_TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId:Int,
    @ColumnInfo(name = "user_name")
    val userName:String,
    val userAge:Int
)
