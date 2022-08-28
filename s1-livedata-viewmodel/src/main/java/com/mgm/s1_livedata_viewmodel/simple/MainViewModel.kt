package com.mgm.s1_livedata_viewmodel.simple

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Majid-Golmoradi on 8/28/2022.
 * Email: golmoradi.majid@gmail.com
 */
class MainViewModel:ViewModel() {

    val data : MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun updateData(){
        data.postValue("hiii again")
    }
}