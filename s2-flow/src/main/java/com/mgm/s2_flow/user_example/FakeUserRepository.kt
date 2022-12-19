package com.mgm.s2_flow.user_example

import kotlinx.coroutines.delay

/**
 * Created by Majid-Golmoradi on 12/19/2022.
 * Email: golmoradi.majid@gmail.com
 */
class FakeUserRepository : UserApiService {
    private val users = List(50) { User("User$it") }
    private val pageSize = 3

    override suspend fun getAllUser(pageNumber: Int): List<User> {
        delay(500)
        return users.drop(pageSize * pageNumber)
            .take(pageSize)
    }
}