package com.mazzeom.app.armstrong.libs.api.response

import com.mazzeom.app.armstrong.libs.api.dto.RecordDTO

data class GetPushUpByProfileIdAndDateResponse (
    var records: Array<RecordDTO>
)