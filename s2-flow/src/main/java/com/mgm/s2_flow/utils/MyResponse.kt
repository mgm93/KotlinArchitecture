package com.mgm.s2_flow.utils

/**
 * Created by Majid-Golmoradi on 12/19/2022.
 * Email: golmoradi.majid@gmail.com
 */
data class MyResponse<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> loading() : MyResponse<T> {
            return MyResponse(Status.LOADING)
        }

        fun <T> success(data: T?) : MyResponse<T> {
            return MyResponse(Status.SUCCESS , data)
        }

        fun <T> error(error : String) : MyResponse<T>{
            return MyResponse(Status.ERROR, message = error)
        }
    }
}