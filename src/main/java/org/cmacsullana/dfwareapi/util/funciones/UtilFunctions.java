package org.cmacsullana.dfwareapi.util.funciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilFunctions {

	public static Date fechaActual() {
		
		return new Date();
	}
	
	public static Date fechaFromateada(String fechaSeleccionada, String formato){
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
        Date fecha = null;
        try {
           fecha = formatoDelTexto.parse(fechaSeleccionada);
         } catch (ParseException ex) {
           ex.printStackTrace();
           }
        return fecha;
    }
	
	public static String fechasFormateada(Date fe, String formatFecha){
        SimpleDateFormat formatoFecha = new SimpleDateFormat(formatFecha);
        String ff=formatoFecha.format(fe);
       return ff;
   }
	
}
