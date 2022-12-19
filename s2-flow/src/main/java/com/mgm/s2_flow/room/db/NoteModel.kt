package com.mgm.s2_flow.room.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mgm.s2_flow.utils.NOTE_TABLE

/**
 * Created by Majid-Golmoradi on 12/19/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Entity(tableName = NOTE_TABLE)
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = ""
)
