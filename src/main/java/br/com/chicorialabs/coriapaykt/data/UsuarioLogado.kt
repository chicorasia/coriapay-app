package br.com.chicorialabs.coriapaykt.data

object UsuarioLogado {

    lateinit var usuario: Usuario
    lateinit var token: Token

    fun isUsuarioLogado() = this::token.isInitialized

    fun isUsuarioNaoLogado() = !isUsuarioLogado()

}