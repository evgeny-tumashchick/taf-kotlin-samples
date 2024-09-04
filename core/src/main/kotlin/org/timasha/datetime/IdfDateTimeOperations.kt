package org.timasha.datetime

import java.text.SimpleDateFormat
import java.util.*

fun getTodayDate(): String = SimpleDateFormat("yyyyMMdd").format(Date())