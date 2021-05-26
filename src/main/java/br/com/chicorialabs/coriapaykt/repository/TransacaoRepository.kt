package br.com.chicorialabs.coriapaykt.repository

import br.com.chicorialabs.coriapaykt.dao.TransacaoDAO
import br.com.chicorialabs.coriapaykt.data.transacao.Transacao
import br.com.chicorialabs.coriapaykt.extension.toLocal
import br.com.chicorialabs.coriapaykt.extension.toModel
import br.com.chicorialabs.coriapaykt.service.ApiService

interface TransacaoRepository {

    suspend fun getTransacoes(login: String) : List<Transacao>

    suspend fun getSaldo(login: String) : Double

}

class TransacaoRepositoryImpl(
    private val apiService: ApiService,
    private val transacaoDAO: TransacaoDAO
) : TransacaoRepository {

    override suspend fun getTransacoes(login: String): List<Transacao> =
        apiService.getTransacoes(login).content.map {
            it.toModel()
        }.toList().also {
            transacaoDAO.save(it.toLocal())
        }

    override suspend fun getSaldo(login: String): Double =
        apiService.getSaldo(login).saldo

}