package com.mgm.s1_livedata_viewmodel.room.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.mgm.s1_livedata_viewmodel.R
import com.mgm.s1_livedata_viewmodel.databinding.ActivityNotesBinding
import com.mgm.s1_livedata_viewmodel.room.db.NoteDatabase
import com.mgm.s1_livedata_viewmodel.room.utils.NOTE_DATABASE

class NotesActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityNotesBinding
    //Other
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    //DataBase
    val noteDatabase:NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initViews
        binding.apply {
            navController = findNavController(R.id.navHost)
            appBarConfiguration = AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(navController,appBarConfiguration)
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}