package com.example.testeummilhao.ummilhao.models

import org.springframework.data.annotation.PersistenceConstructor
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Convidado @PersistenceConstructor constructor(@Id var rg:String,
                                                         var nomeConvidado:String,
                                                         @ManyToOne
                                                         var evento: Evento?)