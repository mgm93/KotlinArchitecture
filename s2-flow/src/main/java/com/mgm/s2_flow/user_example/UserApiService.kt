package com.mgm.s2_flow.user_example

/**
 * Created by Majid-Golmoradi on 12/19/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface UserApiService {
    suspend fun getAllUser(pageNumber: Int): List<User>
}