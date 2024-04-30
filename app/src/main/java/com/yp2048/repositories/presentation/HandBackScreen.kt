package com.yp2048.repositories.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.R

@Composable
fun HandBackScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier.weight(1f)) {

        }

        Row(modifier = modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {
//                navController.navigateUp()
            }) {
                Text(text = stringResource(id = R.string.into_storage_room))
            }

            Button(onClick = {
                navController.navigate("HandBackGuide")
            }) {
                Text(text = stringResource(id = R.string.hand_back_item))
            }

            Button(onClick = {
                navController.navigate("Main")
            }) {
                Text(text = stringResource(id = R.string.go_to_homepage))
            }
        }
    }
}

@Preview
@Composable
fun HandBackScreenPreview() {
    HandBackScreen(navController = rememberNavController())
}