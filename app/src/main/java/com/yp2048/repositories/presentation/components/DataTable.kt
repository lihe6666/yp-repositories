package com.yp2048.repositories.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yp2048.repositories.data.api.HandBackData
import com.yp2048.repositories.data.api.PickUpData

@Composable
fun HandBackTable(
    modifier: Modifier = Modifier,
    tableData: List<HandBackData>,
    increment: (row: HandBackData, v: Int) -> Unit,
    decrease: (row: HandBackData, v: Int) -> Unit
) {

    val header: List<String> = listOf(
        "名称", "领取数量", "物品图片", "归还数量"
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.LightGray), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            header.forEach {
                Column(
                    modifier = modifier.weight(0.25F),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = it)
                }
            }
        }

        Column(
            modifier = modifier
                .verticalScroll(state = rememberScrollState(), enabled = true)
        ) {
            // 请在这里将Table 列表填充 为 viewModel.fetchPositionTools("1") 的数据

            tableData.forEach { row ->
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    var value by remember {
                        mutableIntStateOf(0)
                    }

                    Column(
                        modifier = modifier
                            .fillMaxHeight()
                            .weight(0.25F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = row.fullName)
                    }
                    Column(
                        modifier = modifier.weight(0.25F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = row.deviceNumber.toString())
                    }
                    Column(
                        modifier = modifier.weight(0.25F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "点击预览")
                    }
                    Column(
                        modifier = modifier.weight(0.25F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            TextButton(
                                modifier = modifier.size(28.dp),
                                contentPadding = PaddingValues(0.dp),
                                onClick = {
                                    if (value > 0) {
                                        decrease(row, --value)
                                    }
                                }) {
                                Text(text = "-")
                            }
                            Text(text = "$value")
                            TextButton(
                                modifier = modifier.size(28.dp),
                                contentPadding = PaddingValues(0.dp),
                                onClick = {
                                    if (value < row.deviceNumber) {
                                        increment(row, ++value)
                                    }
                                }) {
                                Text(text = "+")
                            }
                        }

                    }
                }
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun PickUpTable(
    modifier: Modifier = Modifier,
    tableData: List<PickUpData>,
    increment: (row: PickUpData, v: Int) -> Unit,
    decrease: (row: PickUpData, v: Int) -> Unit
) {

    val header: List<String> = listOf(
        "名称", "剩余数量", "物品图片", "领取数量"
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.LightGray), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            header.forEach {
                Column(
                    modifier = modifier.weight(0.25F),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = it)
                }
            }
        }

        Column(
            modifier = modifier
                .verticalScroll(state = rememberScrollState(), enabled = true)
        ) {
            // 请在这里将Table 列表填充 为 viewModel.fetchPositionTools("1") 的数据

            tableData.forEach { row ->
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    var value by remember {
                        mutableIntStateOf(0)
                    }

                    Column(
                        modifier = modifier
                            .fillMaxHeight()
                            .weight(0.25F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = row.fullName)
                    }
                    Column(
                        modifier = modifier.weight(0.25F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = row.quantityInStock)
                    }
                    Column(
                        modifier = modifier.weight(0.25F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "点击预览")
                    }
                    Column(
                        modifier = modifier.weight(0.25F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            TextButton(
                                modifier = modifier.size(28.dp),
                                contentPadding = PaddingValues(0.dp),
                                onClick = {
                                    if (value > 0) {
                                        decrease(row, --value)
                                    }
                                }) {
                                Text(text = "-")
                            }
                            Text(text = "$value")
                            TextButton(
                                modifier = modifier.size(28.dp),
                                contentPadding = PaddingValues(0.dp),
                                onClick = {
                                    if (value < row.quantityInStock.toInt()) {
                                        increment(row, ++value)
                                    }
                                }) {
                                Text(text = "+")
                            }
                        }
                    }
                }
                HorizontalDivider()
            }
        }
    }
}