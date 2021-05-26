package br.com.chicorialabs.coriapaykt.extension

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.getString() = this.editText?.text.toString()