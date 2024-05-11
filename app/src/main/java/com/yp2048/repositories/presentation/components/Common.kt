package com.yp2048.repositories.presentation.components

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