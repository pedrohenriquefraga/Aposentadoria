package com.example.aposentadoria_pedro

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.aposentadoria_pedro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarSpinner()

        binding.btnCalcular.setOnClickListener {
            calcularAposentadoria()
        }

        binding.btnLimpar.setOnClickListener {
            limparCampos()
        }
    }

    private fun configurarSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.array_opcoes_genero,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGenero.adapter = adapter
    }

    private fun calcularAposentadoria() {
        val idadeTexto = binding.edtIdade.text.toString()

        if (idadeTexto.isEmpty()) {
            binding.layoutIdade.error = "Informe sua idade."
            return
        }

        binding.layoutIdade.error = null

        val idade = idadeTexto.toInt()

        // Posição 0 = Masculino, posição 1 = Feminino
        val idadeMinima = if (binding.spinnerGenero.selectedItemPosition == 0) 65 else 62

        val anosRestantes = idadeMinima - idade

        binding.txtResultado.text = if (anosRestantes <= 0) {
            "Você já pode se aposentar!"
        } else {
            "Faltam $anosRestantes anos para você se aposentar."
        }
    }

    private fun limparCampos() {
        binding.edtIdade.text?.clear()
        binding.spinnerGenero.setSelection(0)
        binding.txtResultado.setText(R.string.txt_resultado_padrao)
        binding.layoutIdade.error = null
    }
}