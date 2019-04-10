package model;

import java.time.LocalTime;

public class Detalle {
	private static int proximoCodigo;
	private int codigo;
	private LocalTime ingreso; //Hora, minuto, segundo 
	private LocalTime egreso;
	private int cantidadDeHoras;
	
	public Detalle() {
		super();
		this.ingreso=LocalTime.now();
		this.codigo=getProximoCodigo();		
	}

	public static int getProximoCodigo(){
		return ++proximoCodigo;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public LocalTime getIngreso() {
		return ingreso;
	}

	public void setIngreso(LocalTime ingreso) {
		this.ingreso = ingreso;
	}

	public LocalTime getEgreso() {
		return egreso;
	}

	public void setEgreso() {
		this.egreso = LocalTime.now();
	}

	public int getCantidadDeHoras() {
		
		int horaDeIngreso=this.ingreso.getHour();
		int horaDeEgreso=this.egreso.getHour();
		
		int diferenciaDeHora=horaDeEgreso-horaDeIngreso;
		
		if(diferenciaDeHora<0){ //quedo mas de 1 dia
			//diferenciaDehora
		}
		cantidadDeHoras=diferenciaDeHora;
		
		return cantidadDeHoras;
	}

	public void setCantidadDeHoras(int cantidadDeHoras) {
		this.cantidadDeHoras = cantidadDeHoras;
	}
	
	
	
	
	
}
