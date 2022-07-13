package com.mgm.s1_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.mgm.s1_room.databinding.ActivityMainBinding
import com.mgm.s1_room.db.UserDatabase
import com.mgm.s1_room.utils.Constants

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding
    //Database
    private val userDb by lazy {
        Room.databaseBuilder(this@MainActivity,UserDatabase::class.java,Constants.USER_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    //Other
    private val usersAdapter by lazy { UsersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            fabAddMainActivity.setOnClickListener {
                val intent = Intent(this@MainActivity, AddUserActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkItems()
    }

    private fun checkItems(){
        binding.apply {
            if (userDb.dao().getAllUser().isNotEmpty()) {
                txtStateMainActivity.visibility = View.GONE
                recyclerMainActivity.visibility = View.VISIBLE
                //SetUp Recycler
                usersAdapter.differ.submitList(  userDb.dao().getAllUser())
                setUpRecycler()

            } else {
                txtStateMainActivity.visibility = View.VISIBLE
                recyclerMainActivity.visibility = View.GONE
            }
        }
    }

    private fun setUpRecycler(){
        binding.recyclerMainActivity.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = usersAdapter

        }
    }
}