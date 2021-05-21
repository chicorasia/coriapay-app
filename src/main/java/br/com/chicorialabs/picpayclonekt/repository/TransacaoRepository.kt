package br.com.chicorialabs.picpayclonekt.repository

import br.com.chicorialabs.picpayclonekt.data.transacao.Transacao
import br.com.chicorialabs.picpayclonekt.extension.toModel
import br.com.chicorialabs.picpayclonekt.service.ApiService

interface TransacaoRepository {

    suspend fun getTransacoes(login: String) : List<Transacao>

    suspend fun getSaldo(login: String) : Double

}

class TransacaoRepositoryImpl(private val apiService: ApiService) : TransacaoRepository {

    override suspend fun getTransacoes(login: String): List<Transacao> =
        apiService.getTransacoes(login).content.map {
            it.toModel()
        }.toList()

    override suspend fun getSaldo(login: String): Double =
        apiService.getSaldo(login).saldo

}