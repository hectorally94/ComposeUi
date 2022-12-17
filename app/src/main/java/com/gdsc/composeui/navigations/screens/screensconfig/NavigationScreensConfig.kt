package com.gdsc.composeui.navigations.screens.screensconfig

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gdsc.composeui.navigations.screens.Screens
import com.gdsc.composeui.navigations.screens.navitemScreens.BottomNavItemScreens
import com.gdsc.composeui.navigations.screens.navitemScreens.DrawerNavItemScreens
import com.gdsc.composeui.screens.ui.*

@Composable
fun NavigationScreensConfig(navController : NavHostController){


    NavHost(navController , startDestination = BottomNavItemScreens.DashBoardone.screen_route){
        
        composable(BottomNavItemScreens.DashBoardone.screen_route){
            DashBoardone(navController)
        }

        composable(BottomNavItemScreens.DashBoardtwo.screen_route){
            DashBoardtwo()
        }
        composable(BottomNavItemScreens.DashBoardthree.screen_route){
            DashBoardthree()
        }

        ////////////////////// DROWER NAVIGATION
        composable(DrawerNavItemScreens.drawerone.screen_route){
            Drawerone()
        }
        composable(DrawerNavItemScreens.drawertwo.screen_route){
            Drawertwo()
        }

        composable(DrawerNavItemScreens.drawerthree.screen_route){
            Drawerthree()
        }
        ////////////////////// Details creens
        composable(Screens.DetailsScreen.screen_route+"/{movie}",
            arguments = listOf(navArgument(name = "movie")
            {type = NavType.StringType})){
                backStackEntry ->
            DetailsScreen(navController = navController,
                backStackEntry.arguments?.getString("movie"))
        }

    }
}
