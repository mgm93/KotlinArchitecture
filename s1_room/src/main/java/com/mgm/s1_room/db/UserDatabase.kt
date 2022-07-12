package com.mgm.s1_room.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Majid-Golmoradi on 7/12/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun dao():UserDao
}