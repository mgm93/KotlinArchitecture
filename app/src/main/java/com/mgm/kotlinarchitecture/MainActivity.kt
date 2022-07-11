package com.mgm.kotlinarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mgm.kotlinarchitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.txtMainActivity.text = "Majid Golmoradi"
        val list : MutableList<UserModel> = loadDate()
        userAdapter.differ.submitList(list)
        list.add(UserModel(8,"Saghar", 20))
        list.add(UserModel(9,"Asghar", 21))
        list.add(UserModel(1,"Majid ", 28))
        list.add(UserModel(1,"Majid Golmoradi", 29))
        binding.recyclerMainActivity.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }

    private fun loadDate():MutableList<UserModel>{
        val list : MutableList<UserModel> = mutableListOf()

        list.add(UserModel(1,"Majid Golmoradi", 29))
        list.add(UserModel(1,"Majid Golmoradi", 29))
        list.add(UserModel(2,"Amir Amiri", 30))
        list.add(UserModel(3,"Hamid Hamidi", 18))
        list.add(UserModel(4,"Reza Rezaei", 10))
        list.add(UserModel(5,"Ali Alavi", 74))
        list.add(UserModel(6,"Behnam Saei", 45))
        list.add(UserModel(7,"Afshin Bedehkar", 25))

        return list
    }
}