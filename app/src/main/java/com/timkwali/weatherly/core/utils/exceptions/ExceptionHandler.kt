package com.timkwali.weatherly.core.utils.exceptions

import com.timkwali.weatherly.core.utils.Constants.BASE_URL
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun Exception.handleException(): Throwable {
   return when(this) {
        is HttpException -> {
            if(code() in 400 .. 499) UserException()
            else if(code() in 500 .. 599) ServerException()
            else Throwable("Error making request")
        }
        is TimeoutException -> ConnectionTimeoutException()
        is UnknownHostException -> UrlException(BASE_URL)
        is IOException -> OperationException()
        else -> GeneralException()
    }
}