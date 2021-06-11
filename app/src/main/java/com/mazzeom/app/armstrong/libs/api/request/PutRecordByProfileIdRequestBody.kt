package com.mazzeom.app.armstrong.libs.api.request

import com.mazzeom.app.armstrong.libs.api.dto.RecordDTO

data class PutRecordByProfileIdRequestBody(
    val record: RecordDTO
)