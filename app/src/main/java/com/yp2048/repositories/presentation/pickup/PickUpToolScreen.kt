package com.yp2048.repositories.presentation.pickup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.R
import com.yp2048.repositories.presentation.components.PickUpTable
import com.yp2048.repositories.presentation.components.updatePickUpPackage

@Composable
fun PickUpToolScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PickUpToolViewModel = viewModel(),
    id: String? = null
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if (id != null) {
            viewModel.updateTools(id = id)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        val devices = mutableMapOf<String, Device>()
        val packages = mutableMapOf<String, MutableList<Device>>()

        Column(modifier = modifier.weight(1f)) {

            PickUpTable(tableData = uiState.data, increment = { row, v ->
                updatePickUpPackage(packages, devices, row, v.toString())
            }, decrease = { row, v ->
                updatePickUpPackage(packages, devices, row, v.toString())
            })
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                modifier = modifier.width(150.dp),
                enabled = uiState.data.isNotEmpty(),
                onClick = {
                    viewModel.updatePickUpPackages(packages)
                }) {
                Text(text = stringResource(id = R.string.apply))
            }

            Button(
                modifier = modifier.width(150.dp),
                onClick = {
                    navController.navigate("Menu")
                }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    }

    if (uiState.isLoading) {
        // Show loading dialog
        Box(
            modifier = modifier
                .clickable {
                }
                .fillMaxSize()
                .background(Color.Gray.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    if (uiState.userMessage?.isNotEmpty() == true) {
        // Show message dialog
        Toast.makeText(context, uiState.userMessage, Toast.LENGTH_SHORT).show()

        viewModel.resetUserMessage()
    }
}

@Preview
@Composable
fun PickUpToolScreenPreview() {
    PickUpToolScreen(
        navController = rememberNavController()
    )
}