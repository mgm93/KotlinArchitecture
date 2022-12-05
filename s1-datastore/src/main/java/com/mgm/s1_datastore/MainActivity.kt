package com.mgm.s1_datastore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.coroutineScope
import com.mgm.s1_datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //DataStore
    private val dataStore: DataStore<Preferences> by preferencesDataStore("user_info")
    private val userName = stringPreferencesKey("USERNAME")
    private val userAge = intPreferencesKey("USERAGE")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            btnSave.setOnClickListener {
                CoroutineScope(IO).launch {
                    saveData(edtData.text.toString(), 29)
                }
            }

            //getData
            lifecycle.coroutineScope.launchWhenCreated {
                getName().collect{
                    txtSaved.text = it
                }
            }
        }
    }

    //Use suspend because dataStore inherits coroutine
    private suspend fun saveData(strName: String, age: Int) {
        dataStore.edit {
            it[userName] = strName
            it[userAge] = age
        }
    }

    private fun getName() = dataStore.data.map {
            it[userName] ?: ""
        }

}