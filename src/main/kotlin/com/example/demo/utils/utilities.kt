package com.example.demo.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }

fun Long.toLocalDateTime(): LocalDateTime = Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDateTime()

fun LocalDateTime.toTimestamp(): Long = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()