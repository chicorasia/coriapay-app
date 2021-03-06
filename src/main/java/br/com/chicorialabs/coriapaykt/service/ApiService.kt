package br.com.chicorialabs.coriapaykt.service

import br.com.chicorialabs.coriapaykt.data.Login
import br.com.chicorialabs.coriapaykt.data.PageTransacao
import br.com.chicorialabs.coriapaykt.data.Token
import br.com.chicorialabs.coriapaykt.data.Usuario
import br.com.chicorialabs.coriapaykt.data.transacao.Transacao
import retrofit2.http.*

interface ApiService {

    @GET("/usuarios/contatos")
    suspend fun getContatos(@Query("login") login: String) : List<Usuario>

    @POST("/transacoes")
    suspend fun realizarTransacao(@Body transacao: Transacao) : Transacao

    @POST("/autenticacao")
    suspend fun autentica(@Body login: Login) : Token

    @GET("/usuarios/{login}/saldo")
    suspend fun getSaldo(@Path("login") login: String) : Usuario

    @GET("/transacoes")
    suspend fun getTransacoes(@Query("login") login: String): PageTransacao

//   Deprecated?
    @GET("/usuarios/{login}")
    suspend fun getUsuario(@Path("login") login: String) : Usuario
}