package com.yp2048.repositories.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.R

@Composable
fun MenuScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val context: Context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.FillWidth
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            val openTheDoor = stringResource(id = R.string.open_the_door)

            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        openTheDoor,
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = stringResource(id = R.string.into_storage_room))
            }

            Button(
                onClick = {
                    navController.navigate("pickUp")
                },
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = stringResource(id = R.string.pick_up_item))
            }

            Button(
                onClick = {
                    navController.navigate("handBack")
                },
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = stringResource(id = R.string.hand_back_item))
            }

            Button(onClick = {
                navController.navigateUp()
            }, modifier = Modifier.width(200.dp)) {
                Text(text = stringResource(id = R.string.go_to_homepage))
            }
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen(navController = rememberNavController())
}