package com.yp2048.repositories.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.R
import com.yp2048.repositories.presentation.handback.HandBackViewModel

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HandBackViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

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

            Button(
                onClick = {
                    viewModel.updateDoorState()
                },
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = stringResource(id = R.string.into_storage_room))
            }

            Button(
                onClick = {
                    navController.navigate("pickUpBoard")
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
                TokenManager.setToken(null)
                
                navController.popBackStack("Main", false)
            }, modifier = Modifier.width(200.dp)) {
                Text(text = stringResource(id = R.string.go_to_homepage))
            }
        }
    }

    if (uiState.isLoading) {
        // Loading
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    if (uiState.userMessage?.isNotEmpty() == true) {
        // Show toast
        Toast.makeText(
            context,
            uiState.userMessage,
            Toast.LENGTH_SHORT
        ).show()

        viewModel.resetUserMessage()
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen(navController = rememberNavController())
}