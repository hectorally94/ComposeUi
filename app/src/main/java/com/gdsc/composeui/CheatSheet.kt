package com.gdsc.composeui

/////////////////// creating TopBarComponent.kit ////////////////////////////
/*
@Composable
fun TopBarComponent(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    var mDisplayMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

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
        contentColor = Color.White
    )
}

@Preview(showBackground = false)
@Composable
fun TopBarComponentPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    TopBarComponent(scope = scope, scaffoldState = scaffoldState)
}

 */
/////// defining buttom navigation screens /////////
/*
sealed class BottomNavItemScreens(var title:String, var icon:Int, var screen_route:String){
    object DashBoardone : BottomNavItemScreens("Home1", R.drawable.ic_launcher_background,"dashboard_one")
    object DashBoardtwo: BottomNavItemScreens("Home2",R.drawable.ic_launcher_background,"dashboard_two")
    object DashBoardthree: BottomNavItemScreens("Home3",R.drawable.ic_launcher_background,"dashboard_three")
   }
* */
///////// implement bottom navigation screens //////////
/*
 // implement this Navigation library in the gradle (Module:app)
    implementation "androidx.navigation:navigation-compose:2.6.0-alpha04"
*/
/*
*
@Composable
fun BottomNavigationComponent(navController: NavController) {

    val items = listOf(
        BottomNavItemScreens.DashBoardone,
        BottomNavItemScreens.DashBoardtwo,
        BottomNavItemScreens.DashBoardthree,
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.secondary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title,
                    fontSize = 9.sp) },
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = MaterialTheme.colors.secondary,
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let {
                                screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun BottomNavigationComponentPreview() {
    val navController = NavController(LocalContext.current)
    BottomNavigationComponent(navController )
}*/

//////////////// end of implement bottom navigation screens/////////

////////////// defining Drawer navigation screens ////////////
/*
*
sealed class DrawerNavItemScreens(var title:String, var icon:Int, var screen_route:String){

    object drawerone : DrawerNavItemScreens("Drawer screen 1", R.drawable.ic_launcher_background,"drawer_one")
    object drowertwo: DrawerNavItemScreens("Drawer screen 2", R.drawable.ic_launcher_background,"drawer_two")
    object drowerthree : DrawerNavItemScreens("Drawer screen 3", R.drawable.ic_launcher_background,"drawer_three")

}*/

//// defining header of drawer
/*

@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "header image",
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(10.dp)
        )
        Text(modifier = Modifier
            .height(50.dp).align(alignment = Alignment.CenterHorizontally),
            text = "System Managment of Shop")
    }
}

@Preview(name = "DrawerHeader")
@Composable
private fun PreviewDrawerHeader() {
    DrawerHeader()
}

* */
/// previewing how draweritem look like
/*
*
@Composable
fun DrawerItem(item: DrawerNavItemScreens, selected: Boolean, onItemClick: (DrawerNavItemScreens) -> Unit) {

    val background = if (selected) android.R.color.black else android.R.color.transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.
        fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .height(45.dp)
            .background(colorResource(id = background ))
            .padding(start = 10.dp)
    ) {
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.White),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = item.title,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}


@Preview(name = "DrawerItem")
@Composable
private fun PreviewDrawerItem() {
    DrawerItem(item = DrawerNavItemScreens.drawerone, selected = false, onItemClick = {})
}*/
///////// implement Drawer  screens //////////
/*
*
@Composable
fun DrawerComponent(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {

    val scrollState = rememberScrollState()

    val screens  = listOf(
        DrawerNavItemScreens.drawerone,
        DrawerNavItemScreens.drowertwo,
        DrawerNavItemScreens.drowerthree
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
}*/
////end of drawer implementation ////////////

// creation of tbs

/// let add to the gradle this library
/*
// Paging Compose
implementation "com.google.accompanist:accompanist-pager:0.13.0"
implementation "com.google.accompanist:accompanist-pager-indicators:0.13.0"
 */
//// let create first content's methode of the tabs and displaying text in individual page
/*
// on below line we are creating a tab content method
// in which we will be displaying the individual page of our tab .
@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    // on below line we are creating
    // horizontal pager for our tab layout.
    HorizontalPager(state = pagerState) {
        // on below line we are specifying
        // the different pages.
            page ->
        when (page) {
            // on below line we are calling tab content screen
            // and specifying data as  Screen one.
            0 -> TabContentScreen(data = "Welcome to Tab Screen one")
            // on below line we are calling tab content screen
            // and specifying data as  Screen two.
            1 -> TabContentScreen(data = "Welcome to  tab Screen two")
            // on below line we are calling tab content screen
            // and specifying data as  Screen three.
            2 -> TabContentScreen(data = "Welcome to Tab Screen three")
        }
    }
}
// on below line we are creating a Tab Content
// Screen for displaying a simple text message.
@Composable
fun TabContentScreen(data: String) {
    // on below line we are creating a column
    Column(
        // in this column we are specifying modifier
        // and aligning it center of the screen on below lines.
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // in this column we are specifying the text
        Text(
            // on below line we are specifying the text message
            text = data,

            // on below line we are specifying the text style.
            style = MaterialTheme.typography.h5,

            // on below line we are specifying the text color
            color = MaterialTheme.colors.secondary,

            // on below line we are specifying the font weight
            fontWeight = FontWeight.Bold,

            //on below line we are specifying the text alignment.
            textAlign = TextAlign.Center
        )
    }
}
*/

