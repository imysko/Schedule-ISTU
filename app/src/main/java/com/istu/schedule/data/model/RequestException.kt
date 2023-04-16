package com.istu.schedule.data.model

class RequestException(val code: Int, message: String) : Throwable(message)
