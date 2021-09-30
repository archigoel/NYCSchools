package com.example.nycschools.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("s3k6-pzi2.json")
    fun getSchoolsList(): Call<List<School>>
    @GET("f9bf-2cp4.json")
    fun getSATScors(): Call<List<SchoolSatScore>>
}
