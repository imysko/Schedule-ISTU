package me.progneo.campus.data.exception

internal class RequestException(val code: Int, message: String) : Throwable(message)
