package com.gdsc.composeui.navigations.screens.navitemScreens

import com.gdsc.composeui.R

sealed class DrawerNavItemScreens(var title:String, var icon:Int, var screen_route:String){

    object drawerone : DrawerNavItemScreens("Drawer screen 1", R.drawable.ic_launcher_background,"drawer_one")
    object drawertwo: DrawerNavItemScreens("Drawer screen 2", R.drawable.ic_launcher_background,"drawer_two")
    object drawerthree : DrawerNavItemScreens("Drawer screen 3", R.drawable.ic_launcher_background,"drawer_three")

}
