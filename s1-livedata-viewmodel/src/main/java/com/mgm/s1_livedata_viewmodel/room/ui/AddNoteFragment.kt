package com.mgm.s1_livedata_viewmodel.room.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mgm.s1_livedata_viewmodel.databinding.FragmentAddNoteBinding
import com.mgm.s1_livedata_viewmodel.room.db.NoteEntity

class AddNoteFragment : Fragment() {
    //Binding
    private lateinit var binding :FragmentAddNoteBinding
    //Other
    private lateinit var note: NoteEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Save note
            btnSave.setOnClickListener {
                val title = titleEdt.text.toString()
                val desc = descEdt.text.toString()

                note = NoteEntity(0, title, desc)

                (activity as NotesActivity).noteDatabase.noteDao().saveNote(note)
                findNavController().popBackStack()
            }
        }
    }
    
}