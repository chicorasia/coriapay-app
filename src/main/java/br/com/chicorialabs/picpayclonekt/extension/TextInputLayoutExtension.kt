package br.com.chicorialabs.picpayclonekt.extension

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.getString() = this.editText?.text.toString()