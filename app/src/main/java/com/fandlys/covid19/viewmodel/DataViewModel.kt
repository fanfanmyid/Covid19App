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
                    val list = responeObjects.getJSONArray("data")
                    for (i in 0 until list.length()){
                        val case = list.getJSONObject(i)
                        val caseItem = DataPerCase()

                        //API telah berubah jadi perbaruannya pak agak sedikit berbeda
                        caseItem.id = case.getInt("id_pasien")
                        caseItem.caseId = case.getInt("kode_pasien")
                        caseItem.cluster = case.getString("keterangan")

                        //Bug di Umur idk why null
                        //D/DataViewModel: Value null at umur of type org.json.JSONObject$1 cannot be converted to int
                        //caseItem.age = case.getInt("umur")
                        if (case.getInt("jenis_kelamin") == 0) {
                            caseItem.gender = "Laki-laki"
                        } else caseItem.gender = "Perempuan"

                        if (case.getInt("id_status") == 1) {
                            caseItem.status = "Positif"
                        } else caseItem.status = "Negatif"
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


