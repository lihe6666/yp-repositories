package com.yp2048.repositories.data.api

data class ToolResponse(
    val code: Int = 0,
    val msg: String = "",
    val rows: List<ToolData> = listOf(),
    val total: Int = 0
)

data class HandBackResponse(
    val code: Int = 0,
    val msg: String = "",
    val rows: List<HandBackData> = listOf(),
    val total: Int = 0
)