package com.mgm.s1_room.db

import androidx.room.*
import com.mgm.s1_room.utils.Constants

/**
 * Created by Majid-Golmoradi on 7/12/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Update
    fun updateUser(user: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Query("SELECT * from ${Constants.USER_TABLE} ORDER BY userId DESC")
    fun getAllUser(): MutableList<UserEntity>

    @Query("SELECT * FROM ${Constants.USER_TABLE} WHERE userId LIKE :id")
    fun getUser(id: Int): UserEntity

    @Query("DELETE FROM ${Constants.USER_TABLE}")
    fun deleteAllUser()

}