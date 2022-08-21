package com.mgm.s1_hilt.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mgm.s1_hilt.databinding.ActivityNotesBinding
import com.mgm.s1_hilt.room.db.NoteModel
import com.mgm.s1_hilt.room.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityNotesBinding

    @Inject
    lateinit var repository: DbRepository
    @Inject
    lateinit var model: NoteModel
    @Inject
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener{
                model.id = 0
                model.title = titleEdt.text.toString()
                repository.saveNote(model)
                titleEdt.setText("")
                noteAdapter.differ.submitList(repository.getAllNotes())
            }
            noteAdapter.differ.submitList(repository.getAllNotes())
            notesList.apply {
                layoutManager = LinearLayoutManager(this@NotesActivity)
                adapter = noteAdapter
            }
            noteAdapter.setOnItemClickListener {
                Toast.makeText(this@NotesActivity,"${it.id} : ${it.title}",Toast.LENGTH_SHORT).show()
            }
        }


    }
}