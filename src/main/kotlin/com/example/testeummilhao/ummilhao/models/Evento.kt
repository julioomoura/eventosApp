package com.example.testeummilhao.ummilhao.models

import org.springframework.data.annotation.PersistenceConstructor
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
data class Evento @PersistenceConstructor constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val codigo: Long = 0,
        @NotEmpty
        val nome: String? = null,
        @NotEmpty
        val local: String? = null,
        @NotEmpty
        val data: String? = null,
        @NotEmpty
        val horario: String? = null){

        @OneToMany
        var convidados: List<Convidado>? = null
}

