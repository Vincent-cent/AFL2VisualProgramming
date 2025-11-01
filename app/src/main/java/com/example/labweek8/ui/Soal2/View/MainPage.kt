package com.example.labweek8.ui.Soal2.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.labweek8.ui.Soal2.Model.Album
import com.example.labweek8.ui.Soal2.ViewModel.ArtistViewModel

@Composable
fun MainPage(viewModel: ArtistViewModel, navController: NavController) {
    val artist by viewModel.artist.collectAsState()
    val albums by viewModel.albums.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    MainPageContent(
        artistName = artist?.strArtist,
        artistGenre = artist?.strGenre,
        artistThumb = artist?.strArtistThumb,
        albums = albums,
        isLoading = isLoading,
        error = error,
        onAlbumClick = { id -> navController.navigate("albumDetail/$id") }
    )
}

@Composable
private fun SimpleTopBar(title: String, bg: Color, titleColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg)
            .statusBarsPadding()
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = titleColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MainPageContent(
    artistName: String?,
    artistGenre: String?,
    artistThumb: String?,
    albums: List<Album>,
    isLoading: Boolean,
    error: String?,
    onAlbumClick: (String) -> Unit
) {
    val bg = Color(0xFF1E1E1E)
    val cardBg = Color(0xFF2A2A2A)
    val gold = Color(0xFFD6C36A)
    val textDim = Color(0xFFBDBDBD)

    Scaffold(
        containerColor = bg,
        topBar = { SimpleTopBar(title = artistName.orEmpty(), bg = bg, titleColor = gold) }
    ) { insets ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(insets)
                .padding(horizontal = 12.dp)
        ) {
            when {
                isLoading || error != null -> LoadingErrorPage(isLoading, error)
                artistName != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = cardBg),
                            border = BorderStroke(1.dp, Color(0xFF3C3C3C)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    painter = rememberAsyncImagePainter(artistThumb),
                                    contentDescription = "Artist",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.verticalGradient(
                                                0f to Color.Transparent,
                                                0.55f to Color.Transparent,
                                                1f to Color(0xAA000000)
                                            )
                                        )
                                )
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = artistName,
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 22.sp
                                    )
                                    Text(
                                        text = artistGenre ?: "Indie",
                                        color = textDim,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        Text(
                            text = "Albums",
                            color = gold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 4.dp)
                        )
                        Divider(
                            color = Color(0xFF3C3C3C),
                            thickness = 1.dp,
                            modifier = Modifier
                                .padding(top = 6.dp, bottom = 10.dp)
                                .fillMaxWidth()
                        )

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            items(albums) { album ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .clickable { album.idAlbum?.let(onAlbumClick) },
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = cardBg),
                                    border = BorderStroke(1.dp, Color(0xFF3C3C3C)),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                                ) {
                                    Column(modifier = Modifier.padding(bottom = 12.dp)) {
                                        Image(
                                            painter = rememberAsyncImagePainter(album.strAlbumThumb),
                                            contentDescription = album.strAlbum ?: "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(150.dp)
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = album.strAlbum ?: "",
                                            color = Color.White,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 15.sp,
                                            modifier = Modifier.padding(horizontal = 12.dp)
                                        )
                                        Text(
                                            text = "${album.intYearReleased ?: "-"} • ${album.strGenre ?: "Indie"}",
                                            color = textDim,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                else -> LoadingErrorPage(false, "Tidak ada data")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPagePreview() {
    val sampleAlbums = listOf(
        Album("1", "Sob Rock", "2021", "Indie", "https://www.theaudiodb.com/images/media/album/thumb/wyxvvt1626320575.jpg", ""),
        Album("2", "New Light", "2018", "Indie", "https://www.theaudiodb.com/images/media/album/thumb/jxwryx1626320542.jpg", ""),
        Album("3", "Paradise Valley", "2013", "Indie", "https://www.theaudiodb.com/images/media/album/thumb/qspqps1376598280.jpg", ""),
        Album("4", "Born and Raised", "2012", "Indie", "https://www.theaudiodb.com/images/media/album/thumb/tsvqrr1376598260.jpg", "")
    ) // Dummy Data Untuk Preview
    MainPageContent(
        artistName = "John Mayer",
        artistGenre = "Indie",
        artistThumb = "https://www.theaudiodb.com/images/media/artist/thumb/wyuyrp1421880821.jpg",
        albums = sampleAlbums,
        isLoading = false,
        error = null,
        onAlbumClick = {}
    )
}