package br.com.chicorialabs.picpayclonekt.ui.ajustes

import androidx.lifecycle.ViewModel
import br.com.chicorialabs.picpayclonekt.data.Usuario
import br.com.chicorialabs.picpayclonekt.data.UsuarioLogado

class AjustesViewModel : ViewModel() {

    private val _usuario = UsuarioLogado.usuario
    val usuario: Usuario
        get() = _usuario


}