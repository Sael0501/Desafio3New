package mx.kodemia.bookodemiasael

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        tv_regresar_registro.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }
        btn_registro.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }

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
}