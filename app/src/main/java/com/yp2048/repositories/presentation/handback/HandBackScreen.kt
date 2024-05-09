package com.yp2048.repositories.presentation.handback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.R
import com.yp2048.repositories.data.api.HandBackData
import com.yp2048.repositories.presentation.components.HandBackTable

@Composable
fun HandBackScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HandBackViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchHandBackItems()
    }

    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = "领取记录")
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.LightGray), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier.weight(0.25F),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "名称")
            }
            Column(
                modifier = modifier.weight(0.25F),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "领取数量")
            }
            Column(
                modifier = modifier.weight(0.25F),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "物品图片")
            }
            Column(
                modifier = modifier.weight(0.25F),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "归还数量")
            }
        }

        Column(
            modifier = modifier
                .weight(1f)
                .verticalScroll(state = rememberScrollState(), enabled = true)
        ) {
            // 请在这里将Table 列表填充 为 viewModel.fetchPositionTools("1") 的数据

            val temp = mutableListOf<HandBackData>()
            for (i in 1..100) {
                if (uiState.data.isNotEmpty()) {
                    temp.add(uiState.data[0])
                }
            }

            HandBackTable(tableData = temp)
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
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

    if (uiState.isLoading) {
        // Show loading dialog
        Box(
            modifier = modifier.fillMaxSize().background(Color.Gray.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun HandBackScreenPreview() {
    HandBackScreen(navController = rememberNavController())
}