package com.example.testeummilhao.ummilhao.models

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Evento:Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val codigo: Long = 0

    val nome: String? = null
    val local: String? = null
    val data: String? = null
    val horario: String? = null

    companion object{
        private const val serialVersionUID = 1L
    }


}