/// let create the composable function to hold the tab layout
/*

// on below line we are creating a
// composable function for our tab layout
@ExperimentalPagerApi
@Composable
fun TabsItem(pagerState: PagerState) {
    // in this function we are creating a list
    // in this list we are specifying data as
    // name of the tab and the icon for it.
    val list = listOf(
        "Home one" to Icons.Default.Home,
        "Shopping two" to Icons.Default.Home,
        "Settings three" to Icons.Default.Home
    )
    // on below line we are creating
    // a variable for the scope.
    val scope = rememberCoroutineScope()
    // on below line we are creating a
    // individual row for our tab layout.
    TabRow(
        // on below line we are specifying
        // the selected index.
        selectedTabIndex = pagerState.currentPage,

        // on below line we are
        // specifying background color.
        backgroundColor = MaterialTheme.colors.background,

        // on below line we are specifying content color.
        contentColor =  MaterialTheme.colors.background,

        // on below line we are specifying
        // the indicator for the tab
        indicator = { tabPositions ->
            // on below line we are specifying the styling
            // for tab indicator by specifying height
            // and color for the tab indicator.
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        // on below line we are specifying icon
        // and text for the individual tab item
        list.forEachIndexed { index, _ ->
            // on below line we are creating a tab.
            Tab(
                // on below line we are specifying icon
                // for each tab item and we are calling
                // image from the list which we have created.
                icon = {
                    Icon(imageVector = list[index].second, contentDescription = null)
                },
                // on below line we are specifying the text for
                // the each tab item and we are calling data
                // from the list which we have created.
                text = {
                    Text(
                        list[index].first,
                        // on below line we are specifying the text color
                        // for the text in that tab
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                // on below line we are specifying
                // the tab which is selected.
                selected = pagerState.currentPage == index,
                // on below line we are specifying the
                // on click for the tab which is selected.
                onClick = {
                    // on below line we are specifying the scope.
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

* */
//finally putting all together the contents methode and methode of holding the tab layout

/*
* // on below line we are creating a
// composable function for our tab layout
@OptIn(ExperimentalUnitApi::class)
@ExperimentalPagerApi
@Composable
fun TabLayout() {

    // on below line we are creating variable for pager state.
    val pagerState = rememberPagerState(pageCount = 3)

    // on below line we are creating a column for our widgets.
    Column(
        // for column we are specifying modifier on below line.
        modifier = Modifier.background(Color.White)
    ) {
        // on below line we are calling tabs
        TabsItem(pagerState = pagerState)
        // on below line we are calling tabs content
        // for displaying our page for each tab layout
        TabsContent(pagerState = pagerState)
    }
}*/

/// end of creating the tabs

//////// creating a new component file to hold textinput and Buttom input configuration
/*

@ExperimentalComposeUiApi
@Composable
fun MyInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},
    keyboardOptions: KeyboardOptions,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            textColor= Color.Black,
            backgroundColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.primaryVariant,
            focusedIndicatorColor = MaterialTheme.colors.primaryVariant,
            focusedLabelColor = MaterialTheme.colors.primaryVariant),
        maxLines = maxLine,
        label = { Text(text = label) },
        keyboardOptions=keyboardOptions,
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),        modifier = modifier
    )

}

@OptIn(ExperimentalComposeUiApi::class)
@Preview(name = "myInputtext")
@Composable
private fun PreviewMyInputText() {
    MyInputText(
        modifier = Modifier
            .width(100.dp),
        text = "Ramadhani",
        label = "your name is",
        onTextChange = {},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}
@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier,
        colors = colors
    )
    {

        Text(text =text,
            fontWeight = FontWeight.Bold,
            fontSize =15.sp
        )

    }

}

@Preview(name = "MyButtom")
@Composable
private fun PreviewMyButton() {
    MyButton(
        modifier= Modifier
            .width(120.dp)
            .height(70.dp),
        text = "myButton",
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.secondary
        )
    )
}
 */
///// end of configure the tabs

/// creation of screens
/// finally let configure our screen for the purpose of navigation form one screen to an other
/*
*
@Composable
fun NavigationScreensConfig(navController : NavHostController){


    NavHost(navController , startDestination = BottomNavItemScreens.DashBoardone.screen_route){

        composable(BottomNavItemScreens.DashBoardone.screen_route){
            DashBoardone()
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

    }
}
*/
//

/////////////// using static data ////////////////////
