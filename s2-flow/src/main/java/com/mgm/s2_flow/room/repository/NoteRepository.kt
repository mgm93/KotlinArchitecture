package com.mgm.s2_flow.room.repository

import com.mgm.s2_flow.room.db.NoteDao
import com.mgm.s2_flow.room.db.NoteModel
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 12/19/2022.
 * Email: golmoradi.majid@gmail.com
 */
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun saveNote(noteModel: NoteModel) = noteDao.saveNote(noteModel)
    suspend fun deleteNote(noteModel: NoteModel) = noteDao.deleteNote(noteModel)
    fun getAllNotes() = noteDao.getAllNotes()
}