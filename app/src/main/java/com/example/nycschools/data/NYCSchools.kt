package com.example.nycschools.data

data class School(
        var school_name : String,
        var phone_number: String,
        var school_email : String,
        var location : String,
        var website : String,
        var dbn : String
)

data class SchoolSatScore(
        var dbn : String,
        var sat_critical_reading_avg_score: String,
        var sat_math_avg_score : String,
        var sat_writing_avg_score : String
)