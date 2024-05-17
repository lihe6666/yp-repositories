package com.yp2048.repositories.presentation.pickup

import android.widget.Toast
import androidx.compose.foundation.Canvas
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
import com.yp2048.repositories.presentation.components.Warehouse

@Composable
fun PickUpGuideScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PickUpGuideViewModel = viewModel(),
    id: String = "0"
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
                .fillMaxSize()
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
                            rows++
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
                                                if (k.positionType == "1") {
                                                    navController.navigate("PickUpTool/${k.id}")
                                                }
                                            }
                                            .background(
                                                when (k.positionType) {
                                                    "1" -> Color.Gray
                                                    "2" -> Color.Cyan
                                                    "3" -> Color.Magenta
                                                    "4" -> Color.Yellow
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

                    val forwardRoute = uiState.data.storageRackList[id.toInt()].route0
                    Canvas(modifier = modifier.background(Color.Blue)) {
                        var i = 0
                        val toWidthPx = (elementWidth * context.resources.displayMetrics.density)
                        val toHeightPx = (elementHeight * context.resources.displayMetrics.density)
                        val toWidthPath =
                            (elementWidth / 2 * context.resources.displayMetrics.density)

                        val toHeightPath =
                            (elementHeight / 2 * context.resources.displayMetrics.density)

                        while (i < forwardRoute.size - 1) {
                            val x = forwardRoute[i].x.toInt() * toWidthPx + toWidthPath
                            val y = forwardRoute[i].y.toInt() * toHeightPx + toHeightPath
                            val nextX = forwardRoute[i + 1].x.toInt() * toWidthPx + toWidthPath
                            val nextY = forwardRoute[i + 1].y.toInt() * toHeightPx + toHeightPath

                            drawLine(
                                color = Color.Green,
                                Offset(x.toFloat(), y.toFloat()),
                                Offset(nextX.toFloat(), nextY.toFloat()),
                                strokeWidth = 10f
                            )
                            i++
                        }
                    }
                }
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.End
        ) {

            Button(onClick = {
                navController.navigate("Main") {
                    popUpTo(0)
                }
            }, modifier = modifier.width(125.dp)) {
                Text(text = stringResource(id = R.string.go_to_homepage))
            }
        }
    }

    if (uiState.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator()
        }
    }

    if (uiState.userMessage.isNotEmpty()) {
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
fun PickUpGuideScreenPreview() {
    PickUpGuideScreen(navController = rememberNavController())
}