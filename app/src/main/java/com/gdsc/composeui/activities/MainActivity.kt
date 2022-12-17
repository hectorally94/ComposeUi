package com.gdsc.composeui.activities

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.gdsc.composeui.components.BottomNavigationComponent
import com.gdsc.composeui.components.DrawerComponent
import com.gdsc.composeui.components.TabLayout
import com.gdsc.composeui.components.TopBarComponent
import com.gdsc.composeui.navigations.screens.screensconfig.NavigationScreensConfig
import com.gdsc.composeui.ui.theme.ComposeUiTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   // TabLayout()
                  ComposeUiNavigation()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ComposeUiNavigation() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {TopBarComponent(scope = scope, scaffoldState = scaffoldState)},
        drawerBackgroundColor = MaterialTheme.colors.primary,
        // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
        drawerContent = {
            DrawerComponent(scope = scope, scaffoldState = scaffoldState, navController = navController)
        },
        bottomBar = { BottomNavigationComponent(navController = navController) },

        backgroundColor = MaterialTheme.colors.secondary

        ) {NavigationScreensConfig(navController = navController) }
    }

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeUiTheme {
        ComposeUiNavigation()
    }
}