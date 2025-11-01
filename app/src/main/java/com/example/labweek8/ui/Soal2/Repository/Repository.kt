package com.example.labweek8.ui.Soal2.Repository

import com.example.labweek8.ui.Soal2.Model.AlbumResponse
import com.example.labweek8.ui.Soal2.Model.TrackResponse
import com.example.labweek8.ui.Soal2.Model.ArtistResponse
import com.example.labweek8.ui.Soal2.Network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArtistRepository {

    private val api = RetrofitInstance.api

    suspend fun getArtist(): ArtistResponse? {
        return withContext(Dispatchers.IO) {
            try {
                api.searchArtist("John Mayer")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun getAlbums(): AlbumResponse? {
        return withContext(Dispatchers.IO) {
            try {
                api.getAlbumsByArtist("John Mayer")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun getTracks(albumId: String): TrackResponse? {
        return withContext(Dispatchers.IO) {
            try {
                api.getTracksByAlbum(albumId)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
