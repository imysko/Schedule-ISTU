package me.progneo.campus.util

fun String.removeBracketBlocks(): String {
    return this.replace(Regex("\\[.*?\\]"), "")
}
