package br.com.chicorialabs.coriapaykt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.coriapaykt.data.Usuario
import br.com.chicorialabs.coriapaykt.databinding.ItemPagarBinding

class PagarAdapter(private val lista: List<Usuario>,
                   val onClick: (Usuario) -> Unit)
    : RecyclerView.Adapter<PagarAdapter.PagarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagarViewHolder {
        val binding = ItemPagarBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return PagarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagarViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size

    inner class PagarViewHolder(private val binding: ItemPagarBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(usuario: Usuario){
            usuario.let {
                binding.itemLoginTv.text = it.login
                binding.itemFullnameTv.text = it.nomeCompleto
            }
            binding.root.setOnClickListener { onClick(usuario) }
        }

    }

}


