package model.logic;

import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import model.data_structures.Stack;
import model.data_structures.Queue;
import model.data_structures.IQueue;
import model.data_structures.IStack;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ArrayList<Queue<Viaje>> datosCola;
	private ArrayList<Stack<Viaje>> datosPila;

	private int tamano;
	private Viaje viaje;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		datosCola = new ArrayList< Queue<Viaje>>();
		datosCola.add(new Queue<Viaje>());
		datosCola.add(new Queue<Viaje>());

		datosPila = new ArrayList< Stack<Viaje>>();
		datosPila.add(new Stack<Viaje>());
		datosPila.add(new Stack<Viaje>());
	}
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano(int lista)
	{
		return datosCola.get(lista).size();
	}
	public void crearLista(int lista) throws Exception
	{
		CSVReader reader=null;
		reader = new CSVReader(new FileReader(".bogota-cadastral-2018-1-All-HourlyAggregate.csv"));
		String [] nextLine=reader.readNext();
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line

			datosCola.get(lista).enqueue(new Viaje(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4],nextLine[5], nextLine[6]));
			datosPila.get(lista).push(new Viaje(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4],nextLine[5], nextLine[6]));

		}
		reader.close();
	}

	
	public Queue<Viaje> grupoGrandeHora(int hora) {
		// TODO Auto-generated method stub
		ArrayList<Queue<Viaje>> aux2=new ArrayList<Queue<Viaje>>();
		Queue<Viaje> aux=new Queue<Viaje>();
		double mayor=hora;
		for (int i=0;i<=1;i++){
		while(datosCola.get(i).size()!=0){

			Viaje a=datosCola.get(0).dequeue();
			if(a.getHod()>=mayor){
				aux.enqueue(a);
				mayor=a.getHod();
			}
			else{
				aux2.add(aux);
				mayor=hora;
				aux=null;
			}
		}
		}
		int tam=0;
		Queue <Viaje>c=null;
		for(int i=0;i<aux2.size();i++)
		{
			if(tam<aux2.get(i).size())
			{
				tam=aux2.get(i).size();
				c=aux2.get(i);
			}
		}
		return c;
	}
	public Queue<Viaje> nViajesHora(int h, int n) {
		// TODO Auto-generated method stub
		Queue<Viaje> aux=new Queue<Viaje>();
		for(int i=0;i<=1;i++){
		while(datosPila.get(0).size()!=0){
			Viaje a=datosPila.get(0).pop();
			if(a.getHod()==h){
				aux.enqueue(a);
			}
		}	
		}
		int contador=aux.size()-n;
		while(contador!=0){
			aux.dequeue();
			contador--;
		}
		
		return aux;  
		// esto
	}

}
