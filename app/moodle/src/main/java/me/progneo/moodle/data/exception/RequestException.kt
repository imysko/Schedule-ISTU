package me.progneo.moodle.data.exception

internal class RequestException(val code: Int, message: String) : Throwable(message)
