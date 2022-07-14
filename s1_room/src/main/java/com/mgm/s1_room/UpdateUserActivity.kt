package com.mgm.s1_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.mgm.s1_room.databinding.ActivityUpdateUserBinding
import com.mgm.s1_room.db.UserDatabase
import com.mgm.s1_room.db.UserEntity
import com.mgm.s1_room.utils.Constants

class UpdateUserActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityUpdateUserBinding

    //Database
    private val userDatabase by lazy {
        Room.databaseBuilder(this,UserDatabase::class.java,Constants.USER_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    //Other
    private lateinit var user:UserEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init
        intent.extras?.let {
            val userId = it.getInt(Constants.BUNDLE_USER_ID)
            user = userDatabase.dao().getUser(userId)
        }
        binding.apply {
            nameEdt.setText(user.userName)
            ageEdt.setText(user.userAge.toString())
            //Delete
            deleteBtn.setOnClickListener {
                userDatabase.dao().deleteUser(user)
                finish()
            }
            //Update
            updateBtn.setOnClickListener {
                val name = nameEdt.text.toString()
                val age = ageEdt.text.toString()

                if (name.isNotEmpty() && age.isNotEmpty()) {
                    user = UserEntity(user.userId,name, age.toInt())
                    userDatabase.dao().updateUser(user)
                    finish()
                } else {
                    Snackbar.make(it , "Name and Age cannot be empty", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}