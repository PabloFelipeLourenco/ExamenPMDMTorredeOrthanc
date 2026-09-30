package com.isengard.fruegas

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editText = findViewById<EditText>(R.id.editTextText)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val checkBoxAntorcha = findViewById<CheckBox>(R.id.checkBox)
        val boton = findViewById<ImageButton>(R.id.imageButton)

        editText.requestFocus()

        editText.setOnFocusChangeListener { _, tieneFoco ->
            if (!tieneFoco) {
                if (editText.text.toString().isEmpty()) {
                    editText.error = "Error: El soldado no puede ser anónimo"
                }
            }
        }

        boton.setOnClickListener {
            if (editText.text.toString().isEmpty()) {
                editText.error = "Error: El soldado no puede ser anónimo"
            } else {
                val unidadSeleccionada = spinner.selectedItem.toString()
                val equipamientoSeleccionado = radioGroup.checkedRadioButtonId
                val antorchaSeleccionada = checkBoxAntorcha.isChecked

                Toast.makeText(
                    this,
                    "¡Unidad ${editText.text} enviada al Abismo de Helm!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val unidad = resources.getStringArray(R.array.unidad)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            unidad
        )

        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinner.adapter = adaptador


    }
}