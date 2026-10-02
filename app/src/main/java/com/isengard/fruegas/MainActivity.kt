package com.isengard.fruegas

import android.os.Bundle
import android.util.Log
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
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        // Buscamos los elementos por su ID
        val editText = findViewById<EditText>(R.id.editTextText)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val checkBoxAntorcha = findViewById<CheckBox>(R.id.checkBox)
        val boton = findViewById<ImageButton>(R.id.imageButton)

        // Obtenemos el array "unidad" que está uardado en strings.xml
        val unidad = resources.getStringArray(R.array.unidad)

        // Creamos un adaptador para mostrar las unidades en el Spinner
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            unidad
        )

        // Indicamos que será un desplegable
        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinner.adapter = adaptador

        // Colocamos el cursor automáticamente en el EditText
        editText.requestFocus()

        // Comprobamos si el EditText gana o pierde el foco
        editText.setOnFocusChangeListener { _, tieneFoco ->
            if (!tieneFoco) {
                if (editText.text.toString().isEmpty()) {
                    editText.error = "Error: El ejército no acepta soldados anónimos"
                }
            }
        }

        boton.setOnClickListener {
            if (editText.text.toString().isEmpty()) {
                editText.error = "Error: El ejército no acepta soldados anónimos"
            } else {

                val unidadSeleccionada = spinner.selectedItem.toString()
                val equipamientoSeleccionado = radioGroup.checkedRadioButtonId
                val antorchaSeleccionada = checkBoxAntorcha.isChecked

                if (!antorchaSeleccionada) {
                    Log.e(
                        "FraguasIsengard",
                        "¡Peligro! Unidad enviada sin fuego"
                    )
                }

                Toast.makeText(
                    this,
                    "¡Unidad ${editText.text} enviada al Abismo de Helm!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // Comprobamos si existen datos guardados de una ejecución anterior
        if (savedInstanceState != null) {
            // Recuperamos el nombre o identificación de la tropa
            editText.setText(
                savedInstanceState.getString("idTropa")
            )

            // Recuperamos la posición seleccionada del Spinner
            spinner.setSelection(
                savedInstanceState.getInt("unidad")
            )

            // Recuperamos el RadioButton que estaba seleccionado
            radioGroup.check(
                savedInstanceState.getInt("equipamiento")
            )

            // Recuperamos el estado del CheckBox
            checkBoxAntorcha.isChecked =
                savedInstanceState.getBoolean("antorcha")

            // Recuperamos el mensaje de error guardado
            val error = savedInstanceState.getString("error")

            // Si existía un error anteriormente volvemos a mostrarlo
            if (error != null) {
                editText.error = error
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Volvemos a obtener los elementos de la interfaz
        val editText = findViewById<EditText>(R.id.editTextText)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val checkBoxAntorcha = findViewById<CheckBox>(R.id.checkBox)

        // Guardamos el texto escrito en el EditText
        outState.putString(
            "idTropa",
            editText.text.toString()
        )

        // Guardamos la posición seleccionada del Spinner
        outState.putInt(
            "unidad",
            spinner.selectedItemPosition
        )

        // Guardamos el ID del RadioButton seleccionado
        outState.putInt(
            "equipamiento",
            radioGroup.checkedRadioButtonId
        )

        // Guardamos si el CheckBox está marcado o no
        outState.putBoolean(
            "antorcha",
            checkBoxAntorcha.isChecked
        )

        // Guardamos el mensaje de error del EditText
        outState.putString(
            "error",
            editText.error?.toString()
        )
    }

    // Se ejecuta cuando la actividad empieza a ser visible
    override fun onStart() {
        super.onStart()
        Log.d(
            "FraguasIsengard",
            "onStart: Las fraguas se encienden"
        )
    }

    // Se ejecuta cuando la actividad pasa a primer plano
    override fun onResume() {
        super.onResume()
        Log.d(
            "FraguasIsengard",
            "onResume: Saruman continúa la producción"
        )
    }

    // Se ejecuta cuando la actividad deja de estar en primer plano
    override fun onPause() {
        super.onPause()
        Log.d(
            "FraguasIsengard",
            "onPause: Saruman detiene la producción temporalmente"
        )
    }

    // Se ejecuta cuando la actividad deja de ser visible
    override fun onStop() {
        super.onStop()
        Log.d(
            "FraguasIsengard",
            "onStop: Las fraguas quedan en silencio"
        )
    }

    // Se ejecuta cuando la actividad va a ser destruida
    override fun onDestroy() {
        super.onDestroy()
        Log.d(
            "FraguasIsengard",
            "onDestroy: La producción de Isengard termina"
        )
    }
}