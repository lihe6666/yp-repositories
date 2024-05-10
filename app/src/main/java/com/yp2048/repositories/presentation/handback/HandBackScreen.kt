package com.yp2048.repositories.presentation.handback

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
import com.yp2048.repositories.data.api.HandBackBody
import com.yp2048.repositories.presentation.components.HandBackTable

@Composable
fun HandBackScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HandBackViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchHandBackItems()
    }

    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = "领取记录")
        }

        var packages = mutableMapOf<String, List<HandBackBody>>()

        Column(modifier = modifier.weight(1F)) {
            HandBackTable(tableData = uiState.data, increment = { row, v ->

                if (packages.containsKey(row.storageId)) {
                    // 遍历工具
                    packages[row.storageId]?.forEach {
                        if (it.id == row.id) {
                            it.deviceNumber = v
                            return@forEach
                        }
                    }

                    return@HandBackTable
                }

                val body: MutableList<HandBackBody> = mutableListOf()
                body.add(HandBackBody(id = row.storageId, deviceNumber = v, giveBackId = row.id))
                packages = packages.toMutableMap().apply {
                    put(row.storageId, body)
                }
            }, decrease = { row, v ->
                if (packages.containsKey(row.storageId)) {
                    // 遍历工具
                    packages[row.storageId]?.forEach {
                        if (it.id == row.id) {
                            it.deviceNumber = v
                            return@forEach
                        }
                    }
                    return@HandBackTable
                }
            })
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(onClick = {
                viewModel.fetchHandBackPackages(packages)

            }) {
                Text(text = stringResource(id = R.string.hand_back_item))
            }

            Button(onClick = {
                navController.navigate("Menu")
            }) {
                Text(text = stringResource(id = R.string.go_to_homepage))
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

    if (uiState.userMessage.isNotEmpty()) {
        // Show message dialog
        Toast.makeText(context, uiState.userMessage, Toast.LENGTH_SHORT).show()
    }
}

@Preview
@Composable
fun HandBackScreenPreview() {
    HandBackScreen(navController = rememberNavController())
}