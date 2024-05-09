package com.yp2048.repositories.presentation.pickup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.R
import com.yp2048.repositories.data.api.ToolData

@Composable
fun PickUpToolScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PickUpToolViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.updateTools("1")
    }

    Table01(navController = navController, uiState = uiState)
}

@Composable
fun Table01(
    modifier: Modifier = Modifier,
    navController: NavController,
    uiState: PickUpUiState
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = modifier
            .fillMaxWidth()
            .background(Color.Gray), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(text = "名称")
            }
            Column {
                Text(text = "剩余数量")
            }
            Column {
                Text(text = "物品图片")
            }
            Column {
                Text(text = "领取数量")
            }
        }
        Column(
            modifier = modifier
                .weight(1f)
                .verticalScroll(state = rememberScrollState(), enabled = true)
        ) {
            // 请在这里将Table 列表填充 为 viewModel.fetchPositionTools("1") 的数据

            Table(tableData = uiState.tools)
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                navController.navigate("PickUpGuide")
            }, modifier = modifier.width(125.dp)) {
                Text(text = stringResource(id = R.string.apply))
            }

            Button(onClick = {
                navController.navigateUp()
            }, modifier = modifier.width(125.dp)) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    }
}

@Composable
fun Table(
    modifier: Modifier = Modifier,
    tableData: List<ToolData>
) {
    tableData.forEach { rowData ->
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(modifier = modifier.widthIn(100.dp)) {
                Text(text = rowData.fullName)
            }
            Column(modifier = modifier.width(100.dp)) {
                Text(text = rowData.quantityInStock)
            }
            Column(modifier = modifier.width(100.dp)) {
                Text(text = "无")
            }
            Column(modifier = modifier.width(100.dp)) {
                Text(text = rowData.storageRack.toString())
            }
        }
        HorizontalDivider()
    }
}

@Preview
@Composable
fun PickUpToolScreenPreview() {
    PickUpToolScreen(
        navController = rememberNavController(),
        viewModel = PickUpToolViewModel()
    )
}