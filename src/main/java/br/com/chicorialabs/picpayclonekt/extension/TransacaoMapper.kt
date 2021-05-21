package br.com.chicorialabs.picpayclonekt.extension

import br.com.chicorialabs.picpayclonekt.data.CartaoCredito
import br.com.chicorialabs.picpayclonekt.data.Usuario
import br.com.chicorialabs.picpayclonekt.data.transacao.Transacao
import br.com.chicorialabs.picpayclonekt.data.transacao.TransacaoLocal
import br.com.chicorialabs.picpayclonekt.data.transacao.TransacaoNetwork

fun TransacaoNetwork.toModel(): Transacao = Transacao(
    codigo = codigo.orEmpty(),
    origem = origem ?: Usuario(),
    destino = destino ?: Usuario(),
    dataHora = dataHora.orEmpty(),
    isCartaoCredito = isCartaoCredito ?: false,
    valor = valor ?: 0.0,
    cartaoCredito = cartaoCredito ?: CartaoCredito()
)

fun Transacao.toLocal() : TransacaoLocal = TransacaoLocal(
    codigo = codigo,
    origem = origem.login,
    destino = destino.login,
    dataHora = dataHora,
    isCartaoCredito = isCartaoCredito,
    valor = valor
)

fun List<Transacao>.toLocal() : List<TransacaoLocal> =
    this.map { it.toLocal() }.toList()
