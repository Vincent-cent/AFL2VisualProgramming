package com.example.labweek8.ui.Soal2.View

import androidx.navigation.NavController
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.labweek8.ui.Soal2.Model.Track
import com.example.labweek8.ui.Soal2.ViewModel.ArtistViewModel

@Composable
fun AlbumDetailPage(
    viewModel: ArtistViewModel,
    albumId: String,
    navController: NavController
) {
    val tracks by viewModel.tracks.collectAsState()
    val albums by viewModel.albums.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(albumId) { viewModel.loadTracks(albumId) }

    val album = albums.firstOrNull { it.idAlbum == albumId }
    val albumName = album?.strAlbum ?: tracks.firstOrNull()?.strAlbum ?: "Unknown Album"
    val albumThumb = album?.strAlbumThumb
    val year = album?.intYearReleased
    val genre = album?.strGenre
    val description = album?.strDescriptionEN

    AlbumDetailContent(
        albumTitle = albumName,
        albumThumb = albumThumb,
        year = year,
        genre = genre,
        description = description,
        tracks = tracks,
        isLoading = isLoading,
        error = error,
        onBack = {
            if (!navController.popBackStack()) {
                navController.navigate("main") {
                    popUpTo("main") { inclusive = false }
                    launchSingleTop = true
                }
            }
        }
    )
}

@Composable
private fun SimpleTopBar(
    title: String,
    bg: Color,
    titleColor: Color,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg)
            .statusBarsPadding()
            .height(56.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = titleColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )

                IconButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun AlbumDetailContent(
    albumTitle: String,
    albumThumb: String?,
    year: String?,
    genre: String?,
    description: String?,
    tracks: List<Track>,
    isLoading: Boolean,
    error: String?,
    onBack: () -> Unit = {}
) {
    val bg = Color(0xFF1E1E1E)
    val cardBg = Color(0xFF2A2A2A)
    val gold = Color(0xFFD6C36A)
    val textDim = Color(0xFFBDBDBD)

    Scaffold(
        containerColor = bg,
        topBar = { SimpleTopBar(title = albumTitle, bg = bg, titleColor = gold, onBack = onBack) }
    ) { insets ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(insets)
        ) {
            when {
                isLoading || error != null -> LoadingErrorPage(isLoading, error)
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        item {
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = cardBg),
                                border = BorderStroke(1.dp, Color(0xFF3C3C3C)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = cardBg),
                                border = BorderStroke(1.dp, Color(0xFF3C3C3C)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                            ) {
                                val painter = rememberAsyncImagePainter(albumThumb)
                                val size = painter.intrinsicSize
                                val aspect = if (size.width > 0f && size.height > 0f) size.width / size.height else 1f

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(aspect)
                                ) {
                                    Image(
                                        painter = painter,
                                        contentDescription = albumTitle,
                                        modifier = Modifier.matchParentSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                    Box(
                                        modifier = Modifier
                                            .matchParentSize()
                                            .background(
                                                Brush.verticalGradient(
                                                    0f to Color.Transparent,
                                                    0.7f to Color.Transparent,
                                                    1f to Color(0x88000000)
                                                )
                                            )
                                    )
                                }
                            }

                            Spacer(Modifier.height(16.dp))

                            Text(
                                text = albumTitle,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 22.sp,
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                            )
                            Text(
                                text = "${year ?: "-"} • ${genre ?: "Indie"}",
                                color = textDim,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                            )

                            if (!description.isNullOrBlank()) {
                                    Text(
                                        text = description,
                                        color = textDim,
                                        fontSize = 14.sp,
                                        modifier = Modifier.padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                                    )
                                }
                                Spacer(Modifier.height(12.dp))
                            }

                            Text(
                                text = "Tracks",
                                color = gold,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 20.dp, start = 4.dp, bottom = 8.dp)
                            )
                        }

                        items(tracks) { track ->
                            TrackRow(
                                number = track.intTrackNumber,
                                title = track.strTrack,
                                duration = formatDuration(track.intDuration),
                                gold = gold,
                                textDim = textDim
                            )
                            Divider(color = Color(0xFF3C3C3C))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TrackRow(
    number: String?,
    title: String?,
    duration: String,
    gold: Color,
    textDim: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(Color(0xFF2A2A2A), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, gold),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Box(
                    modifier = Modifier.size(28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = (number ?: "•"), color = gold, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        Spacer(Modifier.width(12.dp))
        Text(
            text = title ?: "-",
            color = Color.White,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = duration,
            color = textDim,
            fontSize = 12.sp
        )
    }
}

private fun formatDuration(raw: String?): String {
    if (raw.isNullOrBlank()) return "–:–"
    return try {
        val v = raw.toLong()
        val totalSec = if (v > 10_000) v / 1000 else v
        val m = totalSec / 60
        val s = totalSec % 60
        "%d:%02d".format(m, s)
    } catch (_: Exception) {
        "–:–"
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlbumDetailPreview() {
    val sampleTracks = listOf(
        Track("1", "Last Train Home", "1", "Sob Rock", "187000"),
        Track("2", "Shouldn't Matter but It Does", "2", "Sob Rock", "236000"),
        Track("3", "New Light", "3", "Sob Rock", "217000"),
        Track("4", "Why You No Love Me", "4", "Sob Rock", "199000")
    )
    AlbumDetailContent(
        albumTitle = "Sob Rock",
        albumThumb = "https://www.theaudiodb.com/images/media/album/thumb/wyxvvt1626320575.jpg",
        year = "2021",
        genre = "Indie",
        description = "Sob Rock is the eighth studio album by American singer‑songwriter John Mayer...",
        tracks = sampleTracks,
        isLoading = false,
        error = null
    )
}
