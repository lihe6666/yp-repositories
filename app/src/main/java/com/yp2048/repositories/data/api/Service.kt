package com.yp2048.repositories.data.api

data class PickUpResponse(
    val code: Int = 0,
    val msg: String? = "",
    val rows: List<PickUpData> = listOf(),
    val total: Int = 0
)

data class HandBackResponse(
    val code: Int = 0,
    val msg: String? = "",
    val rows: List<HandBackData> = listOf(),
    val total: Int = 0
)

data class WarehouseResponse(
    val code: Int,
    val msg: String,
    val data: WarehouseData,
)

data class WarehouseData(
    val storeWarehouseBitmapId: String,
    val storeWarehouseBitmapName: String,
    val storeWarehouseBitmapLong: Long,
    val storeWarehouseBitmapWide: Long,
    val storeWarehouseBitmapWidth: Long,
    val storeWarehouseBitmapLength: Long,
    val list: List<Position>,
    val storageRackList: List<StorageRackList>,
)

data class Position(
    val id: String,
    val x: String,
    val y: String,
)

data class StorageRackList(
    val id: String,
    val code: String?,
    val sortName: String,
    val status: String,
    val positionType: String,
    val w: Long,
    val h: Long,
    val x: Long,
    val y: Long,
    val i: String,
    val route0: List<Route0>,
    val route1: List<Route1>,
)

data class Route0(
    val id: Any?,
    val x: String,
    val y: String,
)

data class Route1(
    val id: Any?,
    val x: String,
    val y: String,
)