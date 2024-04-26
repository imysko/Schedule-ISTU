package me.progneo.campus.util

internal fun String.removeBracketBlocks(): String {
    return this.replace(Regex("\\[.*?\\]"), "")
}
