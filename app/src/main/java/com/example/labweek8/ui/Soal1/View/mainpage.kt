package com.example.labweek8.ui.Soal1.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labweek8.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp

@Composable
fun mainpageview() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.weather___home_2),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            alignment = Companion.TopCenter,
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                    .padding(20.dp)
                    .size(220.dp, 70.dp)
            ) {
                val searchText = remember { mutableStateOf("") }

                TextField(
                    value = searchText.value,
                    onValueChange = { searchText.value = it },
                    textStyle = LocalTextStyle.current.copy(color = Color.White),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Enter city name",
                            color = Color.LightGray,
                            fontSize = 16.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White.copy(alpha = 0.27f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.27f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedPlaceholderColor = Color.White,
                        unfocusedPlaceholderColor = Color.White,
                    ),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .padding(top = 20.dp, end = 8.dp)
                    .size(150.dp, 70.dp),
                colors = buttonColors(containerColor = Color.White.copy(alpha = 0.27f)),
                onClick = { /* TODO: Handle button click */ }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Search",
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun mainpageviewpreview() {
    mainpageview()
}
