package com.hashim.myapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.core.content.getSystemService
import timber.log.Timber


class SensorService {
    fun hInit(
        hInit: Context,
        hSensorType: Int,
        hSensorCallBack: (s: SensorEvent) -> Unit
    ) {
        val hSensorManager = hInit.getSystemService<SensorManager>()
        val hHeartRateSensor: Sensor?
        val hStepCountSensor: Sensor?
        val hStepDetectSensor: Sensor?
        val hProximitySensor: Sensor?

        val hSensorEventListener = object : SensorEventListener {

            override fun onSensorChanged(p0: SensorEvent?) {
                p0?.let {
                    hSensorCallBack(p0)
                }
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                Timber.d("onAccuracyChanged ${p0?.name}")
            }

        }

        when (hSensorType) {
            Sensor.TYPE_HEART_RATE -> {
                hHeartRateSensor = hSensorManager?.getDefaultSensor(Sensor.TYPE_HEART_RATE)

                if (hHeartRateSensor != null) {
                    hSensorManager?.registerListener(
                        hSensorEventListener,
                        hHeartRateSensor,
                        SensorManager.SENSOR_DELAY_NORMAL
                    )
                } else {
                    Timber.d("Heart Sensor not available")
                }

            }
            Sensor.TYPE_STEP_COUNTER -> {

                hStepCountSensor = hSensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
                if (hStepCountSensor != null) {
                    hSensorManager?.registerListener(
                        hSensorEventListener,
                        hStepCountSensor,
                        SensorManager.SENSOR_DELAY_NORMAL
                    )
                } else {
                    Timber.d("Step counter not available")
                }

            }
            Sensor.TYPE_STEP_DETECTOR -> {
                hStepDetectSensor = hSensorManager?.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
                if (hStepDetectSensor != null) {
                    hSensorManager?.registerListener(
                        hSensorEventListener,
                        hStepDetectSensor,
                        SensorManager.SENSOR_DELAY_NORMAL
                    )
                } else {
                    Timber.d("Step Detector not available")
                }
            }
            Sensor.TYPE_PROXIMITY -> {
                hProximitySensor = hSensorManager?.getDefaultSensor(Sensor.TYPE_PROXIMITY)
                if (hProximitySensor != null) {
                    hSensorManager?.registerListener(
                        hSensorEventListener,
                        hProximitySensor,
                        SensorManager.SENSOR_DELAY_NORMAL
                    )
                } else {
                    Timber.d("Proximity Sensor not available")
                }
            }
        }
    }

}