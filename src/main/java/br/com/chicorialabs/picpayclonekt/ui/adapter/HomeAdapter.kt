package br.com.chicorialabs.picpayclonekt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.picpayclonekt.data.transacao.Transacao
import br.com.chicorialabs.picpayclonekt.databinding.ItemHomeBinding
import br.com.chicorialabs.picpayclonekt.extension.formatarMoeda

class HomeAdapter(private val lista: List<Transacao>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size


    inner class HomeViewHolder(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(transacao: Transacao) {
            with(binding) {
                transacao.let{
                    itemInicialTv.text = transacao.origem.nomeCompleto.first().toString()
                    itemOrigemTv.text = transacao.origem.login
                    itemDestinoTv.text = transacao.destino.login
                    itemValorTv.text = transacao.valor.formatarMoeda()
                    itemDatahoraTv.text = transacao.dataHora
                }
            }
        }

    }
}