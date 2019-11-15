package com.porfirio.cineapp.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {
	
	public static List<String> getNextDays(int count) {
		SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
		Date start = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, count);
		Date endDate = cal.getTime();
		
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(start);
		List<String> nextDays = new ArrayList<>();
		
		while(!gcal.getTime().after(endDate)) {
			Date d = gcal.getTime();
			gcal.add(Calendar.DATE, 1);
			nextDays.add(dateformatter.format(d));
		}
		
		return nextDays;
	}
	
	// subir archivos al servidor
		public static String guardarImagen(MultipartFile file, HttpServletRequest request) {
			if(file.getSize() > 6000000) { // el archivo pesa mas de 5 megas
				System.out.print("tamaño incorecto");
				return null;
			}
			
			if (file.getContentType().equalsIgnoreCase("image/jpg") || file.getContentType().equalsIgnoreCase("image/jpeg")
					|| file.getContentType().equalsIgnoreCase("image/png")) {// es una imagen
				// Obtenemos el nombre original del archivo
				String nombreOriginal = file.getOriginalFilename();
				nombreOriginal = nombreOriginal.replace(" ","-");
				String nombreFinal = generarNombre(8) + nombreOriginal;
				// Obtenemos la ruta ABSOLUTA del directorio images
				// apache-tomcat/webapps/cineapp/resources/images/
				String rutaFinal = request.getServletContext().getRealPath("/resources/imagenes/");
				try {
					// Formamos el nombre del archivo para guardarlo en el disco duro
					File imageFile = new File(rutaFinal + nombreFinal);
					// Aqui se guarda fisicamente el archivo en el disco duro
					file.transferTo(imageFile);
					return nombreFinal;
				} catch (IOException e) {
					System.out.println("++++++Error+++++ " + e.getMessage());
					return null;
				}
			} else { // no es una imagen
				System.out.print("no es una imagen");
				return null;
			}
			
		}
		
		public static String generarNombre(int count) {
			String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			StringBuilder nombre = new StringBuilder();
			
			for (int i = count; i >= 1; i--) {
				int caracter = (int)(Math.random() * caracteres.length());
				nombre.append(caracteres.charAt(caracter));
			}
			return nombre.toString();
		}

}
