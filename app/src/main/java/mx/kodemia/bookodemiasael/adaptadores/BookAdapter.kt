package mx.kodemia.bookodemiasael.adaptadores

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.card.MaterialCardView
import mx.kodemia.bookodemiasael.Detalles
import mx.kodemia.bookodemiasael.Home
import mx.kodemia.bookodemiasael.R
import mx.kodemia.bookodemiasael.model.libros.Book

class BookAdapter(val listaLibros: MutableList<Book>, val activity: Activity) :
    RecyclerView.Adapter<BookAdapter.Libros>() {

    class Libros(val view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView = view.findViewById(R.id.item_card_view)
        val book: ImageView = view.findViewById(R.id.iv_book)
        val titulo: TextView = view.findViewById(R.id.text_title)
        val autor: TextView = view.findViewById(R.id.text_autor)
        val categoria: TextView = view.findViewById(R.id.text_categoria)

        fun info(listaLibros: Book) {
            titulo.text = listaLibros.attributes.title
            //autor.text = listaLibros.relationships.authors
           // categoria.text = listaLibros.categoria_libro

            Glide.with(view).load(listaLibros.book).error(R.drawable.libro_2)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(book)

            cardView.setOnClickListener{
                detallesLibro(view.context, listaLibros )
            }
        }

        fun detallesLibro(context: Context, libro: Book){
            val bundle = Bundle()
            bundle.putSerializable("book", libro)
            val intent = Intent(context.applicationContext,Detalles::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.Libros {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Libros(layoutInflater.inflate(R.layout.item_card_view, parent, false))
    }

    override fun onBindViewHolder(holder: Libros, position: Int) {
        holder.info(listaLibros[position])
    }

    override fun getItemCount(): Int = listaLibros.size

   /* fun insertarLibro(datosLibro: Book) {
        this.listaLibros.add(datosLibro)
        notifyItemInserted(itemCount)
    }*/


}
