/**
 * Created by Fandly on 30/3/2020.
 * Made With Love
 */

package com.fandlys.covid19.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fandlys.covid19.pojos.DataPerProvince
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

private const val TAG = "ProvinceViewModel"

class ProvinceViewModel: ViewModel() {
    private val listProvince = MutableLiveData<ArrayList<DataPerProvince>>()

    fun setProvince(){
        val listItems = ArrayList<DataPerProvince>()

        val url = "https://indonesia-covid-19.mathdro.id/api/provinsi"
        Log.d(TAG, "Load : $url")
        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("data")

                    for (i in 0 until list.length()){
                        val province = list.getJSONObject(i)
                        val provinceItem = DataPerProvince()
                        //di JSON terdapat provinsi indonesia, jadi saya putuskan untuk menghilangkan
                        if (province.getInt("fid")== 35) continue
                        provinceItem.id = province.getInt("fid")

                        provinceItem.province = province.getString("provinsi")
                        provinceItem.casePositive = province.getInt("kasusPosi")
                        provinceItem.caseRecovered = province.getInt("kasusSemb")
                        provinceItem.caseDie = province.getInt("kasusMeni")
                        listItems.add(provinceItem)
                    }
                    listProvince.postValue(listItems)
                }catch (e:Exception){
                    Log.d(TAG, e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(TAG , error?.message.toString())
            }

        })
    }

    fun getProvince(): LiveData<ArrayList<DataPerProvince>>{
        return listProvince
    }
}