package mx.kodemia.bookodemiasael

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import mx.kodemia.bookodemiasael.adaptadores.BookAdapter
import mx.kodemia.bookodemiasael.extra.eliminarSesion
import mx.kodemia.bookodemiasael.extra.estaEnLinea
import mx.kodemia.bookodemiasael.extra.mensajeEmergente
import mx.kodemia.bookodemiasael.extra.obtenerDeSesion
import mx.kodemia.bookodemiasael.model.dataClass.Errors
import mx.kodemia.bookodemiasael.model.libros.Book
import mx.kodemia.bookodemiasael.model.libros.DataBook
import org.json.JSONObject

class Home : AppCompatActivity() {

    private val TAG = Home::class.qualifiedName


    private lateinit var pb_home_rv_books: ProgressBar
    private var parent_view: View? = null
    private lateinit var rv_home_libros: RecyclerView


   // val librosLista: MutableList<Book> = mutableListOf()
   // var adapterAgregados = BookAdapter(librosLista, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        parent_view = findViewById(android.R.id.content)
        rv_home_libros = findViewById(R.id.recyclerView_Home)
        pb_home_rv_books = findViewById(R.id.pb_home)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> return@setOnNavigationItemSelectedListener true
                R.id.nav_list -> {
                    val queue = Volley.newRequestQueue(applicationContext)
                    val request = object : StringRequest(Request.Method.POST,
                        getString(R.string.url_servidor) + getString(R.string.api_logout),
                        { response ->
                            Log.d(TAG, response.toString())
                            eliminarSesion(applicationContext)
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        },
                        { error ->
                            Log.e(TAG, "Entro en error")
                            Log.e(TAG, error.toString())
                        }) {
                        override fun getHeaders(): MutableMap<String, String> {
                            val headers = HashMap<String, String>()
                            headers["Authorization"] =
                                "Bearer ${obtenerDeSesion(applicationContext, "token")}"
                            return headers
                        }
                    }
                    queue.add(request)
                }
                R.id.nav_user -> return@setOnNavigationItemSelectedListener true
            }
            true
        }


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


    override fun onResume() {
        super.onResume()
        if (estaEnLinea(applicationContext)) {
            realizarPeticion()
        } else {
            mensajeEmergente(this, getString(R.string.error_internet))
        }
    }

    fun realizarPeticion() {
        val queue = Volley.newRequestQueue(applicationContext)
        val request = object : JsonObjectRequest(Request.Method.GET,
            getString(R.string.url_servidor) + getString(R.string.api_libros),
            null,
            { response ->
                Log.d(TAG,response.toString())
                rv_home_libros.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
                rv_home_libros.setHasFixedSize(true)
                val books = Json.decodeFromString<DataBook>(response.toString())
                val adapter = BookAdapter(books.data, this)
                rv_home_libros.adapter = adapter
                adapter.notifyDataSetChanged()
                pb_home_rv_books.visibility = View.GONE
                rv_home_libros.visibility = View.VISIBLE
            },
            { error ->
                Log.e(TAG, error.toString())
                if(error.networkResponse.statusCode == 401){
                    eliminarSesion(applicationContext)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                }else{
                    pb_home_rv_books.visibility = View.VISIBLE
                    rv_home_libros.visibility = View.GONE
                    val jsonObject = JSONObject(String(error.networkResponse.data, Charsets.UTF_8))
                    val errors = Json.decodeFromString<Errors>(jsonObject.toString())
                    for (error in errors.errors){
                        mensajeEmergente(this, error.detail)
                    }
                    Log.e(TAG, error.networkResponse.toString())
                }
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] =
                    "Bearer ${obtenerDeSesion(applicationContext, "token")}"
                headers["Accept"] = "application/json"
                headers["Content-type"] = "application/json"
                return headers
            }
        }
        queue.add(request)
    }

    /*private fun addLibros() {

       // librosLista.add(DataClassHome(R.drawable.libro_1, "Las Cronicas de Narnia", "Sabe", "Fantasia"))
       // librosLista.add(DataClassHome(R.drawable.libro_2, "La Sombra", "Sabe", "Suspenso"))
        //librosLista.add(DataClassHome(R.drawable.libro_1, "El Evangelio del mal","Sabe", "Suspenso"))
       // librosLista.add(DataClassHome(R.drawable.libro_2,"La musica del silencio", "Mark", "Fantasia"))



        recyclerView_Home.layoutManager = LinearLayoutManager(this)
        recyclerView_Home.setHasFixedSize(true)
        adapterAgregados = BookAdapter(librosLista, this)
        recyclerView_Home.adapter = adapterAgregados
    }*/

   /*fun item_card(view: View) {
        val intent = Intent(this, Detalles::class.java)
        startActivity(intent)
    }*/
}



