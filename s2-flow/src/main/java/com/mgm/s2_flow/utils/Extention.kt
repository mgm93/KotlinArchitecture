package com.mgm.s2_flow.utils

import android.view.View

/**
 * Created by Majid-Golmoradi on 12/25/2022.
 * Email: golmoradi.majid@gmail.com
 */

fun View.isVisible(showLoading: Boolean, container: View){
    if(showLoading) {
        this.visibility = View.VISIBLE
        container.visibility = View.GONE
    }else{
        this.visibility = View.GONE
        container.visibility = View.VISIBLE
    }
}