package com.istu.schedule.data.api.entities

class RequestException(val code: Int, message: String) : Throwable(message)
