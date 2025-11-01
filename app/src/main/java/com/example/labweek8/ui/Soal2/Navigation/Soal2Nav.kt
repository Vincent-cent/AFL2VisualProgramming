package com.example.labweek8.ui.Soal2.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.labweek8.ui.Soal2.View.MainPage
import com.example.labweek8.ui.Soal2.View.AlbumDetailPage
import com.example.labweek8.ui.Soal2.ViewModel.ArtistViewModel

@Composable
fun Soal2NavHost(navController: NavHostController, viewModel: ArtistViewModel) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainPage(viewModel = viewModel, navController = navController)
        }
        composable("albumDetail/{albumId}") { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId") ?: ""
            AlbumDetailPage(
                viewModel = viewModel,
                albumId = albumId,
                navController = navController
            )
        }
    }
}
