package com.mazzeom.app.armstrong.libs.api.response

import com.mazzeom.app.armstrong.libs.api.dto.RecordDTO

data class GetRecordByProfileIdAndDateResponse (
    var records: Array<RecordDTO>
)