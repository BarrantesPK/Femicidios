package com.if7100.controller;

import com.if7100.entity.HechoImputado;
import com.if7100.entity.Lugar;
import com.if7100.service.HechoImputadoService;
import com.if7100.service.HechoService;
import com.if7100.service.ImputadoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HechoImputadoController {

    private HechoImputadoService hechoImputadoService;
    private HechoService hechoService;

    private ImputadoService imputadoService;

    public HechoImputadoController(HechoImputadoService hechoImputadoService, HechoService hechoService, ImputadoService imputadoService){
        super();
        this.hechoImputadoService = hechoImputadoService;
        this.hechoService = hechoService;
        this.imputadoService = imputadoService;
    }

    @GetMapping("/hechoimputado")
    public String listHechoImputado(Model model){
        model.addAttribute("hechoImputado", hechoImputadoService.getAllHechoImputado());
        return "hechosImputados/hechoImputado";
    }

    @GetMapping("/hechosimputado/{id}")
    public String listHechosImputado(Model model, @PathVariable Integer id){
        model.addAttribute("hechoImputado", hechoImputadoService.getAllHechosImputado(id));
        return "hechosImputados/hechoImputado";
    }

    @GetMapping("/hechoimputados/{id}")
    public String listHechoImputados(Model model, @PathVariable Integer id){
        model.addAttribute("hechoImputado", hechoImputadoService.getAllHechoImputados(id));
        return "hechosImputados/hechoImputado";
    }

    @GetMapping("hechoimputado/new")
    public String createHechoImputadoForm(Model model){
        HechoImputado hechoImputado = new HechoImputado();
        model.addAttribute("hechoImputado", hechoImputado);
        model.addAttribute("hechos", hechoService.getAllHechos());
        model.addAttribute("imputados", imputadoService.getAllUsuarios());
        return "hechosImputados/create_hecho_imputado";
    }

    @GetMapping("/hechosimputado/new/{Id}")
    public String createHechosImputadoForm(Model model, @PathVariable Integer Id) {
        HechoImputado hechoImputado = new HechoImputado();
        hechoImputado.setCIHecho(Id);
        model.addAttribute("hechoImputado", hechoImputado);
        model.addAttribute("hechos", hechoService.getAllHechos());
        model.addAttribute("imputados", imputadoService.getAllUsuarios());
        return "hechosImputados/create_hechos_imputado";
    }

    @GetMapping("/hechoimputado/{id}")
    public String deleteHecho(@PathVariable Integer id){
        hechoImputadoService.deleteHechoImputadoById(id);
        return "redirect:/hechoimputado";
    }

    @PostMapping("/hechoimputado")
    public String saveHechoImputado(@ModelAttribute("hechoImputado") HechoImputado hechoImputado, Model model){
        try {
            hechoImputadoService.saveHechoImputado(hechoImputado);
            return "redirect:/hechoimputado";
        }catch (DataIntegrityViolationException e){
            String mensaje = "No se puede guardar el hecho debido a un error de integridad de datos.";
            model.addAttribute("error_message", mensaje);
            model.addAttribute("error", true);
            return createHechoImputadoForm(model);
        }
    }

    @PostMapping("/hechosimputado")
    public String saveHechosImputado(@ModelAttribute("hechoImputado") HechoImputado hechoImputado, Model model){
        try {
            hechoImputadoService.saveHechoImputado(hechoImputado);
            return "redirect:/hechoimputado";
        }catch (DataIntegrityViolationException e){
            String mensaje = "No se puede guardar el hecho debido a un error de integridad de datos.";
            model.addAttribute("error_message", mensaje);
            model.addAttribute("error", true);
            return createHechosImputadoForm(model, hechoImputado.getCIHecho());
        }
    }


}
