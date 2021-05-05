package br.com.chicorialabs.picpayclonekt.data

object UsuarioLogado {

    lateinit var usuario: Usuario

    fun isUsuarioLogado() = this::usuario.isInitialized

    fun isUsuarioNaoLogado() = !isUsuarioLogado()
}