package com.mazzeom.app.armstrong.libs.api.dto

enum class RecordType {
    MAX_COUNT,
    PYRAMID,
    THREE_GRIP,
    MAX_SET,
    PUSH_UP
}

data class RecordDTO(
    var id: Int,
    var date: String,
    var type: RecordType,
    var count: Int,
    var order: Int
)
