package com.example.labweek8.ui.Soal2.Model

data class AlbumResponse(
    val album: List<Album>?
)

data class Album(
    val idAlbum: String?,
    val strAlbum: String?,
    val intYearReleased: String?,
    val strGenre: String?,
    val strAlbumThumb: String?,
    val strDescriptionEN: String?
)
