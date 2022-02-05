package mx.kodemia.bookodemiasael

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import mx.kodemia.bookodemiasael.extra.*
import mx.kodemia.bookodemiasael.model.dataClass.Errors
import org.json.JSONObject

class Login : AppCompatActivity() {

    private val TAG = Login::class.qualifiedName

    override fun onCreate(savedInstanceState: Bundle?) {

        eliminarSesion(applicationContext)
        if(validarSesion(applicationContext)){
            lanzarActivity()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()


        tiet_correo_login.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editText: Editable?) {
                if (editText.toString().trim().isEmpty()) {
                    til_correo_login.setError("Ingrese un correo")
                } else {
                    til_correo_login.setErrorEnabled(false)
                    til_correo_login.setError("")
                }
            }

        })

        tiet_password_login.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editPass: Editable?) {
                if (editPass.toString().trim().isEmpty()) {
                    til_password_login.setError("Ingresa una contraseña")
                } else {
                    til_password_login.setErrorEnabled(false)
                    til_password_login.setError("")
                }
            }

        })

        /*btn_login.setOnClickListener {
            if (til_correo_login.isErrorEnabled || til_password_login.isErrorEnabled) {
                Snackbar.make(it, "Por favor complete todos los campos", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                if (validarCorreo() && validarContrasena()) {
                    realizarPeticion()
                }
            }
        }*/
        /*tv_registro_login.setOnClickListener {
            startActivity(Intent(this, Registro::class.java))
            finish()
        }*/
    }





    private fun lanzarActivity() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }


    fun init(){
        btn_login.setOnClickListener {
            val listaBool = listOf<Boolean>(validarCorreo(),validarContrasena())
            var contador = 0
            for(validacion in listaBool){
                if(validacion == false){
                    contador++
                }
            }
            if(contador<1){
                contador = 0
                realizarPeticion()
            }
        }
        val textSigin = findViewById<TextView>(R.id.tv_registro_login)
        textSigin.setOnClickListener {
            startActivity(Intent(this,Registro::class.java))
        }
    }

    fun realizarPeticion() {
        VolleyLog.DEBUG = true
        if (estaEnLinea(applicationContext)) {
            btn_login.visibility = View.GONE
            pb_login.visibility = View.VISIBLE
            val queue = Volley.newRequestQueue(applicationContext)
            val json = JSONObject()
            json.put("email", til_correo_login.editText?.text.toString())
            json.put("password", til_password_login.editText?.text.toString())
            json.put("device_name", "User's phone")

            val request = object : JsonObjectRequest(
                Request.Method.POST,
                getString(R.string.url_servidor) + getString(R.string.api_login),
                json,
                { response ->
                    val jsonObject = JSONObject(response.toString())
                    iniciarSesion(applicationContext, jsonObject)
                    if (validarSesion(applicationContext)) {
                        lanzarActivity()
                    }
                },
                { error ->
                    btn_login.visibility = View.VISIBLE
                    pb_login.visibility = View.GONE
                    val jsonObject = JSONObject(String(error.networkResponse.data, Charsets.UTF_8))
                    val errors = Json.decodeFromString<Errors>(jsonObject.toString())
                    for (error in errors.errors) {
                        mensajeEmergente(this, error.detail)
                    }
                    Log.e(TAG, error.networkResponse.toString())
                    Log.e(TAG, error.toString())
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Accept"] = "application/json"
                    headers["Content-type"] = "application/json"
                    return headers
                }
            }
            queue.add(request)
        } else {
            mensajeEmergente(activity = this, mensaje = getString(R.string.error_internet))
        }
    }

    private fun validarCorreo(): Boolean {
        return if (til_correo_login.editText.toString().isEmpty()) {
            til_correo_login.error = getString(R.string.error_input_isempty)
            false
        } else {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(til_correo_login.editText?.text.toString())
                    .matches()
            ) {
                til_correo_login.isErrorEnabled = false
                true
            } else {
                til_correo_login.error = getString(R.string.error_email_noformat)
                false
            }
        }
    }

    private fun validarContrasena(): Boolean {
        return if (til_password_login.editText?.text.toString().isEmpty()) {
            til_password_login.error = getString(R.string.error_input_isempty)
            false
        } else {
            til_password_login.isErrorEnabled = false
            true
        }
    }
    /* val textoEmail: String = tiet_correo_login.text?.trim().toString()
     val textPass: String = tiet_password_login.text?.trim().toString()
     if (textoEmail.isEmpty()) {
         til_correo_login.setError("Ingrese un correo")
     } else {
         til_correo_login.setError(null)
     }

     if (textPass.isEmpty()) {
         til_password_login.setError("Ingrese una contrasena")
     } else {
         til_password_login.setError(null)
     }
     startActivity(Intent(this, Home::class.java))
 }*/

    //tv_registro_login.setOnClickListener
   // {
      //  startActivity(Intent(this, Registro::class.java))
    //}
}

