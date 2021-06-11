package com.mazzeom.app.armstrong.libs.api.dto

data class CreateRecordDTO(
    var date: String,
    var type: RecordType,
    var count: Int,
    var order: Int
)
