package com.example.testeummilhao.ummilhao.controllers

import com.example.testeummilhao.ummilhao.models.Evento
import com.example.testeummilhao.ummilhao.repository.EventoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView


@Controller
class EventoController {

    @Autowired
    private var er: EventoRepository? = null

    @RequestMapping(value = ["/cadastrarEvento"], method = [RequestMethod.GET])
    fun form():String{
        return "formEvento"

    }


    @RequestMapping(value = ["/cadastrarEvento"], method = [RequestMethod.POST])
    fun form(evento:Evento):String{

        er?.save(evento)

        return "redirect:/cadastrarEvento"

    }

    @RequestMapping("/eventos")
    fun listEventos():ModelAndView{
        val mv = ModelAndView("index")
        val eventos = er?.findAll()
        mv.addObject("eventos", eventos)
        return mv
    }
}