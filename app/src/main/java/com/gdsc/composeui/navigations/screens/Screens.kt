package com.gdsc.composeui.navigations.screens

sealed class Screens(var title:String, var screen_route:String){

    object DetailsScreen : Screens("Details Screen","Details_Screen")

}