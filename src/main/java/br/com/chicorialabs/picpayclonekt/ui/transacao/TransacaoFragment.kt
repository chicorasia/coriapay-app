package br.com.chicorialabs.picpayclonekt.ui.transacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.chicorialabs.picpayclonekt.databinding.FragmentHomeBinding
import br.com.chicorialabs.picpayclonekt.databinding.FragmentTransacaoBinding

class TransacaoFragment : Fragment() {

    private lateinit var binding: FragmentTransacaoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransacaoBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}