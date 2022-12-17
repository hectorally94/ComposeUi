package com.gdsc.composeui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gdsc.composeui.navigations.screens.navitemScreens.DrawerNavItemScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerComponent(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {

    val scrollState = rememberScrollState()

    val screens  = listOf(
        DrawerNavItemScreens.drawerone,
        DrawerNavItemScreens.drawertwo,
        DrawerNavItemScreens.drawerthree
    )

    Column {
        // Header
        DrawerHeader()
        // Space between
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        Column(
            modifier = Modifier.verticalScroll(
                state = scrollState,
                enabled=true,
                flingBehavior = null,
                reverseScrolling= false
            )) {
            // List of navigation items
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            screens.forEach { item ->
                DrawerItem(item = item, selected = currentRoute == item.screen_route, onItemClick = {
                    navController.navigate(item.screen_route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                    // Close drawer
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Developed by Ramadhani Ally",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }

}
@Preview(showBackground = false)
@Composable
fun DrawerComponentPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val navController = rememberNavController()
    DrawerComponent(scope = scope, scaffoldState = scaffoldState, navController = navController)
}