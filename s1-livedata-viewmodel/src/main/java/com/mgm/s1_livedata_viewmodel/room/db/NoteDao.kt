package com.mgm.s1_livedata_viewmodel.room.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mgm.s1_livedata_viewmodel.room.utils.NOTE_TABLE

/**
 * Created by Majid-Golmoradi on 8/27/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM $NOTE_TABLE")
    fun getAllNotes() : LiveData<MutableList<NoteEntity>>
}