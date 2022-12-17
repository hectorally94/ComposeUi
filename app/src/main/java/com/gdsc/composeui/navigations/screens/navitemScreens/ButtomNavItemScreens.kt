package com.gdsc.composeui.navigations.screens.navitemScreens

import com.gdsc.composeui.R

sealed class BottomNavItemScreens(var title:String, var icon:Int, var screen_route:String){

    object DashBoardone : BottomNavItemScreens("Home1", R.drawable.ic_launcher_background,"dashboard_one")
    object DashBoardtwo: BottomNavItemScreens("Home2",R.drawable.ic_launcher_background,"dashboard_two")
    object DashBoardthree: BottomNavItemScreens("Home3",R.drawable.ic_launcher_background,"dashboard_three")
   }