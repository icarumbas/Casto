package com.icarumbas.casto.platformSpecific.utils

actual fun roundDecimal(number: Number, digits: Int): String {
    return "%.${digits}f".format(number)
}