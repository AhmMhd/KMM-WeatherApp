package com.abdulhakeem.weatherapp_kmm.android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abdulhakeem.weatherapp_kmm.model.request.DailyForecastRequest
import com.abdulhakeem.weatherapp_kmm.network.Response
import com.abdulhakeem.weatherapp_kmm.usecases.FetchDailyForecastUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.ln

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            val res =
                FetchDailyForecastUC().invoke(DailyForecastRequest(lat = 24.8607, lng = 67.0011))
            when (res) {
                is Response.Success<*> -> {
                    Toast.makeText(
                        this@MainActivity,
                        "${res.response.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                }
            }
        }
    }
}
