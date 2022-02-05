package mx.kodemia.bookodemiasael

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.VolleyLog.TAG
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import mx.kodemia.bookodemiasael.extra.estaEnLinea
import mx.kodemia.bookodemiasael.extra.iniciarSesion
import mx.kodemia.bookodemiasael.extra.mensajeEmergente
import mx.kodemia.bookodemiasael.model.dataClass.Errors
import org.json.JSONObject

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        tv_regresar_registro.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }
        /*btn_registro.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }*/

        tiet_registro_usuario.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editUser: Editable?) {
                if(editUser.toString().trim().isEmpty()){
                    til_registro_usuario.setError("Ingrese nombre de usuario")
                }else{
                    til_registro_usuario.setErrorEnabled(false)
                    til_registro_usuario.setError("")
                }
            }

        })

        tiet_registro_correo.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editCorreo: Editable?) {
                if(editCorreo.toString().trim().isEmpty()){
                    til_registro_correo.setError("Ingrese correo")
                }else{
                    til_registro_correo.setErrorEnabled(false)
                    til_registro_correo.setError("")
                }
            }

        })

        tiet_registro_password.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editPass: Editable?) {
                if(editPass.toString().trim().isEmpty()){
                    til_registro_password.setError("Ingrese contrasena")
                }else{
                    til_registro_password.setErrorEnabled(false)
                    til_registro_password.setError("")
                }
            }

        })

        tiet_registro_password2.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editConfirm: Editable?) {
                if(editConfirm.toString().trim().isEmpty()){
                    til_registro_password2.setError("Confirme Contrasena")
                }else{
                    til_registro_password2.setErrorEnabled(false)
                    til_registro_password2.setError("")
                }
            }

        })

        btn_registro.setOnClickListener {

            val textoUsuario: String = tiet_registro_usuario.text?.trim().toString()
            val textoCorreo: String = tiet_registro_correo.text?.trim().toString()
            val textoPassword: String = tiet_registro_password.text?.trim().toString()
            val textoPassConfirm: String = tiet_registro_password2.text?.trim().toString()

            if(textoUsuario.isEmpty()){
                til_registro_usuario.setError("Ingrese nombre de usuario")
            }else{
                til_registro_usuario.setError(null)
            }

            if(textoCorreo.isEmpty()){
                til_registro_correo.setError("Ingrese correo")
            }else{
                til_registro_correo.setError(null)
            }

            if(textoPassword.isEmpty()){
                til_registro_password.setError("Ingrese contrasena")
            }else{
                til_registro_password.setError("")
            }

            if(textoPassConfirm.isEmpty()){
                til_registro_password2.setError("Confirme contrasena")
            }else{
                til_registro_password2.setError("")
            }
            if (textoPassword != textoPassConfirm){
                Toast.makeText(this,"Contraseñas diferentes", Toast.LENGTH_SHORT).show()
                til_registro_password.setError("Ingrese contrasena")
                til_registro_password2.setError("Confirme contrasena")
            }else{
                startActivity(Intent(this,Home::class.java))
            }

        }

    }
    fun realizarPeticion() {
        if (estaEnLinea(applicationContext)) {
            val json = JSONObject()
            json.put("name", til_registro_usuario.editText?.text.toString())
            json.put("email", til_registro_correo.editText?.text.toString())
            json.put("password", til_registro_password.editText?.text.toString())
            json.put("password_confirmation", til_registro_password2.editText?.text.toString())
            json.put("device_name", "User's phone")

            val queue = Volley.newRequestQueue(applicationContext)
            val request = object : JsonObjectRequest(
                Request.Method.POST,
                getString(R.string.url_servidor) + getString(R.string.api_registro),
                json,
                { response ->
                    Log.d(TAG, response.toString())
                    val jsonObject = JSONObject(response.toString())
                    iniciarSesion(applicationContext, jsonObject)
                    val intent = Intent(this, Home::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                },
                { error ->
                    Log.e(TAG, error.toString())
                    val jsonObject = JSONObject(String(error.networkResponse.data, Charsets.UTF_8))
                    val errors = Json.decodeFromString<Errors>(jsonObject.toString())
                    for (error in errors.errors){
                        mensajeEmergente(this, error.detail)
                    }
                    Log.e(TAG, error.networkResponse.toString())
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
            mensajeEmergente(this, getString(R.string.error_internet))
        }
    }


    private fun validarUsuario(): Boolean {
        return if (til_registro_usuario.editText?.text.toString().isEmpty()) {
            til_registro_usuario.error = getString(R.string.error_input_isempty)
            false
        } else {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(til_registro_usuario.editText?.text.toString())
                    .matches()
            ) {
                til_registro_usuario.isErrorEnabled = false
                true
            } else {
                til_registro_usuario.error = getString(R.string.error_email_noformat)
                false
            }
        }
    }

    private fun validarCorreo(): Boolean {
        return if (til_registro_correo.editText?.text.toString().isEmpty()) {
            til_registro_correo.error = getString(R.string.error_input_isempty)
            false
        } else {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(til_registro_correo.editText?.text.toString())
                    .matches()
            ) {
                til_registro_correo.isErrorEnabled = false
                true
            } else {
                til_registro_correo.error = getString(R.string.error_email_noformat)
                false
            }
        }
    }

    private fun validarContrasena(): Boolean {
        return if (til_registro_password.editText?.text.toString().isEmpty()) {
            til_registro_password.error = getString(R.string.error_input_isempty)
            false
        } else {
            til_registro_password.isErrorEnabled = false
            true
        }
    }

    private fun validarConfirmacionContrasena(): Boolean {
        return if (til_registro_password2.editText?.text.toString().isEmpty()) {
            til_registro_password2.error = getString(R.string.error_input_isempty)
            false
        } else {
            til_registro_password2.isErrorEnabled = false
            true
        }
    }
}