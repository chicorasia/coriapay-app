package br.com.chicorialabs.coriapaykt.ui.ajustes

import androidx.lifecycle.ViewModel
import br.com.chicorialabs.coriapaykt.data.Usuario
import br.com.chicorialabs.coriapaykt.data.UsuarioLogado

class AjustesViewModel : ViewModel() {

    private val _usuario = UsuarioLogado.usuario
    val usuario: Usuario
        get() = _usuario

}