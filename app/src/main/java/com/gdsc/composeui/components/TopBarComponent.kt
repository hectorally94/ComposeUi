package com.gdsc.composeui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopBarComponent(scope: CoroutineScope, scaffoldState: ScaffoldState)
{
    var mDisplayMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current
    // on below line we are creating variable for pager state.
    val pagerState = rememberPagerState(pageCount = 3)

    Column {

        TopAppBar(
            title = { Text(text = "Compose UI App", fontSize = 18.sp) },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(Icons.Filled.Menu, "")
                }
            },
            actions = {
                Icon(imageVector = Icons.Rounded.Check,
                    contentDescription = "Icon",
                    tint = Color.White)

                // Creating Icon button for dropdown menu
                IconButton(onClick = { mDisplayMenu =!mDisplayMenu }) {
                    Icon(Icons.Default.MoreVert, "")
                }
                // Creating a dropdown menu
                DropdownMenu(
                    expanded = mDisplayMenu,
                    onDismissRequest = { mDisplayMenu = false }
                ) {

                    // Creating dropdown menu item, on click
                    // would create a Toast message
                    DropdownMenuItem(
                        onClick = {
                        }) {
                        Text(text = "Settings")
                    }
                    // Creating dropdown menu item, on click
                    // would create a Toast message
                    DropdownMenuItem(
                        onClick = {
                            Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                        }) {
                        Text(text = "Logout")
                    }
                }
            },
            //backgroundColor = colorResource(id = R.color.colorPrimary),

            contentColor = Color.White,

        )
///////////
        

    }

}

@Preview(showBackground = false)
@Composable
fun TopBarComponentPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    TopBarComponent(scope = scope, scaffoldState = scaffoldState)
}
