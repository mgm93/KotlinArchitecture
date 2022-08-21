package com.mgm.s1_hilt.room.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Majid-Golmoradi on 8/21/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao(): NoteDao
}