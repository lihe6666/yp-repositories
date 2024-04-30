package com.yp2048.repositories

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.presentation.HandBackGuideScreen
import com.yp2048.repositories.presentation.HandBackScreen
import com.yp2048.repositories.presentation.MenuScreen
import com.yp2048.repositories.presentation.PickUpGuideScreen
import com.yp2048.repositories.presentation.PickUpScreen
import com.yp2048.repositories.ui.theme.RepositoriesTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasRequiredPermissions()) {
            // Permissions granted, proceed with the app
            ActivityCompat.requestPermissions(
                applicationContext as MainActivity,
                CAMERA_PERMISSIONS,
                0
            )
        }

        setContent {
            RepositoriesTheme(
                dynamicColor = false
            ) {

                val controller = remember {
                    LifecycleCameraController(applicationContext).apply {
                        setEnabledUseCases(
                            CameraController.IMAGE_CAPTURE
                        )
                    }
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(
                                controller = controller,
                                navController = navController,
                                context = applicationContext
                            )
                        }
                        composable("Menu") {
                            MenuScreen(
                                navController = navController
                            )
                        }
                        composable("pickUp") {
                            PickUpScreen(navController = navController)
                        }
                        composable("pickUpGuide") {
                            PickUpGuideScreen(navController = navController)
                        }
                        composable("handBack") {
                            HandBackScreen(navController = navController)
                        }
                        composable("HandBackGuide") {
                            HandBackGuideScreen(navController = navController)
                        }
                        // 在这里添加更多的 composable
                    }

                }
            }
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return CAMERA_PERMISSIONS.all {
            ActivityCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val CAMERA_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    controller: LifecycleCameraController,
    navController: NavController,
    context: Context
) {
    var isScanFace by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = isScanFace) {
        coroutineScope.launch {
            delay(3000)
            isScanFace = true
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        CameraPreview(
            controller = controller,
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {

            IconButton(onClick = {
                controller.cameraSelector =
                    if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                        CameraSelector.DEFAULT_FRONT_CAMERA
                    } else {
                        CameraSelector.DEFAULT_BACK_CAMERA
                    }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.flip_camera),
                    contentDescription = "Take Picture",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = MaterialTheme.colorScheme.outline,
                    disabledContentColor = MaterialTheme.colorScheme.outlineVariant
                ),
                onClick = {
                // 模拟刷脸成功
                coroutineScope.launch {
                    controller.takePicture(
                        ContextCompat.getMainExecutor(context),
                        object : ImageCapture.OnImageCapturedCallback() {
                            override fun onCaptureSuccess(image: ImageProxy) {
                                super.onCaptureSuccess(image)

                                Log.e("Camera", image.toBitmap().toString())
                                navController.navigate("Menu")
                            }

                            override fun onError(exception: ImageCaptureException) {
                                super.onError(exception)

                                Log.e("Camera", "Couldn't take photo: ", exception)
                            }
                        }
                    )
                }

            },
                enabled = isScanFace,
                modifier = Modifier.widthIn(200.dp)) {
                Text(text = stringResource(id = R.string.scan_face))
            }

        }
    }
}

@Composable
fun CameraPreview(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    // 在这里添加相机预览的 composable
    val lifecycleOwner = LocalLifecycleOwner.current

    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(factory = {
            PreviewView(it).apply {
                controller.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                this.controller = controller
                controller.bindToLifecycle(lifecycleOwner)
            }
        }, modifier = modifier)
    }

    DisposableEffect(controller) {
        onDispose {
            controller.unbind()
        }
    }
}