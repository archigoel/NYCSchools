package com.example.nycschools.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nycschools.data.School
import com.example.nycschools.data.NYCSchoolsRepository
import com.example.nycschools.data.SchoolSatScore

class SchoolsViewModel : ViewModel() {
    var schoolsList = MutableLiveData<List<School>>()
    var satScoresList = MutableLiveData<List<SchoolSatScore>>()
    init {
        schoolsList = NYCSchoolsRepository.getSchoolsList()
        satScoresList = NYCSchoolsRepository.getSATScoresList()
    }
}