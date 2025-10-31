package com.example.labweek8.ui.Soal1.Model

data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val sys: Sys,
    val wind: Wind,
    val name: String
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int
)

data class Sys(
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

data class Wind(
    val speed: Double,
    val deg: Int
)
