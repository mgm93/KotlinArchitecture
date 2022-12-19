package com.mgm.s2_flow.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mgm.s2_flow.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }
}