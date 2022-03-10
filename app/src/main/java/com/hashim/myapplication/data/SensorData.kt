package com.hashim.myapplication.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class SensorData(
    val sensorType: String? = null,
    val value: String? = null,
)