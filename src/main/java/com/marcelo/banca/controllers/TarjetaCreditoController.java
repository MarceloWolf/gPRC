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
import com.marcelo.banca.entities.TarjetaCredito;
import com.marcelo.banca.services.ClienteBancaService;
import com.marcelo.banca.services.TarjetaCreditoService;




@Controller
@RequestMapping("/tarjetaCredito")
public class TarjetaCreditoController {
	
	@Autowired
	@Qualifier("tarjetaCreditoService")
	private TarjetaCreditoService tarjetaCreditoService;
	
	@Autowired
	@Qualifier("clienteBancaService")
	private ClienteBancaService clienteBancaService;
	
	@GetMapping("/")
	public String crear (Model model) {
		TarjetaCredito tarjetaCredito = new TarjetaCredito();
		List<ClienteBanca> lstClientesBanca = clienteBancaService.getAll();
		model.addAttribute("titulo", "Formulario: Nueva Tarjeta de Crédito");
		model.addAttribute("clienteBanca", lstClientesBanca);
		model.addAttribute("tarjetaCredito", tarjetaCredito);
		return "persona/crearTarjetaCredito";
	}
	
	@PostMapping("/")
	public String guardar (@Valid @ModelAttribute TarjetaCredito tarjetaCredito, BindingResult result, Model model,RedirectAttributes attribute) {
		List<ClienteBanca> lstClientesBanca = clienteBancaService.getAll();
		if(tarjetaCreditoService.findByNumero(tarjetaCredito.getNumero())!=null) {
			FieldError error = new FieldError("tarjetaCredito", "numero", "Ya existe una tarjeta con ese número");
			result.addError(error);
		}
		
		//--------------------validador de número-----------------
		
		if(tarjetaCredito.getNumero()==0) {
			FieldError error = new FieldError("tarjetaCredito", "numero", "El número no puede ser cero");
			result.addError(error);
		}
		
		String prueba=""+tarjetaCredito.getNumero();
		if(prueba.length()!=16) {
			FieldError error = new FieldError("tarjetaCredito", "numero", "El número de la tarjeta tiene que ser de 16 dígitos");
			result.addError(error);
		}
		//---------------------Validador codigo de seguridad---------------
	
	if(tarjetaCredito.getCodSeg()==0) {
		FieldError error = new FieldError("tarjetaCredito", "codSeg", "El número no puede ser cero");
		result.addError(error);
	}
	
	
	
	String prueba1=""+tarjetaCredito.getCodSeg();
	if(prueba1.length()!=3) {
		FieldError error = new FieldError("tarjetaCredito", "codSeg", "El código de seguridad de la tarjeta tiene que ser de 3 dígitos");
		result.addError(error);
	}
	
	//-------------------------validador mes-------------------------
	
	if(tarjetaCredito.getMes()==0) {
		FieldError error = new FieldError("tarjetaCredito", "mes", "El número de mes no puede ser cero");
		result.addError(error);
	}
	
	if(tarjetaCredito.getMes()<0) {
		FieldError error = new FieldError("tarjetaCredito", "mes", "Número de mes inválido");
		result.addError(error);
	}
	
	if(tarjetaCredito.getMes()>12) {
		FieldError error = new FieldError("tarjetaCredito", "mes", "Número de mes inválido");
		result.addError(error);
	}
	
	String prueba2=""+tarjetaCredito.getMes();
	if(prueba2.length()!=2) {
		FieldError error = new FieldError("tarjetaCredito", "mes", "El mes tiene que ser de dos dígitos");
		result.addError(error);
	}
	
	//-----------------------Validador año---------------------
	
	if(tarjetaCredito.getAnio()==0) {
		FieldError error = new FieldError("tarjetaCredito", "anio", "El número del año no puede ser cero o menor");
		result.addError(error);
	}
	
	if(tarjetaCredito.getAnio()<21) {
		FieldError error = new FieldError("tarjetaCredito", "anio", "El año debe ser el corriente en curso o superior. Tarjeta vencida");
		result.addError(error);
	}
	
	String prueba3=""+tarjetaCredito.getAnio();
	if(prueba3.length()!=2) {
		FieldError error = new FieldError("tarjetaCredito", "anio", "El año tiene que ser de dos dígitos");
		result.addError(error);
	}
		
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nueva Tarjeta de Credito");
			model.addAttribute("clienteBanca", lstClientesBanca);
			model.addAttribute("tarjetaCredito", tarjetaCredito);
			System.out.println("Hubo errores en la creacion del formulario!");
			
			
			return "persona/crearTarjetaCredito";
		}
		
		//LocalDate hola=	LocalDate.of(tarjetaCredito.getAnio(), tarjetaCredito.getMes(), 1);
		//System.out.println(hola);
		/*ClienteBanca clienteBanca = tarjetaCredito.getClienteBanca();
		clienteBanca.agregarTC(tarjetaCredito);*/
		tarjetaCreditoService.save(tarjetaCredito);
		System.out.println("Tarjeta de crédito guardado con exito!");
		attribute.addFlashAttribute("success","Tarjeta agregada con exito");
		return "redirect:/tarjetaCredito/";
	}

}