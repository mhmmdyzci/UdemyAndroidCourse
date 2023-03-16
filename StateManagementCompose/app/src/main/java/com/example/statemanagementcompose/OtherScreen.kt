package com.example.statemanagementcompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtherScreen() {
    Surface(color = Color.DarkGray) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            var myString = remember {
                mutableStateOf("Android Compose ")
            }

            TextField(value = myString.value, onValueChange = {
                myString.value = it


            })

            Spacer(modifier = Modifier.padding(7.dp))

            var textString = remember {
                mutableStateOf("hello")
            }

            Text(text = textString.value, fontSize = 26.sp)

            Spacer(modifier = Modifier.padding(7.dp))

            Button(onClick = { textString.value = "Android"}) {
                Text(text = "button")
            }

            /* Spacer(modifier = Modifier.padding(7.dp))

             Image(bitmap = ImageBitmap.imageResource(id = R.drawable.kick),contentDescription = null)

             Spacer(modifier = Modifier.padding(7.dp))

             Image(imageVector = ImageVector.vectorResource(id=R.drawable.ic_launcher_background) , contentDescription = null)

             Spacer(modifier = Modifier.padding(7.dp))

             Image(painter =ColorPainter(Color.Black ) , contentDescription =null ) */


        }

    }


}