package br.com.chicorialabs.coriapaykt.extension

import java.text.NumberFormat
import java.util.*

fun Double?.formatarMoeda(): String =
    NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        .format(this) ?: "R$ 0,00"