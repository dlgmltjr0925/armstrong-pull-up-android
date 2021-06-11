package com.mazzeom.app.armstrong.libs.api.request

import com.mazzeom.app.armstrong.libs.api.dto.CreateRecordDTO

data class PostRecordByProfileIdRequestBody(
    val record: CreateRecordDTO
)