package com.example.labweek8.ui.Soal2.Model

data class TrackResponse(
    val track: List<Track>?
)

data class Track(
    val idTrack: String?,
    val strTrack: String?,
    val intTrackNumber: String?,
    val strAlbum: String?,
    val intDuration: String?
)
