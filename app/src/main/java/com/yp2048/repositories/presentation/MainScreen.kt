package com.yp2048.repositories.presentation

import android.app.Activity
import android.content.Context
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.yp2048.repositories.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavController,
) {
    var isScanFace by remember {
        mutableStateOf(false)
    }

    val context: Context = LocalContext.current as Activity
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE
            )
        }
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