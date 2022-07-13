package com.mgm.s1_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.mgm.s1_room.databinding.ActivityAddUserBinding
import com.mgm.s1_room.databinding.ActivityMainBinding
import com.mgm.s1_room.db.UserDatabase
import com.mgm.s1_room.db.UserEntity
import com.mgm.s1_room.utils.Constants

class AddUserActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityAddUserBinding

    //Database
    private val userDb:UserDatabase by lazy {
        Room.databaseBuilder(this,UserDatabase::class.java,Constants.USER_DATABASE)
            .allowMainThreadQueries()//allow to run on io thread
            .fallbackToDestructiveMigration()//fail on migration
            .build()
    }

    //Other
    private lateinit var user:UserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initialize
        binding.apply {
            saveBtn.setOnClickListener {
                val name = nameEdt.text.toString()
                val age = ageEdt.text.toString()

                if (name.isNotEmpty() && age.isNotEmpty()) {
                    user = UserEntity(0, name, age.toInt())
                    userDb.dao().insertUser(user)
                    finish()
                } else {
                    Snackbar.make(it , "Name and Age cannot be empty", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

    }
}