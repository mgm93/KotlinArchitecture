package com.mgm.s1_hilt.room.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mgm.s1_hilt.NOTE_TABLE

/**
 * Created by Majid-Golmoradi on 8/21/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Entity(tableName = NOTE_TABLE)
data class NoteModel(@PrimaryKey(autoGenerate = true) var id:Int = 0, var title:String="")
