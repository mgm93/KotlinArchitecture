package com.mgm.s1_livedata_viewmodel.room.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mgm.s1_livedata_viewmodel.R
import com.mgm.s1_livedata_viewmodel.databinding.FragmentNotesListBinding

class NotesListFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentNotesListBinding

    //Other
    private val notesAdapter by lazy { NoteAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initView


        binding.apply {
            //Click
            addNoteBtn.setOnClickListener {
                findNavController().navigate(R.id.actionNoteListToAddNote)
            }
            //Get data
            (activity as NotesActivity).noteDatabase.noteDao().getAllNotes()
                .observe(viewLifecycleOwner) {
                    notesAdapter.differ.submitList(it)

                    notesRecycler.apply {
                        layoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        adapter = notesAdapter
                    }
                }
        }
    }

}