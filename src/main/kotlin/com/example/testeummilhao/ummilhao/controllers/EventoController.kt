package com.example.testeummilhao.ummilhao.controllers

import com.example.testeummilhao.ummilhao.models.Convidado
import com.example.testeummilhao.ummilhao.models.Evento
import com.example.testeummilhao.ummilhao.repository.ConvidadoRepository
import com.example.testeummilhao.ummilhao.repository.EventoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView


@Controller
class EventoController {

    @Autowired
    private lateinit var er: EventoRepository

    @Autowired
    private lateinit var cr: ConvidadoRepository

    @GetMapping("/cadastrarEvento")
    fun form():String{
        return "formEvento"

    }


    @PostMapping("/cadastrarEvento")
    fun form(evento:Evento):String{

        er.save(evento)

        return "redirect:/cadastrarEvento"

    }

    @GetMapping("/eventos")
    fun listEventos():ModelAndView{
        val mv = ModelAndView("index")
        val eventos = er.findAll()
        mv.addObject("eventos", eventos)
        return mv
    }

    @GetMapping("/{codigo}")
    fun detalhesEvento (@PathVariable("codigo") codigo: Long):ModelAndView{
        var evento: Evento? = er.findByCodigo(codigo)
        val mv = ModelAndView("detalhesEvento")
        mv.addObject("evento", evento)
        println("evento $evento")
        return mv
    }

    @PostMapping("/{codigo}")
    fun detalhesEventoPost(@PathVariable("codigo") codigo: Long, convidado: Convidado):String{
        var ev : Evento = er.findByCodigo(codigo)
        convidado.evento = ev
        cr.save(convidado)
        return "redirect:/{codigo}"
    }
}