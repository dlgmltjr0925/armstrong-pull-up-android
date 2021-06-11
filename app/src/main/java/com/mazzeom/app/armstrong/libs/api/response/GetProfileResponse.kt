package com.mazzeom.app.armstrong.libs.api.response

import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO

data class GetProfileResponse (
    var profiles: Array<ProfileDTO>
)