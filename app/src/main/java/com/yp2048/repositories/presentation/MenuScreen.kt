package com.yp2048.repositories.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MenuScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val context: Context = LocalContext.current
    val buttonColors = ButtonDefaults.buttonColors(
        MaterialTheme.colorScheme.secondary,
    )

    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {

            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "门已开，欢迎进入库房！",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                colors = buttonColors,
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "进入库房")
            }

            Button(
                onClick = {
                    navController.navigate("pickUp")
                },
                colors = buttonColors,
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "领取物品")
            }

            Button(
                onClick = {
                    navController.navigate("handBack")
                },
                colors = buttonColors,
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "归还物品")
            }

            Button(onClick = {
                navController.navigate("Main")
            }, colors = buttonColors, modifier = Modifier.width(200.dp)) {
                Text(text = "返回首页")
            }
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen(navController = rememberNavController())
}