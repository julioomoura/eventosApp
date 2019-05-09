package com.example.testeummilhao.ummilhao.controllers

import com.example.testeummilhao.ummilhao.models.Convidado
import com.example.testeummilhao.ummilhao.models.Evento
import com.example.testeummilhao.ummilhao.repository.ConvidadoRepository
import com.example.testeummilhao.ummilhao.repository.EventoRepository
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.util.*
import javax.validation.Valid


@Controller
class EventoController(val eventoRepository: EventoRepository, val convidadoRepository: ConvidadoRepository) {

    @GetMapping("/cadastrarEvento")
    fun form() = "formEvento"


    @PostMapping("/cadastrarEvento")
    fun form(@Valid evento:Evento, result: BindingResult, attributes: RedirectAttributes):String{
        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!")
            return "redirect:/cadastrarEvento"
        }

        eventoRepository.save(evento)
        attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!")
        return "redirect:/cadastrarEvento"

    }

    @GetMapping("/eventos")
    fun listEventos():ModelAndView{
        val mv = ModelAndView("index")
        val eventos = eventoRepository.findAll()
        mv.addObject("eventos", eventos)
        return mv
    }

    @GetMapping("/{codigo}")
    fun detalhesEvento (@PathVariable("codigo") codigo: Long):ModelAndView{
        var evento: Optional<Evento> = eventoRepository.findById(codigo)
        val mv = ModelAndView("detalhesEvento")
        evento.ifPresent { value ->
            mv.addObject("evento", value)

            var convidados = convidadoRepository.findByEvento(value)
            mv.addObject("convidados", convidados)
        }
        return mv
    }

    @GetMapping("/deletarEvento")
    fun deletarEvento(codigo:Long):String{
        val evento : Optional<Evento> = eventoRepository.findById(codigo)
        evento.ifPresent{
            eventoRepository.delete(it)
        }
        return "redirect:/eventos"
    }

    @PostMapping("/{codigo}")
    fun detalhesEventoPost(@PathVariable("codigo") codigo: Long, @Valid convidado: Convidado, result: BindingResult, attributes:RedirectAttributes):String{
        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!")
            return "redirect:/{codigo}"
        }
        var ev : Optional<Evento> = eventoRepository.findById(codigo)
        ev.ifPresent {
            convidado.evento = it
            convidadoRepository.save(convidado)
            attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!")
        }
        return "redirect:/{codigo}"
    }

    @GetMapping("/deletarConvidado")
    fun deletarConvidado(rg:String):String{
        val convidado: Optional<Convidado> = convidadoRepository.findById(rg)
        convidado.ifPresent {conv ->
            convidadoRepository.delete(conv)
        }
        val evento: Evento? = convidado.get().evento
        val codigo = evento?.codigo.toString()
        return "redirect:/$codigo"
    }
}