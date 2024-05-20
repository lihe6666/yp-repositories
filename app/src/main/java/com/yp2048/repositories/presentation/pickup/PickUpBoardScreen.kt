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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.R
import com.yp2048.repositories.presentation.components.CircleLoading
import com.yp2048.repositories.presentation.components.Warehouse

@Composable
fun PickUpBoardScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PickUpBoardViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchWarehouseInformation()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .padding(16.dp)
        ) {

            Box(modifier = modifier.fillMaxSize()) {
                if (uiState.data.storageRackList.isNotEmpty()) {
                    val displayMetrics = context.resources.displayMetrics
                    val dpWidth = displayMetrics.widthPixels / displayMetrics.density - 32
                    val dpHeight = displayMetrics.heightPixels / displayMetrics.density - 32 - 200

                    var rows = 0
                    var total = 0

                    uiState.data.list.forEach {
                        if (it.y.toInt() == 0) {
                            rows++;
                        }
                        total++
                    }

                    val elementWidth = dpWidth / rows
                    val elementHeight = dpHeight / (total / rows)
                    Warehouse(
                        modifier = modifier,
                        rows = rows
                    ) {
                        for (j in 0..uiState.data.list.size) {
                            for (k in uiState.data.storageRackList) {
                                if (j.toLong() == (k.x * 10 + k.y)) {
                                    Box(
                                        modifier = modifier
                                            .width(elementWidth.dp)
                                            .height(elementHeight.dp)
                                            .clickable {
                                                if (k.positionType == "1"
                                                    || k.positionType == "2"
                                                    || k.positionType == "3"
                                                    || k.positionType == "4") {

                                                    navController.navigate("PickUpTool/${k.id}")
                                                }
                                            }
                                            .background(
                                                when (k.positionType) {
                                                    "1" -> Color.Gray    // 普通
                                                    "2" -> Color.Cyan    // RFID
                                                    "3" -> Color.Magenta // 钥匙
                                                    "4" -> Color.Yellow  // 工器具
                                                    "5" -> Color(0xFFEFEFEF)
                                                    "6" -> Color.Red
                                                    else -> Color.LightGray
                                                }
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (k.positionType == "5") {
                                            Text(
                                                text = "\uD83D\uDC63\n",
                                                fontSize = 10.sp,
                                                lineHeight = 10.sp,
                                                color = Color.LightGray
                                            )
                                        } else {
                                            Text(
                                                text = k.sortName,
                                                fontSize = 10.sp,
                                                lineHeight = 10.sp,
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }

                                    }
                                    continue
                                }
                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                navController.navigateUp()
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

    if (uiState.isLoading) {
        CircleLoading()
    }

    uiState.userMessage?.let {
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
fun PickUpBoardScreenPreview() {
    PickUpBoardScreen(
        navController = rememberNavController()
    )
}