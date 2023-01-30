package ar.edu.ort.tp1.parcial2.modelo;

import ar.edu.ort.tp1.tdas.implementaciones.ColaNodos;
import ar.edu.ort.tp1.tdas.interfaces.Cola;

public class Materia {

	private static final int CANTIDAD_CURSOS = 10;
	private String nombre;
	private Acta[] actas;
	// TODO -- Reemplazar por una estructura que mantenga los alumnos ordenados por DNI
	private AlumnosPorDni inscriptos;

	public Materia(String nombre) {
		this.nombre = nombre;
		this.inscriptos = new AlumnosPorDni();
	}

	public void inscribir(Alumno a) throws RuntimeException {
		int i = 0;
		while (i < this.inscriptos.size()) {
			if (a.getDni() == this.inscriptos.get(i).getDni()) {
				throw new IllegalArgumentException ("El DNI esta duplicado");
			}
			else {
				i++;
			}
		}
		// TODO -- Actualizar agregando validacion (el alumno no puede repetirse por mismo DNI)
		inscriptos.add(a);
	}

	public void asignarActas() {
		inicializarActas();
		// TODO -- Modificar segun la estructura de alumnos actualizada
		for (Alumno a : this.inscriptos) {
			TipoExamen tipoExamen = a.obtenerTipoExamen();
			if (tipoExamen != null) {
				actas[tipoExamen.ordinal()].agregarAlumno(a);
			}
		}
	}

	private void inicializarActas() {
		actas = new Acta[TipoExamen.values().length];
		for (int i = 0; i < actas.length; i++) {
			actas[i] = new Acta(TipoExamen.values()[i]);
		}
	}

	public void informarActasXCurso() {
		if (actas[0] != null) {
			System.out.println("Alumnos por acta de la materia: " + nombre);
			int[][] cantidades = obtenerMatriz();
			mostrarMatriz(cantidades);
		}
	}

	private int[][] obtenerMatriz() {
		int[][] cantidades = new int[TipoExamen.values().length][CANTIDAD_CURSOS];
		for (int iFila = 0; iFila < actas.length; iFila++) {
			Acta acta = actas[iFila];
			acta.alumnosPorCurso(cantidades[iFila]);
		}
		return cantidades;
	}

	private void mostrarMatriz(int[][] cantidades) {
		// TODO -- Modificar para que tambien muestre los totales por curso
		System.out.printf("%15s", "Curso");
		for (int i = 0; i < CANTIDAD_CURSOS; i++) {
			
			System.out.printf("%5d", 1+i);
		}
		System.out.println();
		for (int iFila = 0; iFila < TipoExamen.values().length; iFila++) {
			TipoExamen te = TipoExamen.values()[iFila];
			System.out.printf("%15s", te);
			for (int iColumna = 0; iColumna < cantidades[iFila].length; iColumna++) {
				System.out.printf("%5d", cantidades[iFila][iColumna]);
			}
			System.out.println();
		}
	}

	public String getNombre() {
		return nombre;
	}
	
	public void cambiarNotaActa(int dni, int nota) {
		int i = 0;
		Alumno a = null; 
		while (i < this.actas.length && a == null) {
			a = actas[i].buscarAlumno(dni);
			i++;
		}
		if (a != null) {
			if (a instanceof AlumnoRegular) {
				a.setNotaFinalCursada(nota);
			} else {
				throw new IllegalArgumentException("ERROR: No se puede actualizar nota de un alumno libre");
			}
		} else {
			throw new IllegalArgumentException("ERROR: Este alumno no se encuentra inscripto");
		}
	}

}