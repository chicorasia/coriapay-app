package br.com.chicorialabs.picpayclonekt.service

import br.com.chicorialabs.picpayclonekt.data.Usuario
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/usuarios/contatos/")
    suspend fun getContatos(@Query("login") login: String) : List<Usuario>

}