package com.mgm.s1_livedata_viewmodel.room.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Majid-Golmoradi on 8/27/2022.
 * Email: golmoradi.majid@gmail.com
 */

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao(): NoteDao
}