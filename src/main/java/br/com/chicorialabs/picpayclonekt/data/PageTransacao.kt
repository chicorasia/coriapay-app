package br.com.chicorialabs.picpayclonekt.data

import br.com.chicorialabs.picpayclonekt.data.transacao.Transacao

data class PageTransacao(
    val content: List<Transacao>,
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