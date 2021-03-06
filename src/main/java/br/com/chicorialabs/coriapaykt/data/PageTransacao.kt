package br.com.chicorialabs.coriapaykt.data

import br.com.chicorialabs.coriapaykt.data.transacao.TransacaoNetwork

data class PageTransacao(
    val content: List<TransacaoNetwork>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: Sort,
    val totalElements: Int,
    val totalPages: Int
)