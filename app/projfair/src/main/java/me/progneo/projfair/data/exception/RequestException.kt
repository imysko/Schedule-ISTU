package me.progneo.projfair.data.exception

internal class RequestException(val code: Int, message: String) : Throwable(message)
