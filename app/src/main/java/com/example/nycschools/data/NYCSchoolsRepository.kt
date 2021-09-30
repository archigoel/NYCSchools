package com.example.nycschools.data

import android.app.ProgressDialog
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import com.example.nycschools.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
This file is designed to retreive the data from the api.
getSchoolsList() - This function will get the data for nyc schools
getSATScoresList() - This function will get the data for SAT scores
 */
object NYCSchoolsRepository {

    fun getSchoolsList():  MutableLiveData<List<School>>{

        val request = ApiClient.buildService(ApiInterface::class.java)
        val call = request.getSchoolsList()
        val schoolsList: MutableLiveData<List<School>> by lazy {
            MutableLiveData<List<School>>()
        }
        call.enqueue(object : Callback<List<School>> {
            override fun onResponse(call: Call<List<School>>, response: Response<List<School>>) {
                if (response.isSuccessful) {
                    schoolsList.value = response.body()!!
                }
            }

            override fun onFailure(call: Call<List<School>>, t: Throwable) {
              println("is failed------- " + t.message)
            }
        })
         return schoolsList
    }

    fun getSATScoresList() : MutableLiveData<List<SchoolSatScore>>{

        val request = ApiClient.buildService(ApiInterface::class.java)
        val call = request.getSATScors()
        val satScoresList: MutableLiveData<List<SchoolSatScore>> by lazy {
            MutableLiveData<List<SchoolSatScore>>()
        }
        call.enqueue(object : Callback<List<SchoolSatScore>> {
            override fun onResponse(call: Call<List<SchoolSatScore>>, response: Response<List<SchoolSatScore>>) {
                if (response.isSuccessful) {
                    satScoresList.value = response.body()!!
                }
            }

            override fun onFailure(call: Call<List<SchoolSatScore>>, t: Throwable) {
                println("is failed------- " + t.message)
            }
        })
        return satScoresList
    }
}