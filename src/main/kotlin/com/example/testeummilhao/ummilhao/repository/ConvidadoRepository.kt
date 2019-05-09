package com.example.testeummilhao.ummilhao.repository

import com.example.testeummilhao.ummilhao.models.Convidado
import com.example.testeummilhao.ummilhao.models.Evento
import org.springframework.data.repository.CrudRepository

interface ConvidadoRepository:CrudRepository<Convidado, String>{

    fun findByEvento(evento: Evento?):Iterable<Convidado>
    fun findByRg(rg:String):Convidado
}