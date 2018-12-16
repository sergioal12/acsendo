package com.sergioal.acsendotest.bean;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.sergioal.acsendotest.model.Billete;
import com.sergioal.acsendotest.service.BilleteService;

@Component
public class CalcularCambio {
	private BilleteService billeteService;
	
	public void calcularCambio(int retiro, List<Billete> billetes) {
		System.out.println("aqui entro a calcular cambio");
		
		
		//List<Billete> billetes = billeteService.findAll();
		
		if(billetes.equals(null)||billetes.isEmpty()) {
			System.out.println("este cajero no puede entregar billetes");
		}else {
			 int[] valores = new int[billetes.size()];
			
			Map<Integer, Integer> nonOrderMap = new HashMap<Integer, Integer>();
			for(int i = 0; i<billetes.size();i++) {
				Billete billete = new Billete();
				billete = billetes.get(i);
				valores[i]=billete.getDenominacion();
				nonOrderMap.put(billete.getDenominacion(), billete.getCantidad());
			}
			
		
			int menorValor = ordenacionMin(valores);
			//validacion 1 que valor total sea mayor que la denominacion mas pequeña
			if(retiro<menorValor) {
				System.out.println("la cantidad solicitada no puede ser entregada");
				System.out.println("debe solicitar una cantidad superior o igual al billete minimo");
			}
			//ordenar de mayor a menor las denominaciones
			Map<Integer, Integer> orderMap = new TreeMap<Integer, Integer>(
					new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return o2.compareTo(o1);
						}
					}
					);
			orderMap.putAll(nonOrderMap);
			
			//al rendimiento no le gusta esto
				
				int j = 0;
				List<int[]> billEntrega = new ArrayList();
				Iterator it = orderMap.entrySet().iterator();
				int monto = 0;
				int codigoOper = 0;
				while(it.hasNext()) {
					int[] contador = new int[3];
					
					Map.Entry<Integer,Integer> pair = (Entry) it.next();
					if(j==0) {//contador de primera operacion (?)
						
						contador = validar(retiro,pair.getKey(),pair.getValue());
						monto = contador[2];
						codigoOper = contador[4];
						System.out.println(" en el primer if condicion 1: "+contador[0]+" condicion 2: "+contador[1]+" condicion 3: "+contador[2]);
						System.out.println("esto es monto en el primer caso: "+monto);
						it.remove();
						if(monto!=0) {
							billEntrega.add(contador);
						}
						j++;
					}else {
						if(codigoOper==4) {
							contador = validar(retiro,pair.getKey(),pair.getValue());
							monto = contador[2];
							codigoOper = contador[4];
							System.out.println(" en el else dentro del otro if condicion 1: "+contador[0]+" condicion 2: "+contador[1]+" condicion 3: "+contador[2]);
							System.out.println("esto es monto en el segundo caso: "+monto);
							it.remove();
							if(monto!=0) {
								billEntrega.add(contador);
							}
						}else if(codigoOper==1&&monto>=menorValor||codigoOper==2&&monto>=menorValor||codigoOper==3&&monto>=menorValor){
							contador = validar(monto,pair.getKey(),pair.getValue());
							monto = contador[2];
							codigoOper = contador[4];
							System.out.println(" en el else dentro del otro else condicion 1: "+contador[0]+" condicion 2: "+contador[1]+" condicion 3: "+contador[2]);
							System.out.println("esto es monto en el tercer caso: "+monto);
							System.out.println("esto es monto en el tercer caso: "+codigoOper);
							it.remove();
							if(monto!=0||monto==0&&codigoOper==3) {
								billEntrega.add(contador);
							}
							
						}else if(codigoOper==1&&monto<menorValor||codigoOper==2&&monto<menorValor||codigoOper==4&&monto<menorValor) {
							contador[2]=monto;
							billEntrega.add(contador);
							break;
						}
					}
				}
				
				for(int[] elem: billEntrega) {
					System.out.println("esto es elem: "+elem[0]+" esto es elem en 2: "+elem[1]+" esto es elem en 3: "+elem[2]+" esto es elem en 4: "+elem[3]);
				}
				
					
					
					
					

		}
		
	}
	
	public int[] validar(int retiro, int denominacion, int billetesCantidad) {
		int[] billetesEntregados = new int[5];
		if(retiro>=denominacion) {
			if(retiro%denominacion!=0) {
				System.out.println("esto es retiro: "+retiro+" esto es denominacion: "+denominacion+" Esto es cantidad: "+billetesCantidad);
				int previaCantidad = retiro/denominacion;
				System.out.println("esto es previaCantidad: "+previaCantidad);
				if(previaCantidad<=billetesCantidad) {
					int montoEntregado = previaCantidad*denominacion;
					billetesEntregados[0] = previaCantidad;//cuantos billetes de la denominacion se entregan
					billetesEntregados[1] = montoEntregado;//monto entregado
					billetesEntregados[2] = retiro - montoEntregado;//monto faltante
					billetesEntregados[3] = denominacion;
					billetesEntregados[4] = 1;//codigo operacion suficientes billetes
					
				}else {
					int montoEntregado = billetesCantidad*denominacion;
					billetesEntregados[0]=billetesCantidad;
					billetesEntregados[1]=montoEntregado;
					billetesEntregados[2] = retiro - montoEntregado;
					billetesEntregados[3] = denominacion;
					billetesEntregados[4]=2;//codigo operacion insuficientes billetes
					
				}
			}else {
				System.out.println("esto es retiro en la condicion rarosa: "+retiro+" esto es denominacion: "+denominacion+" Esto es cantidad: "+billetesCantidad);
				int cantidadBilletes = retiro/denominacion;
				int montoEntregado = cantidadBilletes*denominacion;
				billetesEntregados[0]=cantidadBilletes;
				billetesEntregados[1]=cantidadBilletes*denominacion;
				billetesEntregados[2]=retiro - montoEntregado;
				billetesEntregados[3]=denominacion;
				billetesEntregados[4]=3;//exactamente la cantidad de billetes
			}
		}else {
			billetesEntregados[0]=0;
			billetesEntregados[1]=0;
			billetesEntregados[2]=0;
			billetesEntregados[3]=denominacion;
			billetesEntregados[4]=4;//solicitud de retiro inferior al billete siendo procesado
		}
		return billetesEntregados;
	}
	//Buscar el billete de menor valor
	public int ordenacionMin(int[] valores) {
		int val = valores[0];
		
		for(int i = 1; i<valores.length; i++) {
			if(valores[i]<val) {
				val = valores[i];
			} 
		}
		
		return val;
	}

}
