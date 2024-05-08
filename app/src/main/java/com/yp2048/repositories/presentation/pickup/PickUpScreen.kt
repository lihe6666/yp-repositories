package com.yp2048.repositories.presentation.pickup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.R
import com.yp2048.repositories.data.api.ToolResponse

@Composable
fun PickUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PickUpViewModel
) {

    val tools by viewModel.tools.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPositionTools("1")
    }

    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .weight(1f)
                .background(Color.Red)
                .verticalScroll(state = rememberScrollState(), enabled = true)
        ) {
            // 请在这里将Table 列表填充 为 viewModel.fetchPositionTools("1") 的数据

            Table(tableData = tools ?: ToolResponse())
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
//                navController.navigateUp()
                viewModel.fetchPositionTools("1")
            }, modifier = modifier.width(125.dp)) {
                Text(text = stringResource(id = R.string.go_back_login))
            }

            Button(onClick = {
                navController.navigate("Main") {
                    popUpTo(0)
                }
            }, modifier = modifier.width(125.dp)) {
                Text(text = stringResource(id = R.string.quit))
            }
        }
    }
}

@Composable
fun Table(
    modifier: Modifier = Modifier,
    tableData: ToolResponse
) {

    tableData.rows.forEach { rowData ->
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = rowData.fullName)
        }
    }
}

@Preview
@Composable
fun PickUpScreenPreview() {
    PickUpScreen(
        navController = rememberNavController(),
        viewModel = PickUpViewModel()
    )
}