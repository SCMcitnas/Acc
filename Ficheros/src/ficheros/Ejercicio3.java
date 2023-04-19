package ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio3{
	
	List<String> nombres = new ArrayList<String>();
	List<Integer> fechasNacimiento = new ArrayList<Integer>();
	List<Integer> codigos = new ArrayList<Integer>();
	
	public void darAlta(File fichero, String nombre, int fNacimiento, int codigo) throws IOException{
		try(FileInputStream fin= new FileInputStream(fichero);
				DataInputStream in = new DataInputStream(fin)){
			try(FileOutputStream fos= new FileOutputStream(fichero);
					DataOutputStream out = new DataOutputStream(fos);) {
				nombres.add(nombre);
				fechasNacimiento.add(fNacimiento);
				codigos.add(codigo);
				for(int i=0; i<nombres.size(); i++) {
					out.writeUTF(nombres.get(i));
					out.writeInt(fechasNacimiento.get(i));
					out.writeInt(codigos.get(i));
					out.writeChar('\n');
				}
			}
		}
	}
	
	public void consultarAlm(File fichero) throws FileNotFoundException, IOException {
		try(FileInputStream fin= new FileInputStream(fichero);
				DataInputStream in = new DataInputStream(fin)){

			for(int i=0; i<nombres.size(); i++) {
				System.out.print(in.readUTF()+" ");
				System.out.print(in.readInt()+" ");
				System.out.print(in.readInt());
				System.out.print("\t");
			}
		}catch (EOFException e){
			System.out.print("acaba");
		}
	}
	
	public void modificarAlm(File fichero, String nombre) {
		for(String al:nombres){
			if(al == nombre) {
				
			}
		}
	}
	
	public void borrarAlm(File fichero) {
		
	}
	
	public static void main(String[] args)  {
		Ejercicio3 ejer3 = new Ejercicio3();
		File fichero = new File("C:\\Users\\Santiago\\Desktop\\pruebas_acc\\alumnos.dat");
		
		try {
			ejer3.darAlta(fichero,"Juan",23,12345);
			ejer3.darAlta(fichero,"Pepe",24,12346);
			ejer3.darAlta(fichero,"Yoel",25,12347);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ejer3.consultarAlm(fichero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//ejer3.modificarAlm(fichero);
		ejer3.borrarAlm(fichero);
	}
}