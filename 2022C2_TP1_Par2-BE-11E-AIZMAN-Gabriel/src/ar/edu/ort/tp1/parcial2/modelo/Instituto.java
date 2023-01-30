package ar.edu.ort.tp1.parcial2.modelo;

import java.util.ArrayList;
import java.util.Random;

public class Instituto {

	private static final int NOTA_MINIMA = 1;
	private static final int NOTA_MAXIMA = 10;
	private String nombre;
	private ArrayList<Materia> materias;

	public Instituto(String nombre) {
		this.nombre = nombre;
		this.materias = new ArrayList<Materia>();
		precargarAlumnos();
		prepararActas();
	}

	private boolean booleanAleatorio() {
		return Math.random() < 0.5;
	}

	private void cargarAlumnosAMateria(String nombre, int cantAlumnosReg, int cantAlumnosLibres) {
		Materia m = new Materia(nombre);
		for (int i = 0; i < cantAlumnosReg; i++) {
			try {
				m.inscribir(new AlumnoRegular(10000000 + i, "Nombre " + i, enteroAleatorio(1, 10), enteroAleatorio(1, 10),
						booleanAleatorio()));
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
		for (int i = 0; i < cantAlumnosLibres; i++) {
			try {
				AlumnoLibre a = new AlumnoLibre(20000000 + i, "Nombre " + i, enteroAleatorio(1, 10),
						enteroAleatorio(4, 10));
				// TODO -- Descomentar e implementar para agregarle notas previas a los alumnos libres
				
				int cantVueltas = enteroAleatorio(1, 2);
				for (int j = 0; j < cantVueltas; j++) {
					a.agregarNota(enteroAleatorio(1, 3));
				}

				m.inscribir(a);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}

		}
		materias.add(m);
	}
	
	// TODO -- Implementar el metodo de actualizacion de nota (solo para los alumnmos regulares)
	
	public void cambiarNota(String materia, int dni, int nota) {
		int iterador = 0;
		boolean igualMateria = false;
		System.out.println("-- Actualizando con " + nota + " la nota del alumno con el dni " + dni + " en la materia " + materia);
		if (nota >= NOTA_MINIMA && nota <= NOTA_MAXIMA) {
			while (iterador < this.materias.size() && !igualMateria) {
				Materia matActual = this.materias.get(iterador);
				if (matActual.getNombre().equals(materia)) {
					matActual.cambiarNotaActa(dni, nota);
					igualMateria = true;
					System.out.println("-- Se actualizo con " + nota + " la nota del alumno con el dni " + dni + " en la materia " + materia);
				} else {
					throw new IllegalArgumentException("ERROR: Esta materia no se encontro");
				}
			}
		} else {
			throw new IllegalArgumentException("ERROR: La nota es invalida");
		}
	}
	
	private int enteroAleatorio(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}

	private void precargarAlumnos() {
		int i = 1;
		cargarAlumnosAMateria("Mat" + i++, 30, 20);
		cargarAlumnosAMateria("Mat" + i++, 40, 10);
		cargarAlumnosAMateria("Mat" + i++, 45, 5);
		cargarAlumnosAMateria("Mat" + i++, 10, 40);
	}

	private void prepararActas() {
		for (Materia materia : materias) {
			materia.asignarActas();
		}
	}

	public void informarActasPorMateriaYCurso() {
		System.out.println("Actas de las materias del instituto " + nombre);
		for (Materia materia : materias) {
			materia.informarActasXCurso();
		}
	}
}
