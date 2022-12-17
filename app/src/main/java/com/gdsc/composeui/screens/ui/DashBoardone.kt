package com.gdsc.composeui.screens.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gdsc.composeui.components.MovieRow
import com.gdsc.composeui.data.getMoviesData
import com.gdsc.composeui.models.Movie
import com.gdsc.composeui.navigations.screens.Screens

@Composable
fun DashBoardone(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
       // Text(text = "DashBoardone")
        MainContent(navController = navController)
    }
}
@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMoviesData()
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn( modifier = Modifier.padding(bottom = 50.dp)) {
            items(items = movieList) {
                MovieRow(movie = it){movie->
                    navController.navigate(route = Screens.DetailsScreen.screen_route+"/$movie")

                }
            }
        }

    }

}

@Preview(name = "DashBoardone")
@Composable
private fun PreviewDashBoardone() {
    DashBoardone(navController = NavController(LocalContext.current))
}