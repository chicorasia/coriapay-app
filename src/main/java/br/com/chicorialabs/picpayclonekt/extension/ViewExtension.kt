package br.com.chicorialabs.picpayclonekt.extension

import android.view.View

fun View.mostra() { this.visibility = View.VISIBLE }

fun View.esconde() { this.visibility = View.INVISIBLE }

fun View.elimina() { this.visibility = View.GONE }