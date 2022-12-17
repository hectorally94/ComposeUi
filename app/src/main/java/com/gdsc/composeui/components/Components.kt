package com.gdsc.composeui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.gdsc.composeui.data.getMoviesData
import com.gdsc.composeui.models.Movie


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


@Preview
@Composable
fun MovieRow(movie: Movie = getMoviesData()[0],
             onItemClick: (String) -> Unit = {} )
{
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        //.height(130.dp)
        .clickable {
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp) {
                Image(painter = rememberImagePainter(data = movie.images[0],
                    builder = {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }),
                    contentDescription = "Movie Poster")

//                Icon(imageVector = Icons.Default.AccountBox,
//                    contentDescription = "Movie Image")

            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title,
                    style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.title}",
                    style = MaterialTheme.typography.caption)
                Text(text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.caption)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text( buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp)
                            ) {
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light)
                            ) {
                                append(movie.plot)
                            }

                        }, modifier = Modifier.padding(6.dp))

                        Divider(modifier = Modifier.padding(3.dp))
                        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)

                    }
                }

                Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray)
            }


        }
    }


}
