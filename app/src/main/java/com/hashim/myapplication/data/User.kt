package com.hashim.myapplication.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val name: String? = null,
    val email: String? = null,
    val uuid: String? = null,
)