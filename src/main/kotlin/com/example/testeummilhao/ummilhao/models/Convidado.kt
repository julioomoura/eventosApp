package com.example.testeummilhao.ummilhao.models

import org.springframework.data.annotation.PersistenceConstructor
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.validation.constraints.NotEmpty


@Entity
data class Convidado @PersistenceConstructor constructor(@Id
                                                         @NotEmpty
                                                         var rg:String,
                                                         @NotEmpty
                                                         var nomeConvidado:String,
                                                         @ManyToOne
                                                         var evento: Evento?)