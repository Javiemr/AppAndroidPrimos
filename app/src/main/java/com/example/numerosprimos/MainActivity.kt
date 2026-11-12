package com.example.numerosprimos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  Enlace de los componentes del XML con variables en Kotlin
        val txtNumero = findViewById<EditText>(R.id.txtNumero)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val lblResultado = findViewById<TextView>(R.id.lblResultado)

        // Programación del botón
        btnCalcular.setOnClickListener {
            val textoIntroducido = txtNumero.text.toString().trim()

            // Comprobación si esta vacío
            if (textoIntroducido.isEmpty()) {
                lblResultado.text = "Error: El cuadro de texto está vacío. Introduce un número."
                return@setOnClickListener
            }

            // Bloque try-catch para controlar errores de conversión
            try {
                val numero = textoIntroducido.toInt()

                // Comprobante de positivo
                if (numero <= 0) {
                    lblResultado.text = "Error: Debes introducir un número positivo (mayor que 0)."
                } else if (numero > 100000) {
                    lblResultado.text = "Error: Introduce un número menor o igual a 100.000 para evitar bloqueos."
                } else {
                    // Calculamos los primos menores al número dado
                    val primos = obtenerPrimosMenores(numero)

                    if (primos.isEmpty()) {
                        lblResultado.text = "No hay números primos menores que $numero."
                    } else {
                        lblResultado.text = "Primos menores que $numero:\n${primos.joinToString(", ")}"
                    }
                }

            } catch (e: NumberFormatException) {
                // Si el usuario escribe letras o decimales, entra aquí y NO se cierra la app
                lblResultado.text = "Error: '$textoIntroducido' no es un número entero válido."
            } catch (e: Exception) {
                lblResultado.text = "Error inesperado: ${e.message}"
            }
        }
    }

    // Función que obtiene la lista con todos los primos menores que 'limite' y devuelve los primos menores
    private fun obtenerPrimosMenores(limite: Int): List<Int> {
        val listaPrimos = mutableListOf<Int>()
        for (n in 2 until limite) {
            if (esPrimo(n)) {
                listaPrimos.add(n)
            }
        }
        return listaPrimos
    }

    // Función que comprueba si un número 'n' es primo
    private fun esPrimo(n: Int): Boolean {
        if (n < 2) return false
        var i = 2
        while (i * i <= n) {
            if (n % i == 0) {
                return false
            }
            i++
        }
        return true
    }
}