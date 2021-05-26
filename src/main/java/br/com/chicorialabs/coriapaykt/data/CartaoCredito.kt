package br.com.chicorialabs.coriapaykt.data

data class CartaoCredito(
    val bandeiraCartao: BandeiraCartao = BandeiraCartao.VISA,
    val numeroCartao: String = "",
    val nomeTitular: String = "",
    val codigoSeguranca: String = "123",
    val dataExpiracao: String ="",
    val numeroToken: String ="",
    val usuario: Usuario = Usuario(),
    val isSalvar: Boolean = false

)
