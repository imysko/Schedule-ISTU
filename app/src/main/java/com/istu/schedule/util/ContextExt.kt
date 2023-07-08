package com.istu.schedule.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.sendMail(to: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (_: ActivityNotFoundException) {
    } catch (_: Throwable) {
    }
}

fun Context.dial(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (_: Throwable) {
    }
}
