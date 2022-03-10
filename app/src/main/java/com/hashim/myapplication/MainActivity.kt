package com.hashim.myapplication

import android.app.Activity
import android.hardware.Sensor
import android.os.Bundle
import com.hashim.myapplication.data.SensorData
import com.hashim.myapplication.data.User
import com.hashim.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : Activity() {

    private lateinit var hActivityMainBinding: ActivityMainBinding
    private val hFireStoreUtils = FireStoreUtils()

    private var hUserMap = mutableMapOf<String, User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(hActivityMainBinding.root)

        SensorService().hInit(this, Sensor.TYPE_PROXIMITY) {
            /*On Sensor change insert new data for the first user
            * Use your own logged in user
            * */

            hFireStoreUtils.hInsertSensorData(
                hUserMap.keys.first(),
                SensorData(
                    sensorType = it.sensor.name,
                    value = it.values[0].toString()
                )
            )
        }

        CoroutineScope(Dispatchers.IO).launch {
            hUserMap = hFireStoreUtils.hGetData()
            Timber.d("Data Retrieved ${hUserMap}")
        }

        hFireStoreUtils.hInsertUser(
            User(
                "Hashim",
                "hashim@gamil.com",
                uuid = "abcd"
            )
        )

        hActivityMainBinding.text.setOnClickListener {


        }
    }


}