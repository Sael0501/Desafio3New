package mx.kodemia.bookodemiasael.model.dataClass

import kotlinx.serialization.Serializable


@Serializable
data class Errors(

    val errors: List<Error>

): java.io.Serializable
