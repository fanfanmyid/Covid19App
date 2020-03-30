package com.fandlys.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fandlys.covid19.pojos.DataPerCase
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import java.util.ArrayList

class DataViewModel : ViewModel() {
    private val listDataPerCase = MutableLiveData<ArrayList<DataPerCase>>()

    fun setDataPerCase(){
        val listItems = ArrayList<DataPerCase>()

        val urlCase =  "https://indonesia-covid-19.mathdro.id/api/kasus"
        val client = AsyncHttpClient()
        client.get(urlCase, object :AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?

            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }
}


