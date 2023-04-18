package ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio3{
	
	
	public void darAlta(File fichero, String nombre, int fNacimiento, int codigo) throws IOException{
		try(FileInputStream fin= new FileInputStream(fichero);
				DataInputStream in = new DataInputStream(fin)){
			try(FileOutputStream fos= new FileOutputStream(fichero);
					DataOutputStream out = new DataOutputStream(fos);) {

				out.writeUTF(nombre);
				out.writeInt(fNacimiento);
				out.writeInt(codigo);
				out.writeUTF("\n");
			}
		}
	}
	
	public void consultarAlm(File fichero) {
		
	}
	
	public void modificarAlm(File fichero) {
		
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
		ejer3.consultarAlm(fichero);
		ejer3.modificarAlm(fichero);
		ejer3.borrarAlm(fichero);
	}
}