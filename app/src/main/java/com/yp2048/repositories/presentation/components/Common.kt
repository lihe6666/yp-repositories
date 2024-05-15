package com.yp2048.repositories.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import com.yp2048.repositories.data.api.HandBackBody
import com.yp2048.repositories.data.api.HandBackData
import com.yp2048.repositories.data.api.PickUpData
import com.yp2048.repositories.presentation.pickup.Device

fun updatePickUpPackage(
    packages: MutableMap<String, MutableList<Device>>,
    devices: MutableMap<String, Device>,
    row: PickUpData,
    v: String
) {
    devices[row.id] = Device(row.id, deviceNumber = v)
    packages[row.storageRack.toString()] = devices.values.toMutableList()
}

fun updateHandBackPackage(
    packages: MutableMap<String, MutableList<HandBackBody>>,
    devices: MutableMap<String, HandBackBody>,
    row: HandBackData,
    v: String
) {
    devices[row.id] = HandBackBody(row.storageId, deviceNumber = v.toInt(), giveBackId = row.id)
    packages[row.storageId] = devices.values.toMutableList()
}

@Composable
fun Warehouse(
    modifier: Modifier = Modifier,
    rows: Int = 10,
    content: @Composable () -> Unit,
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
//        val totalWidth = placeableGroup.sumOf { it.width } / rows

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Track the y co-ord we have placed children up to
            var xPosition = 0
            var yPosition = 0
            // Place children in the parent layout

            var quantityInColumn = 0

            placeableGroup.forEach { placeable ->

                if (yPosition > constraints.maxHeight || quantityInColumn >= rows) {
                    xPosition += placeable.width
                    yPosition = 0
                    quantityInColumn = 0
                }

                placeable.placeRelative(x = xPosition, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
                quantityInColumn++
            }
        }
    }
}