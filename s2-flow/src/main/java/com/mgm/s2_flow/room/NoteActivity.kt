package com.mgm.s2_flow.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.mgm.s2_flow.databinding.ActivityNoteBinding
import com.mgm.s2_flow.room.db.NoteModel
import com.mgm.s2_flow.room.viewmodel.NoteViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.mgm.s2_flow.room.adapter.NoteAdapter
import com.mgm.s2_flow.utils.MyResponse
import com.mgm.s2_flow.utils.isVisible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding : ActivityNoteBinding

    @Inject
    lateinit var entity: NoteModel

    @Inject
    lateinit var noteAdapter: NoteAdapter
    //Other
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //save note
            btnSave.setOnClickListener {
                entity.id = 0
                entity.name = titleEdt.text.toString()
                viewModel.saveNote(entity)
                titleEdt.setText("")
            }

            //getAllNotes
            viewModel.getAllNotes()

            viewModel.notes.observe(this@NoteActivity){
                when(it.status){
                    MyResponse.Status.LOADING ->{noteLoading.isVisible(true, noteContainer)}
                    MyResponse.Status.SUCCESS ->{
                        noteLoading.isVisible(false, noteContainer)
                        noteAdapter.setData(it.data)
                        //recycler
                        notesList.apply {
                            layoutManager = LinearLayoutManager(this@NoteActivity)
                            adapter = noteAdapter
                        }
                    }
                    MyResponse.Status.ERROR ->{
                        noteLoading.isVisible(false, noteContainer)
                        Toast.makeText(this@NoteActivity, it.message, Toast.LENGTH_SHORT).show()

                    }
                }
            }
            //Delete note
            noteAdapter.setOnItemClickListener {
                viewModel.deleteNote(it)
            }

        }
    }
}