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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yp2048.repositories.data.api.HandBackData
import com.yp2048.repositories.data.api.ToolData

@Composable
fun Table(
    modifier: Modifier = Modifier,
    tableData: List<ToolData>
) {
    TableHeader(modifier = modifier)

    tableData.forEach { row ->
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(modifier = modifier.widthIn(100.dp)) {
                Text(text = row.fullName)
            }
            Column(modifier = modifier.width(100.dp)) {
                Text(text = row.quantityInStock)
            }
            Column(modifier = modifier.width(100.dp)) {
                Text(text = "无")
            }
            Column(modifier = modifier.width(100.dp)) {
                Text(text = row.storageRack.toString())
            }
        }
        HorizontalDivider()
    }
}

@Composable
fun HandBackTable(
    modifier: Modifier = Modifier,
    tableData: List<HandBackData>
) {
    tableData.forEach { row ->
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                        onClick = { /*TODO*/ }) {
                        Text(text = "-")
                    }
                    Text(text = "0")
                    TextButton(
                        modifier = modifier.size(28.dp),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { /*TODO*/ }) {
                        Text(text = "+")
                    }
                }

            }
        }
        HorizontalDivider()
    }
}

@Composable
fun TableHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Gray), horizontalArrangement = Arrangement.SpaceBetween
    ) {
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
}