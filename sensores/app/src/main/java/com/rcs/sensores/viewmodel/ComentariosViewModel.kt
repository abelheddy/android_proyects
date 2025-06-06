package com.rcs.sensores.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.database.FirebaseDatabase

class ComentariosViewModel(app: Application) : AndroidViewModel(app) {

    private val firebaseRef = FirebaseDatabase.getInstance().getReference("comentarios")

    fun enviarComentario(
        nombre: String,
        email: String,
        mensaje: String,
        onResult: (Boolean) -> Unit
    ) {
        val comentario = com.rcs.sensores.model.Comentario(nombre, email, mensaje)
        firebaseRef.push().setValue(comentario)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }
}