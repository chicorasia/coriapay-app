package br.com.chicorialabs.picpayclonekt.ui.pagar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.chicorialabs.picpayclonekt.databinding.FragmentDashboardBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PagarFragment : Fragment() {

    private val pagarViewModel: PagarViewModel by viewModel()
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        val textView: TextView = binding.textDashboard
        pagarViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return binding.root
    }
}