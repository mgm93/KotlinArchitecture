package com.mgm.s2_flow.room.db

import androidx.room.*
import com.mgm.s2_flow.utils.NOTE_TABLE
import kotlinx.coroutines.flow.Flow

/**
 * Created by Majid-Golmoradi on 12/19/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(noteModel: NoteModel)

    @Delete
    suspend fun deleteNote(noteModel: NoteModel)

    @Query("SELECT * from $NOTE_TABLE")
    fun getAllNotes() : Flow<List<NoteModel>>
}