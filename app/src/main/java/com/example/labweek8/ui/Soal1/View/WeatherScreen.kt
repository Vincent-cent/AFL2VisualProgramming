package com.example.labweek8.ui.Soal1.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labweek8.ui.Soal1.ViewModel.WeatherViewModel
import com.example.labweek8.R

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    var city by remember { mutableStateOf("") }

    val weatherData = viewModel.weatherData.value
    val isLoading = viewModel.isLoading.value
    val error = viewModel.errorMessage.value

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.weather___home_2),
                contentDescription = "background",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        placeholder = {
                            Text(
                                "Enter City Name...",
                                color = Color.White.copy(0.7f),
                                fontSize = 14.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White.copy(0.7f)
                            )
                        },
                        modifier = Modifier.size(260.dp, 60.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color.White.copy(alpha = 0.35f),
                            unfocusedBorderColor = Color.White.copy(alpha = 0.35f),
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.17f),
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.17f)
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedButton(
                        onClick = {
                            if (city.isNotBlank()) {
                                viewModel.loadWeather(city, "95d8b947ad7b65af87116f86392febb1")
                            }
                        },
                        modifier = Modifier
                            .height(56.dp)
                            .width(100.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.White.copy(alpha = 0.4f)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White.copy(alpha = 0.17f),
                            contentColor = Color.White.copy(alpha = 0.9f),
                            disabledContentColor = Color.White.copy(alpha = 0.17f)
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Search", fontSize = 13.sp)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (city.isNotEmpty()) {
                    when {
                        isLoading -> CircularProgressIndicator()
                        error != null -> Text(
                            text = "Error: $error",
                            color = MaterialTheme.colorScheme.error
                        )

                        weatherData != null -> {
                            WeatherContent(data = weatherData)
                        }
                    }
                }
            }

            if (city.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White.copy(0.7f),
                            modifier = Modifier.size(64.dp)
                        )
                        Text(
                            text = "Search for a city to get started",
                            color = Color.White.copy(0.7f),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherScreenPreview() {
    val dummyViewModel = WeatherViewModel()
    WeatherScreen(viewModel = dummyViewModel)
}


