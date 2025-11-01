package com.example.labweek8.ui.Soal2.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labweek8.ui.Soal2.Model.Album
import com.example.labweek8.ui.Soal2.Model.Artist
import com.example.labweek8.ui.Soal2.Model.Track
import com.example.labweek8.ui.Soal2.Repository.ArtistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtistViewModel : ViewModel() {

    private val repository = ArtistRepository()

    private val _artist = MutableStateFlow<Artist?>(null)
    val artist: StateFlow<Artist?> = _artist

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums

    private val _tracks = MutableStateFlow<List<Track>>(emptyList())
    val tracks: StateFlow<List<Track>> = _tracks

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadArtistData()
    }

    fun loadArtistData() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val artistResponse = repository.getArtist()
                val albumResponse = repository.getAlbums()

                if (artistResponse?.artists != null && albumResponse?.album != null) {
                    _artist.value = artistResponse.artists.firstOrNull()
                    _albums.value = albumResponse.album
                } else {
                    _errorMessage.value = "Data tidak ditemukan"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi kesalahan saat memuat data"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadTracks(albumId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val trackResponse = repository.getTracks(albumId)
                if (trackResponse?.track != null) {
                    _tracks.value = trackResponse.track
                } else {
                    _errorMessage.value = "Daftar lagu tidak ditemukan"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Gagal mengambil track"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
