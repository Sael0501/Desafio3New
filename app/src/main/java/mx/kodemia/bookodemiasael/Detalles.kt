package mx.kodemia.bookodemiasael

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detalles.*
import mx.kodemia.bookodemiasael.model.libros.Book

class Detalles : AppCompatActivity() {

    private val TAG = Detalles::class.qualifiedName
    lateinit var tv_detalles_titulo: TextView
    lateinit var tv_detalles_autor: TextView
    lateinit var tv_detalles_categoria: TextView
    lateinit var tv_detalles_cardview_2: TextView
    lateinit var tv_detalles_nombre_autor: TextView
    lateinit var tv_detalles_informacion_autor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        intent.extras?.let{
            val book = it.getSerializable("book") as Book
            tv_title_detalles.text = getString(R.string.Mockupchange,book.attributes.title)
            textView12.text = getString(R.string.lorem_Ipsum_Des,book.attributes.content)
            Log.d(TAG,book.attributes.title)
            val author = it.getString("author")
            tv_autor_detalles.text = getString(R.string.Autorchange,author)
            val category = it.getString("category")
            tv_categoria_detalles.text = getString(R.string.Categoriachange,category)
        }

        text_regresar_detalle.setOnClickListener{
            startActivity(Intent(this,Home::class.java))
        }
        tv_detalles_titulo.setOnClickListener {
            intent.extras?.let{
                val book = it.getSerializable("book") as Book
                tv_detalles_cardview_2.text = getString(R.string.lorem_Ipsum_Des,book.attributes.content)
            }
        }
    }

    fun init(){
        tv_detalles_titulo = findViewById(R.id.tv_descripcion_detalles)
        tv_detalles_autor= findViewById(R.id.tv_autor_detalles)
        tv_detalles_categoria= findViewById(R.id.tv_categoria_detalles)
        tv_detalles_cardview_2= findViewById(R.id.textView12)
        tv_detalles_nombre_autor= findViewById(R.id.tv_sobreAutor_detalles)
        tv_detalles_informacion_autor= findViewById(R.id.tv_descripcion_autor)
    }
}

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val button_favorite: ImageButton = findViewById(R.id.imageButton7)
        val button_share: ImageButton = findViewById(R.id.imageButton6)
        button_favorite.setBackgroundResource(0)
        button_share.setBackgroundResource(0)

        text_regresar_detalle.setOnClickListener{
            startActivity(Intent(this,Home::class.java))
        }

        val text_button_des: TextView = findViewById(R.id.tv_descripcion_detalles)
        val text_button_det: TextView = findViewById(R.id.textView10)
        val alter_text: TextView = findViewById(R.id.textView12)

        text_button_des.setOnClickListener {
            alter_text.setText("Esta es una descripción, is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s")
        }

        text_button_det.setOnClickListener {
            alter_text.setText("Estos son los detalles, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum")
        }
    }
}*/