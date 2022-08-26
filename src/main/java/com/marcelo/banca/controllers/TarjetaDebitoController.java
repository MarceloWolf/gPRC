package com.marcelo.banca.controllers;


//import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marcelo.banca.entities.ClienteBanca;
import com.marcelo.banca.entities.TarjetaDebito;
import com.marcelo.banca.services.ClienteBancaService;
import com.marcelo.banca.services.TarjetaDebitoService;




@Controller
@RequestMapping("/tarjetaDebito")
public class TarjetaDebitoController {
	
	@Autowired
	@Qualifier("tarjetaDebitoService")
	private TarjetaDebitoService tarjetaDebitoService;
	
	@Autowired
	@Qualifier("clienteBancaService")
	private ClienteBancaService clienteBancaService;
	
	@GetMapping("/")
	public String crear (Model model) {
		TarjetaDebito tarjetaDebito = new TarjetaDebito();
		List<ClienteBanca> lstClientesBanca = clienteBancaService.getAll();
		model.addAttribute("titulo", "Formulario: Nueva Tarjeta de Débito");
		model.addAttribute("clienteBanca", lstClientesBanca);
		model.addAttribute("tarjetaDebito", tarjetaDebito);
		return "persona/crearTarjetaDebito";
	}
	
	@PostMapping("/")
	public String guardar (@Valid @ModelAttribute TarjetaDebito tarjetaDebito, BindingResult result, Model model,RedirectAttributes attribute) {
		List<ClienteBanca> lstClientesBanca = clienteBancaService.getAll();
		if(tarjetaDebitoService.findByNumero(tarjetaDebito.getNumero())!=null) {
			FieldError error = new FieldError("tarjetaDebito", "numero", "Ya existe una tarjeta con ese número");
			result.addError(error);
		}
		
		//--------------------validador de número-----------------
		
		if(tarjetaDebito.getNumero()==0) {
			FieldError error = new FieldError("tarjetaDebito", "numero", "El número no puede ser cero");
			result.addError(error);
		}
		
		String prueba=""+tarjetaDebito.getNumero();
		if(prueba.length()!=16) {
			FieldError error = new FieldError("tarjetaDebito", "numero", "El número de la tarjeta tiene que ser de 16 dígitos");
			result.addError(error);
		}
		//---------------------Validador codigo de seguridad---------------
	
	if(tarjetaDebito.getCodSeg()==0) {
		FieldError error = new FieldError("tarjetaDebito", "codSeg", "El número no puede ser cero");
		result.addError(error);
	}
	
	
	
	String prueba1=""+tarjetaDebito.getCodSeg();
	if(prueba1.length()!=3) {
		FieldError error = new FieldError("tarjetaDebito", "codSeg", "El código de seguridad de la tarjeta tiene que ser de 3 dígitos");
		result.addError(error);
	}
	
	//-------------------------validador mes-------------------------
	
	if(tarjetaDebito.getMes()==0) {
		FieldError error = new FieldError("tarjetaDebito", "mes", "El número de mes no puede ser cero");
		result.addError(error);
	}
	
	if(tarjetaDebito.getMes()<0) {
		FieldError error = new FieldError("tarjetaDebito", "mes", "Número de mes inválido");
		result.addError(error);
	}
	
	if(tarjetaDebito.getMes()>12) {
		FieldError error = new FieldError("tarjetaDebito", "mes", "Número de mes inválido");
		result.addError(error);
	}
	
	String prueba2=""+tarjetaDebito.getMes();
	if(prueba2.length()!=2) {
		FieldError error = new FieldError("tarjetaDebito", "mes", "El mes tiene que ser de dos dígitos");
		result.addError(error);
	}
	
	//-----------------------Validador año---------------------
	
	if(tarjetaDebito.getAnio()==0) {
		FieldError error = new FieldError("tarjetaDebito", "anio", "El número del año no puede ser cero o menor");
		result.addError(error);
	}
	
	if(tarjetaDebito.getAnio()<21) {
		FieldError error = new FieldError("tarjetaDebito", "anio", "El año debe ser el corriente en curso o superior. Tarjeta vencida");
		result.addError(error);
	}
	
	String prueba3=""+tarjetaDebito.getAnio();
	if(prueba3.length()!=2) {
		FieldError error = new FieldError("tarjetaDebito", "anio", "El año tiene que ser de dos dígitos");
		result.addError(error);
	}
		
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nueva Tarjeta de Debito");
			model.addAttribute("clienteBanca", lstClientesBanca);
			model.addAttribute("tarjetaDebito", tarjetaDebito);
			System.out.println("Hubo errores en la creacion del formulario!");
			
			
			return "persona/crearTarjetaDebito";
		}
		
		//LocalDate hola=	LocalDate.of(tarjetaCredito.getAnio(), tarjetaCredito.getMes(), 1);
		//System.out.println(hola);
		/*ClienteBanca clienteBanca = tarjetaCredito.getClienteBanca();
		clienteBanca.agregarTC(tarjetaCredito);*/
		tarjetaDebitoService.save(tarjetaDebito);
		System.out.println("Tarjeta de Débito guardado con exito!");
		attribute.addFlashAttribute("success","Tarjeta agregada con exito");
		return "redirect:/tarjetaDebito/";
	}

}