package com.project.domain.exception

import java.io.IOException

class TokenExpiredException: IOException() {
    override val message: String
        get() = "토큰이 만료되었습니다. 다시 로그인해 주세요."
}