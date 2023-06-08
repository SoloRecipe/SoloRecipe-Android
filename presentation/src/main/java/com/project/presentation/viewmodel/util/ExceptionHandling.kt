package com.project.presentation.viewmodel.util

import com.project.domain.exception.BadRequestException
import com.project.domain.exception.ConflictException
import com.project.domain.exception.ForbiddenException
import com.project.domain.exception.NotFoundException
import com.project.domain.exception.ServerException
import com.project.domain.exception.TimeOutException
import com.project.domain.exception.TokenExpiredException
import com.project.domain.exception.UnauthorizedException

fun Throwable.exceptionHandling(
    badRequestAction: () -> Unit = {},
    unauthorizedAction: () -> Unit = {},
    forbiddenAction: () -> Unit = {},
    notFoundAction: () -> Unit = {},
    timeOutAction: () -> Unit = {},
    conflictAction: () -> Unit = {},
    serverAction: () -> Unit = {},
    unknownAction: () -> Unit = {},
) {
    when (this) {
        is BadRequestException -> badRequestAction()
        is UnauthorizedException, is TokenExpiredException -> unauthorizedAction()
        is ForbiddenException -> forbiddenAction()
        is NotFoundException -> notFoundAction()
        is TimeOutException -> timeOutAction()
        is ConflictException -> conflictAction()
        is ServerException -> serverAction()
        else -> unknownAction()
    }
}
