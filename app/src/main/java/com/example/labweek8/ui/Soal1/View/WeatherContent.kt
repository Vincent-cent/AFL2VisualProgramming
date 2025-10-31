package com.example.labweek8.ui.Soal1.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.labweek8.R
import com.example.labweek8.ui.Soal1.Model.Main
import com.example.labweek8.ui.Soal1.Model.Sys
import com.example.labweek8.ui.Soal1.Model.Weather
import com.example.labweek8.ui.Soal1.Model.WeatherResponse
import com.example.labweek8.ui.Soal1.Model.Wind
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherContent(data: WeatherResponse) {
    val condition = data.weather[0].main

    val panda = when (condition) {
        "Clouds" -> R.drawable.blue_and_black_bold_typography_quote_poster
        "Rain" -> R.drawable.blue_and_black_bold_typography_quote_poster_2
        "Clear" -> R.drawable.blue_and_black_bold_typography_quote_poster_3
        else -> R.drawable.blue_and_black_bold_typography_quote_poster
    }

    val sunrise = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(data.sys.sunrise * 1000))
    val sunset = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(data.sys.sunset * 1000))

    val dateNow = SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date())
    val timeNow = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Icon",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${data.name}",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Text(text = dateNow, fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold)
        Text(text = "Updated as of $timeNow", color = Color.White.copy(0.8f), fontSize = 14.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val iconCode = data.weather[0].icon
                val iconUrl = "https://openweathermap.org/img/wn/${iconCode}@2x.png"
                Image(
                    painter = rememberAsyncImagePainter(iconUrl),
                    contentDescription = "Weather Icon",
                    modifier = Modifier.size(80.dp)
                )
                Text(text = condition, fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Medium)
                Text(text = "${data.main.temp.toInt()}°C", fontSize = 70.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(id = panda),
                contentDescription = "Panda Image",
                modifier = Modifier.size(120.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherInfoCard("Humidity", "${data.main.humidity}%", R.drawable.icon_humidity)
                WeatherInfoCard("Wind", "${data.wind.speed} km/h", R.drawable.icon_wind)
                WeatherInfoCard("Feels Like", "${data.main.feels_like.toInt()}°", R.drawable.icon_feels_like)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherInfoCard("Rain Fall", "0.0 mm", R.drawable.vector_2)
                WeatherInfoCard("Pressure", "${data.main.pressure}hPa", R.drawable.devices)
                WeatherInfoCard("Clouds", "8%", R.drawable.cloud)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "Sunrise",
                    modifier = Modifier.size(40.dp)
                )
                Text("Sunrise", color = Color.White.copy(0.8f), fontSize = 14.sp)
                Text(sunrise, color = Color.White, fontWeight = FontWeight.Bold)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.vector_21png),
                    contentDescription = "Sunset",
                    modifier = Modifier.size(40.dp)
                )
                Text("Sunset", color = Color.White.copy(0.8f), fontSize = 14.sp)
                Text(sunset, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun WeatherInfoCard(label: String, value: String, icon: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.1f)),
        modifier = Modifier.size(width = 100.dp, height = 100.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = label.uppercase(), color = Color.White.copy(0.8f), fontSize = 11.sp)
            Text(text = value, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherContentPreview() {
    val dummy = WeatherResponse(
        weather = listOf(Weather("Clear", "clear sky", "01d")),
        main = Main(31.0, 32.0, 1011, 49),
        sys = Sys("ID", 1714010200, 1714054000),
        wind = Wind(2.0, 0),
        name = "Cepu"
    )
    WeatherContent(dummy)
}
