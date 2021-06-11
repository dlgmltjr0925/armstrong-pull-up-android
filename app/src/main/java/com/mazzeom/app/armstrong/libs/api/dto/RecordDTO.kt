package com.mazzeom.app.armstrong.libs.api.dto

enum class RecordType(val value: String) {
    MAX_COUNT("MAX_COUNT"),
    PYRAMID("PYRAMID"),
    THREE_GRIP("THREE_GRIP"),
    MAX_SET("MAX_SET"),
    PUSH_UP("PUSH_UP")
}

data class RecordDTO(
    var id: Int,
    var date: String,
    var type: RecordType,
    var count: Int,
    var order: Int
)
