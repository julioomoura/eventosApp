package com.example.testeummilhao.ummilhao.controllers

import com.example.testeummilhao.ummilhao.models.Evento
import com.example.testeummilhao.ummilhao.repository.EventoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView


@Controller
class EventoController {

    @Autowired
    private var er: EventoRepository? = null

    @GetMapping("/cadastrarEvento")
    fun form():String{
        return "formEvento"

    }


    @PostMapping("/cadastrarEvento")
    fun form(evento:Evento):String{

        er?.save(evento)

        return "redirect:/cadastrarEvento"

    }

    @GetMapping("/eventos")
    fun listEventos():ModelAndView{
        val mv = ModelAndView("index")
        val eventos = er?.findAll()
        mv.addObject("eventos", eventos)
        return mv
    }
}