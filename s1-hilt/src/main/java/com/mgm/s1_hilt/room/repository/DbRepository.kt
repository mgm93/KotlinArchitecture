package com.mgm.s1_hilt.room.repository

import com.mgm.s1_hilt.room.db.NoteDao
import com.mgm.s1_hilt.room.db.NoteModel
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 8/21/2022.
 * Email: golmoradi.majid@gmail.com
 */
class DbRepository @Inject constructor(private val dao: NoteDao) {
    fun saveNote(noteModel: NoteModel) = dao.insertNote(noteModel)
    fun getAllNotes() = dao.getAllNotes()
}