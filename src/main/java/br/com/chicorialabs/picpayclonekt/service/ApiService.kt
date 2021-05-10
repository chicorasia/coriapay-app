package br.com.chicorialabs.picpayclonekt.service

import br.com.chicorialabs.picpayclonekt.data.Usuario
import br.com.chicorialabs.picpayclonekt.data.transacao.Transacao
import retrofit2.http.*

interface ApiService {

    @GET("/usuarios/contatos/")
    suspend fun getContatos(@Query("login") login: String) : List<Usuario>

    @POST("/transacoes")
    suspend fun realizarTransacao(@Body transacao: Transacao) : Transacao

    @GET("/usuarios/{login}/saldo")
    suspend fun getSaldo(@Path("login") login: String) : Usuario
}