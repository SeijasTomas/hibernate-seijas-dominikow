package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCadena {
	@RequestMapping("/{operacion}/{cadena}")
	public ModelAndView cadena(
			@PathVariable("operacion") String operacion, @PathVariable("cadena") String cadena) {
		ModelMap modelo = new ModelMap();
		if(operacion.equals("pasarAMayuscula")) {
			String  cadenaConvertida = cadena.toUpperCase();
			modelo.put("operacionARealizar",operacion);
			modelo.put("cadenaOriginal",cadena);
			modelo.put("cadena2",cadenaConvertida);
		
		}else if(operacion.equals("pasarAMinuscula")){
			String cadenaConvertida = cadena.toLowerCase();
			modelo.put("operacionARealizar",operacion);
			modelo.put("cadenaOriginal",cadena);
			modelo.put("cadena2",cadenaConvertida);
		
		}else if(operacion.equals("invertirOrden")) {
			String cadenaConvertida = "";
			for(int i=cadena.length()-1 ; i>=0 ; i--) {				
				cadenaConvertida += cadena.charAt(i);
			}
			modelo.put("operacionARealizar",operacion);
			modelo.put("cadenaOriginal",cadena);
			modelo.put("cadena2",cadenaConvertida);
		
		}else if(operacion.equals("cantidadDeCaracteres")) {
			Integer cadenaConvertida = (cadena.length()-1);
			modelo.put("operacionARealizar",operacion);
			modelo.put("cadenaOriginal",cadena);
			modelo.put("cadena2",cadenaConvertida);
		}else {
			String error = "Ocurrio un error en la operacion";
			modelo.put("error",error);
			return new ModelAndView("/error",modelo);
		}
		return new ModelAndView("/cadena",modelo);
	}
}	
