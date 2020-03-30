/**
 * Created by Fandly on 30/3/2020.
 * Made With Love
 */

package com.fandlys.covid19.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fandlys.covid19.pojos.DataPerCase
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.util.ArrayList

private const val TAG = "DataViewModel"
class DataViewModel : ViewModel() {
    private val listDataPerCase = MutableLiveData<ArrayList<DataPerCase>>()

    fun setDataPerCase(){
        val listItems = ArrayList<DataPerCase>()

        val urlCase =  "https://indonesia-covid-19.mathdro.id/api/kasus"
        Log.d(TAG, "Load : $urlCase")
        val client = AsyncHttpClient()
        client.get(urlCase, object :AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responeObjects = JSONObject(result)
                    val listData = responeObjects.getJSONObject("data")
                    val list = listData.getJSONArray("nodes")
                    for (i in 0 until list.length()){
                        val case = list.getJSONObject(i)
                        val caseItem = DataPerCase()

                        caseItem.id = case.getInt("id")
                        caseItem.caseId = case.getInt("kasus")
                        caseItem.cluster = case.getString("klaster")

                        //Bug di Umur idk why null
                        //D/DataViewModel: Value null at umur of type org.json.JSONObject$1 cannot be converted to int
                        //caseItem.age = case.getInt("umur")
                        caseItem.gender = case.getString("gender")
                        caseItem.status = case.getString("status")
                        listItems.add(caseItem)
                    }
                    listDataPerCase.postValue(listItems)
                }catch (e: Exception){
                    Log.d(TAG, e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(TAG, error?.message.toString())
            }

        })

    }

    fun getDataPerCase(): LiveData<ArrayList<DataPerCase>>{
        return listDataPerCase
    }
}


