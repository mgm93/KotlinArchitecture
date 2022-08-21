package com.mgm.s1_hilt.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mgm.s1_hilt.NOTE_TABLE

/**
 * Created by Majid-Golmoradi on 8/21/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: NoteModel)

    @Query("SELECT * from $NOTE_TABLE")
    fun getAllNotes():MutableList<NoteModel>
}