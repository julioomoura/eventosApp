package com.example.testeummilhao.ummilhao.repository

import com.example.testeummilhao.ummilhao.models.Evento
import org.springframework.data.repository.CrudRepository

interface EventoRepository : CrudRepository<Evento, String>