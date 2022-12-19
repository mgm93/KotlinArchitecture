package com.mgm.s2_flow.user_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class UserExampleActivity : AppCompatActivity() {
    private val TAG = "UserExample"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = FakeUserRepository()
        val users = getAllUser(repository)

        lifecycleScope.launchWhenCreated {
            val user = users.first {
                Log.e(TAG, "Checking: $it" )
                delay(1000)
                it.name == "User12"
            }
            Log.e(TAG, "user: $user" )
        }
    }

    private fun getAllUser(api : UserApiService): Flow<User> = flow {
        var page = 0
        do {
            Log.e(TAG, "Fetching page:$page" )
            val users = api.getAllUser(page++)
            emitAll(users.asFlow())
        }while (users.isNotEmpty())
    }
}