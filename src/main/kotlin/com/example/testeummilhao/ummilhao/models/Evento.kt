package com.example.testeummilhao.ummilhao.models

import org.springframework.data.annotation.PersistenceConstructor
import javax.persistence.*

@Entity
data class Evento @PersistenceConstructor constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val codigo: Long = 0,
        val nome: String? = null,
        val local: String? = null,
        val data: String? = null,
        val horario: String? = null){

        @OneToMany
        var convidados: List<Convidado>? = null
}

