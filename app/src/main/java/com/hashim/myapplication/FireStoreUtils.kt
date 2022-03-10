package com.hashim.myapplication

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hashim.myapplication.FireStoreUtils.FireStorePaths.hDataPath
import com.hashim.myapplication.FireStoreUtils.FireStorePaths.hUserPath
import com.hashim.myapplication.data.SensorData
import com.hashim.myapplication.data.User
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber

class FireStoreUtils {
    val hFireStoreDb = Firebase.firestore


    suspend fun hGetData(): MutableMap<String, User> {
        return suspendCancellableCoroutine {
            val hUserMap = mutableMapOf<String, User>()
            hFireStoreDb.collection(hUserPath)
                .get()
                .addOnSuccessListener { hSnapShot ->
                    hSnapShot.documents.map { hDocumentSnapShot ->
                        val hUser: User? = hDocumentSnapShot.toObject(User::class.java)
                        hUser?.let { user ->
                            hUserMap.put(hDocumentSnapShot.id, user)
                        }
                    }
                    it.resume(hUserMap, null)
                }
        }
    }

    fun hInsertUser(hUser: User) {
        hFireStoreDb.collection(hUserPath)
            .add(hUser)
            .addOnSuccessListener {
                Timber.d("user with id ${it.id} added")
            }
            .addOnFailureListener {
                Timber.d("Exception ${it.message}")
            }
    }


    fun hInsertSensorData(
        hUserId: String,
        hSensorData: SensorData
    ) {
        val hPath = hDataPath.replace(
            "hUserDocId",
            hUserId
        )
        hFireStoreDb.collection(hPath)
            .add(hSensorData)
            .addOnSuccessListener {
                Timber.d("Data added with id ${it.id} added")
            }
            .addOnFailureListener {
                Timber.d("Exception ${it.message}")
            }
    }

    fun hUpdateData() {

    }

    fun hDeleteData() {

    }

    object FireStorePaths {
        const val hUserPath = "UserC"
        const val hDataPath = "/UserC/hUserDocId/SensorC"
    }

}