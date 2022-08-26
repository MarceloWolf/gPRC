package com.marcelo.banca.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.marcelo.banca.entities.ClienteBanca;
import com.marcelo.banca.entities.TarjetaCredito;
import com.marcelo.banca.entities.TarjetaDebito;

import com.marcelo.banca.services.TarjetaDebitoService;
import com.marcelo.banca.services.TarjetaCreditoService;
import com.marcelo.banca.services.ClienteBancaService;








@Controller
@RequestMapping("/clienteBanca")
public class ClienteBancaController {
	
	
	@Autowired
	@Qualifier("clienteBancaService")
	private ClienteBancaService clienteBancaService;
	

	
	@Autowired
	@Qualifier("tarjetaCreditoService")
	private TarjetaCreditoService tarjetaCreditoService;
	
	@Autowired
	@Qualifier("tarjetaDebitoService")
	private TarjetaDebitoService tarjetaDebitoService;
	

	
	@GetMapping("/")
	public String crear(Model model) {
		ClienteBanca clienteBanca = new ClienteBanca();
		model.addAttribute("titulo", "Formulario: Nuevo cliente");
		model.addAttribute("clienteBanca", clienteBanca);
		System.out.println(tarjetaCreditoService.listar(2333333));
		return "persona/crear";
	}
	
	@PostMapping("/")
	public String guardar(@Valid @ModelAttribute ClienteBanca clienteBanca,BindingResult result, Model model,RedirectAttributes attribute) {
		String dniPersona = String.valueOf(clienteBanca.getDniPersona());
		if(clienteBancaService.findByDni(clienteBanca.getDniPersona())!=null) {
			FieldError error = new FieldError("persona", "dniPersona", "Ya existe una persona con ese DNI");
			result.addError(error);
		}
		if(clienteBanca.getDniPersona()==0) {
			FieldError error = new FieldError("persona", "dniPersona", "El dni de la persona no puede ser 0");
			result.addError(error);
		}
		if(dniPersona.length()>8 || dniPersona.length()<7) {
			FieldError error = new FieldError("persona", "dniPersona", "Los caracteres del dni deben ser 7 como minimo y 8 como maximo");
			result.addError(error);
		}
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nueva Persona");
			model.addAttribute("persona", clienteBanca);
			System.out.println("Hubo errores en la creacion del formulario!");
			return "persona/crear";
		}
		clienteBancaService.save(clienteBanca);
		System.out.println("Cliente guardado con exito!");
		attribute.addFlashAttribute("success","Cliente agregado con exito");
		return "redirect:/clienteBanca/";
	}
	


	@GetMapping("/lista/tarjetasCredito/{dniPersona}")
	public String listarTarjetasCredito(@PathVariable("dniPersona") long dniPersona, Model model) {
		//ClienteBanca clienteBanca = clienteBancaService.buscar(dniPersona);
		List<TarjetaCredito> lstTarjetaCredito = tarjetaCreditoService.listar(dniPersona);
		model.addAttribute("titulo", "Lista de tarjetas de crédito");
		model.addAttribute("lista", lstTarjetaCredito);
		return "persona/listaTarjetasCredito";
	}

	
	@GetMapping("/lista/tarjetasDebito/{dniPersona}")
	public String listarTarjetasDebito(@PathVariable("dniPersona") long dniPersona, Model model) {
		//ClienteBanca clienteBanca = clienteBancaService.buscar(dniPersona);
		List<TarjetaDebito> lstTarjetaDebito = tarjetaDebitoService.listar(dniPersona);
		model.addAttribute("titulo", "Lista de tarjetas de débito");
		model.addAttribute("lista", lstTarjetaDebito);
		return "persona/listaTarjetasDebito";
	}

	
	@GetMapping("/lista")
	public String listarClientesBanca(Model model) {
		List<ClienteBanca> clientesBanca = clienteBancaService.getAll();
		model.addAttribute("titulo", "Clientes");
		model.addAttribute("lista", clientesBanca);
		return "persona/listaClientes";
	}
	


	@GetMapping("lista/delete/{id}")
	public String eliminar(@PathVariable("id") int id,RedirectAttributes attribute) {
		clienteBancaService.eliminar(id);
		System.out.println("Registro eliminado con exito");
		attribute.addFlashAttribute("warning","Cliente eliminado con exito");
		return "redirect:/clienteBanca/lista";
	}
	

	

	


}