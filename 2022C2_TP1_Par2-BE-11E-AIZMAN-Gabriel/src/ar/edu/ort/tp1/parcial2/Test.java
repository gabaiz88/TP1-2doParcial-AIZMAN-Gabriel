package ar.edu.ort.tp1.parcial2;

import ar.edu.ort.tp1.parcial2.modelo.Instituto;

public class Test {

	public static void main(String[] args) {
		Instituto i = new Instituto("ORT");
		cambiarNotas(i);
		i.informarActasPorMateriaYCurso();
	}

	public static void cambiarNotas(Instituto i) {
		//Descomentar una vez implementado el cambiarNota para testear. NO MODIFICAR!

		try {
			i.cambiarNota("Mat1", 10000001, 10);
		}
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		try {
			i.cambiarNota("Mat1", 20000001, 5);
		}
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		try {
			i.cambiarNota("Programacion", 10000001, 5);
		}
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		try {
			i.cambiarNota("Mat1", -3, 5);
		}
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		try {
			i.cambiarNota("Mat1", 10000001, -5);
		}
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}			


	}

}

	
