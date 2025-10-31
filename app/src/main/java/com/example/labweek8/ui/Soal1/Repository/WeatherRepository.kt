package com.example.labweek8.ui.Soal1.Repository

import com.example.labweek8.ui.Soal1.Model.WeatherResponse
import com.example.labweek8.ui.Soal1.network.RetrofitInstance

class WeatherRepository {
    suspend fun getWeather(city: String, apiKey: String): WeatherResponse {
        return RetrofitInstance.api.getWeather(city, apiKey)
    }
}
