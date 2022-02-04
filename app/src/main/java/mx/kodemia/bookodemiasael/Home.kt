package mx.kodemia.bookodemiasael

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import mx.kodemia.bookodemiasael.adaptadores.BookAdapter
import mx.kodemia.bookodemiasael.model.dataClass.DataClassHome

class Home : AppCompatActivity() {


    val librosLista: MutableList<DataClassHome> = mutableListOf()
    var adapterAgregados = BookAdapter(librosLista)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        addLibros()

        tv_regresar_home.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

    /* fun initRecyclerAgregados() {
        val myLinearLayoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        recyclerView_Home.layoutManager = myLinearLayoutManager
        recyclerView_Home.adapter = adapterAgregados
        recyclerView_Home.setHasFixedSize(true)
        }
     */


    private fun addLibros() {

        librosLista.add(DataClassHome(R.drawable.libro_1, "Las Cronicas de Narnia", "Sabe", "Fantasia"))
        librosLista.add(DataClassHome(R.drawable.libro_2, "La Sombra", "Sabe", "Suspenso"))
        librosLista.add(DataClassHome(R.drawable.libro_1, "El Evangelio del mal","Sabe", "Suspenso"))
        librosLista.add(DataClassHome(R.drawable.libro_2,"La musica del silencio", "Mark", "Fantasia"))



        recyclerView_Home.layoutManager = LinearLayoutManager(this)
        recyclerView_Home.setHasFixedSize(true)
        adapterAgregados = BookAdapter(librosLista)
        recyclerView_Home.adapter = adapterAgregados
    }

    fun item_card(view: View) {
        val intent = Intent(this, Detalles::class.java)
        startActivity(intent)
    }
}



