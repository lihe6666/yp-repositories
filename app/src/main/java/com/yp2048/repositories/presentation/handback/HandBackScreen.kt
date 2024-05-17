package com.yp2048.repositories.presentation.handback

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.R
import com.yp2048.repositories.data.api.HandBackBody
import com.yp2048.repositories.presentation.components.CircleLoading
import com.yp2048.repositories.presentation.components.HandBackTable
import com.yp2048.repositories.presentation.components.updateHandBackPackage

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

        val packages = mutableMapOf<String, MutableList<HandBackBody>>()

        Column(modifier = modifier.weight(1F)) {
            HandBackTable(tableData = uiState.data, increment = { row, v ->
                updateHandBackPackage(packages, row, v.toString())
            }, decrease = { row, v ->
                updateHandBackPackage(packages, row, v.toString())
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
                    viewModel.fetchHandBackPackages(packages)
                }) {
                Text(text = stringResource(id = R.string.hand_back_item))
            }

            Button(
                modifier = modifier.width(150.dp),
                onClick = {
                    navController.popBackStack("Menu", false)
                }) {
                Text(text = stringResource(id = R.string.go_to_homepage))
            }
        }
    }

    if (uiState.isLoading) {
        // Show loading dialog
        CircleLoading()
    }

    if (uiState.userMessage?.isNotEmpty() == true) {
        // Show message dialog
        Toast.makeText(context, uiState.userMessage, Toast.LENGTH_SHORT).show()
        viewModel.resetUserMessage()
    }
}

@Preview
@Composable
fun HandBackScreenPreview() {
    HandBackScreen(navController = rememberNavController())
}