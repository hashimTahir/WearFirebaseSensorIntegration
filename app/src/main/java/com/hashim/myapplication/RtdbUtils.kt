package com.hashim.myapplication

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RtdbUtils {
    val hRtdb = Firebase.database
    val hDbRef = hRtdb.reference


    fun hReadDataFromDb() {
        hDbRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val value = dataSnapshot.value
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }

    fun hGetDataFromDb() {

    }

//    hDbRef?.child("users 1")?.setValue(binding.text.text.toString()+"1234")
}