package br.com.chicorialabs.picpayclonekt.service

import br.com.chicorialabs.picpayclonekt.data.Usuario
import br.com.chicorialabs.picpayclonekt.data.transacao.Transacao
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("/usuarios/contatos/")
    suspend fun getContatos(@Query("login") login: String) : List<Usuario>

    @POST("/transacoes")
    suspend fun realizarTransacao(@Body transacao: Transacao) : Transacao

}