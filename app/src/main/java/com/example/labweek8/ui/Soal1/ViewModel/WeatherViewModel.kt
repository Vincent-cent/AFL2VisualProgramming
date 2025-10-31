package com.example.labweek8.ui.Soal1.ViewModel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labweek8.ui.Soal1.Model.WeatherResponse
import com.example.labweek8.ui.Soal1.Repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository = WeatherRepository()
) : ViewModel() {

    var weatherData = mutableStateOf<WeatherResponse?>(null)
    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)

    fun loadWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                errorMessage.value = null

                val result = repository.getWeather(city, apiKey)
                weatherData.value = result

            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Terjadi kesalahan"
            } finally {
                isLoading.value = false
            }
        }
    }
}
