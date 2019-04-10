package controller;

import model.Detalle;

public interface ParkingLot {
	
	public int CantidadEstacionados();
	
	public int EspaciosDisponibles();
	
	public void setPrecioPorDia(int precio);
	
	public int getPrecioPorDia();
	
	public void ingresoDetectado();
	
	public void egresoDetectado(int codigo);
	
	public void facturarEstadia(int precioPorDia, Detalle autoEstacionado);

	public int getRandomCode();

	public void recorrerAutoPorEstadia();
}
