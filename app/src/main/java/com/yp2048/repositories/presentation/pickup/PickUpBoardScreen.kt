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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.R

@Composable
fun PickUpBoardScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PickUpBoardViewModel = viewModel() as PickUpBoardViewModel
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
            // navController.navigate("PickUpTool/1")

            println("uiState.data.list.size: ${uiState.data.list.size}")
            Box(modifier = modifier) {
                Warehouse(
                    modifier = modifier,
                    rows = 10
                ) {
                    for (j in 0..uiState.data.list.size) {
                        for (k in uiState.data.storageRackList) {
                            if (j.toLong() == (k.x * 10 + k.y)) {
                                Box(
                                    modifier = modifier
                                        .size(35.dp)
                                        .clickable {
                                            if (k.positionType == "1") {
                                                navController.navigate("PickUpTool/${k.id}")
                                            }
                                        }
                                        .background(
                                            when (k.positionType) {
                                                "1" -> Color.Gray
                                                "2" -> Color.Green
                                                "3" -> Color.Magenta
                                                "4" -> Color.Yellow
                                                "5" -> Color(0xFFEFEFEF)
                                                "6" -> Color.Red
                                                else -> Color.LightGray
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(

                                        text = k.sortName,
                                        fontSize = 10.sp,
                                        lineHeight = 10.sp
                                    )
                                }
                                continue
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

@Composable
fun Warehouse(
    modifier: Modifier = Modifier,
    rows: Int = 10,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measures, constraints ->
        // measure and position children given constraints logic here
        // ...
        val placeableGroup = measures.map { measurable ->
            // Measure each children
            measurable.measure(constraints)
        }

        // Calculate total width and height of all items
        val totalWidth = placeableGroup.sumOf { it.width } / rows

        // Calculate parent layout center
        val centerX = (constraints.maxWidth - totalWidth) / 2

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Track the y co-ord we have placed children up to
            var xPosition = centerX
            var yPosition = 0
            // Place children in the parent layout
            var quantityInRow = 0

            placeableGroup.forEach { placeable ->

                // Position item on the screen
                if (xPosition > constraints.maxWidth || quantityInRow >= rows) {
                    yPosition += placeable.height
                    xPosition = centerX
                    quantityInRow = 0
                }

                placeable.placeRelative(x = xPosition, y = yPosition)

                // Record the y co-ord placed up to
                xPosition += placeable.width
                quantityInRow++
            }
        }
    }
}

@Preview
@Composable
fun PickUpBoardScreenPreview() {
    PickUpBoardScreen(
        navController = rememberNavController()
    )
}