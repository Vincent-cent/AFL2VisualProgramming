package com.example.labweek8.ui.Soal2.Model

data class ArtistResponse(
    val artists: List<Artist>?
)

data class Artist(
    val idArtist: String?,
    val strArtist: String?,
    val strGenre: String?,
    val strCountry: String?,
    val strBiographyEN: String?,
    val strArtistThumb: String?,
    val strArtistBanner: String?,
    val intFormedYear: String?
)
