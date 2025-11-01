package com.example.labweek8.ui.Soal2.Network

import com.example.labweek8.ui.Soal2.Model.ArtistResponse
import com.example.labweek8.ui.Soal2.Model.AlbumResponse
import com.example.labweek8.ui.Soal2.Model.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("search.php")
    suspend fun searchArtist(
        @Query("s") artistName: String
    ): ArtistResponse

    @GET("searchalbum.php")
    suspend fun getAlbumsByArtist(
        @Query("s") artistName: String
    ): AlbumResponse

    @GET("track.php")
    suspend fun getTracksByAlbum(
        @Query("m") albumId: String
    ): TrackResponse
}
