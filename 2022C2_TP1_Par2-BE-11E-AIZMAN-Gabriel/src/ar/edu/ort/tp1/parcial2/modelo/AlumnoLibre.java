package ar.edu.ort.tp1.parcial2.modelo;

import ar.edu.ort.tp1.tdas.implementaciones.PilaNodos;
import ar.edu.ort.tp1.tdas.interfaces.Pila;

public class AlumnoLibre extends Alumno {
	//ver que hacer con esto
	private Pila<Integer> examenesPrevios;

	public AlumnoLibre(int dni, String nombre, int nroCurso, int notaFinalCursada) {
		super(dni, nombre, nroCurso, notaFinalCursada);
		this.examenesPrevios = new PilaNodos<>();
	}
	
	@Override
	public TipoExamen obtenerTipoExamen() {
		return TipoExamen.FINAL_PRACTICO;
	}

	public void agregarNota(int notaPrevia) {
		this.examenesPrevios.push(notaPrevia);
	}
}
