package br.com.chicorialabs.picpayclonekt

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.chicorialabs.picpayclonekt.databinding.ActivityMainBinding
import br.com.chicorialabs.picpayclonekt.ui.componente.ComponenteViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val componenteViewModel: ComponenteViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        componenteViewModel.componentes.observe(this) {
            it?.let { componentes ->
                if(componentes.bottomNavigation) {
                    navView.visibility = View.VISIBLE
                } else {
                    navView.visibility = View.GONE
                }
            }
        }

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }
}