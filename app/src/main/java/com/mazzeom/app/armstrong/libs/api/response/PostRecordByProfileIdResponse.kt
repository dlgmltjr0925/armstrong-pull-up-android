package com.mazzeom.app.armstrong.libs.api.response

import com.mazzeom.app.armstrong.libs.api.dto.RecordDTO

data class PostRecordByProfileIdResponse(
    var record: RecordDTO?,
    var id: Int?
)
