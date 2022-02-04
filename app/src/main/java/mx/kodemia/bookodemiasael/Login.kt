package mx.kodemia.bookodemiasael

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


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

        btn_login.setOnClickListener {
            val textoEmail: String = tiet_correo_login.text?.trim().toString()
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
        }

        tv_registro_login.setOnClickListener {
            startActivity(Intent(this, Registro::class.java))
        }
    }
}
