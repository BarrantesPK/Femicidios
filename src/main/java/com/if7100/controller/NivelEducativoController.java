package com.if7100.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.if7100.entity.NivelEducativo;

import com.if7100.service.NivelEducativoService;


@Controller
public class NivelEducativoController {
private NivelEducativoService nivelEducativoService;
	
	public NivelEducativoController(NivelEducativoService nivelEducativoService) {
	
		super();
		this.nivelEducativoService = nivelEducativoService;
	
}
@GetMapping("/nivelEducativo")
	public String listStudents(Model model) {
		model.addAttribute("nivelEducativo",nivelEducativoService.getAllNivelEducativo());
		return "nivelEducativo/nivelEducativo";
}
@GetMapping("/nivelEducativo/new")
public String createUsuarioForm(Model model){
	NivelEducativo nivelEducativo = new NivelEducativo();
	model.addAttribute("nivelEducativo", nivelEducativo);
	return"nivelEducativo/create_nivelEducativo";
}

@PostMapping("/nivelEducativo")
public String saveNivelEducativo(@ModelAttribute("nivelEducativo") NivelEducativo nivelEducativo) {
	nivelEducativoService.saveNivelEducativo(nivelEducativo);
	return "redirect:/nivelEducativo";
}

@GetMapping("/nivelEducativo/{id}")
public String deleteNivelEducativo(@PathVariable Integer id) {
	nivelEducativoService.deleteNivelEducativoById(id);
	return "redirect:/nivelEducativo";
}
@GetMapping("/nivelEducativo/edit/{id}")
public String editNivelEducativoForm(@PathVariable Integer id, Model model) {
	model.addAttribute("nivelEducativo", nivelEducativoService.getNivelEducativoById(id));
	return "nivelEducativo/edit_nivelEduactivo";
}
@PostMapping("/nivelEducativo/{id}")
public String updateNivelEducativo (@PathVariable Integer id, @ModelAttribute ("nivelEducativo")NivelEducativo nivelEducativo, Model model) {
	NivelEducativo existingNivelEducativo = nivelEducativoService.getNivelEducativoById(id);
	existingNivelEducativo.setCI_Id(id);
	existingNivelEducativo.setCVTitulo(nivelEducativo.getCVTitulo());
	existingNivelEducativo.setCVDescripcion(nivelEducativo.getCVDescripcion());
	existingNivelEducativo.setCI_Pais(nivelEducativo.getCI_Pais());
	
	nivelEducativoService.updateNivelEducativo(existingNivelEducativo);
	return "redirect:/nivelEducativo";
}


}
