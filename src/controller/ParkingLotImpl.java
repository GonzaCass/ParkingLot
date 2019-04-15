package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javamail.ServicioExterno;
import model.Detalle;

public class ParkingLotImpl implements ParkingLot {
	private List<Detalle> aparcados;
	private int precio;
	
	public ParkingLotImpl (){
		this.aparcados=new ArrayList<Detalle>(100);
		this.precio=0;
	}
	
	@Override
	public int CantidadEstacionados() {
		// TODO Auto-generated method stub
		return aparcados.size();
	}

	@Override
	public int EspaciosDisponibles() {
		// TODO Auto-generated method stub
		return 100-aparcados.size();
	}

	@Override
	public void setPrecioPorDia(int precio) {
		// TODO Auto-generated method stub
		this.precio=precio;
		
	}

	@Override
	public int getPrecioPorDia() {
		// TODO Auto-generated method stub
		return precio;
	}

	@Override
	public void ingresoDetectado() {
		// TODO Auto-generated method stub
		if(this.EspaciosDisponibles() >0){
			Detalle d=new Detalle();
			aparcados.add(d);
		} else JOptionPane.showMessageDialog(null, "No hay lugares disponibles", "Error", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void egresoDetectado(int codigo) {
		// TODO Auto-generated method stub
		Detalle aux=buscarAutoPorCodigo(codigo);
		
		if(aux!=null){
			aux.setEgreso();
			this.facturarEstadia(getPrecioPorDia(), aux);
			aparcados.remove(aux);
		}
		else{
			System.out.println("Codigo erroneo");
		}
			
	}

	@Override
	public void facturarEstadia(int precioPorDia, Detalle pagoAtrasado) {
		// TODO Auto-generated method stub
		/*1) Hay que evaluar si hay un egreso
		 * 2) Si lo hay, es un egreso normal. Sino, son las 00hs y hay que cobrar estadia completa y envar mail
		 * */
		boolean notificarPorMail=false;
		if(pagoAtrasado.getEgreso()==null){//son las 00 y se quedo ahi
			pagoAtrasado.setEgreso();
			notificarPorMail=true;
		}
		
		int cantHs=pagoAtrasado.getCantidadDeHoras();
		int montoFinal=precioPorDia*cantHs;
		
		if(notificarPorMail==true){
			ServicioExterno.enviarMail("cuerpo de mail", "email@example.org");
		}
		else{
			System.out.println("Monto a pagar: " +montoFinal);
		}	
	}
	
	public void recorrerAutoPorEstadia(){
		int precioPorDia=this.getPrecioPorDia();
		for(int i=0;i<aparcados.size();i++){
			Detalle aux=aparcados.get(i);
			this.facturarEstadia(precioPorDia,aux);	
			aparcados.remove(aux);
		}
		if(!aparcados.isEmpty()){
			Detalle aux=aparcados.get(0);
			this.facturarEstadia(precioPorDia,aux);	
			aparcados.remove(aux);
		}
			
	}
	
	
	private Detalle buscarAutoPorCodigo(int codigo){
		for(int i=0;i<=aparcados.size();i++){
			Detalle aux=aparcados.get(i);
			if(aux.getCodigo()==codigo){
				return aux;
			}
		}
		return null;
	}
	
	/**
	 * Este metodo devuelve una posicion random del vector
	 * Siempre y cuando haya autos en el estacionamiento*/
	public int getFirstCode(){
		if(this.CantidadEstacionados()>0) //Si tengo autos aparcados
			return aparcados.get(0).getCodigo(); //devuelvo el codigo de la primera posicion
		return -1; //devuelvo -1 que significa que no hay autos estacionados, es un mal funcionamiento del servicio
	}

}
