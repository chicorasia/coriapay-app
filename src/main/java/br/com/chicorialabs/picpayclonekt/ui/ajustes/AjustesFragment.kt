package br.com.chicorialabs.picpayclonekt.ui.ajustes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.chicorialabs.picpayclonekt.databinding.FragmentAjustesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AjustesFragment : Fragment() {

    private val ajustesViewModel: AjustesViewModel by viewModel()
    private lateinit var binding: FragmentAjustesBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAjustesBinding.inflate(layoutInflater, container, false)
        val textView: TextView = binding.textNotifications
        ajustesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return binding.root
    }
}