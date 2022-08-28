package com.mgm.s1_livedata_viewmodel.room.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mgm.s1_livedata_viewmodel.room.utils.NOTE_TABLE

/**
 * Created by Majid-Golmoradi on 8/27/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Entity(tableName = NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var title:String = "",
    var desc:String = ""
)
