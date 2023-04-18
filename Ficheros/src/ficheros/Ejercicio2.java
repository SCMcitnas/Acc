package ficheros;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio2{
	
	public void copiarNoBuffer(File fichero,double porcentaje) throws IOException {
		int contador=0;
		try(FileInputStream fin= new FileInputStream(fichero);
				DataInputStream in = new DataInputStream(fin)){
			File copia = new File("C:\\Users\\Santiago\\Desktop\\pruebas_acc\\binarioCopia.dat");
			try(FileOutputStream fos= new FileOutputStream(copia);
					DataOutputStream out = new DataOutputStream(fos);) {
				
				while(true) {
					out.writeUTF(in.readUTF()) ;
					out.writeInt(in.readInt()) ;
					out.writeFloat(in.readFloat());
				}
				
				
			}catch(EOFException e) {
				System.out.print(e);
			}
			
		}
	}
	
	public void copiarBufferNormal(File fichero, double porcentaje) throws IOException{

		try(FileInputStream fin= new FileInputStream(fichero);
				BufferedInputStream bis = new BufferedInputStream(fin)){
			File copia = new File("C:\\Users\\Santiago\\Desktop\\pruebas_acc\\binarioCopiaBuffer.dat");
			
			bis.mark((int)fichero.length());
			String completo= bis.readAllBytes()+"";
			porcentaje= porcentaje/100;
		    int integer = (int)(completo.length()*porcentaje);
			bis.reset();
			
			try(FileOutputStream fos = new FileOutputStream(copia);
					BufferedOutputStream bfo = new BufferedOutputStream (fos)){
				
				bfo.write(bis.readNBytes(integer));
			}
		}
	}
	
	public void crear(File fichero) {
		try (FileOutputStream fos = new
				FileOutputStream(fichero);
				DataOutputStream out =new DataOutputStream (fos)) {
				
				out.writeUTF("Hola") ;
				out.writeInt(123) ;
				out.writeFloat(12);
				out.writeUTF("Fin") ;
				
				} catch (IOException e )
				{System.out.println("Error");}
	}
	public static void main(String[] args)  {

        Ejercicio2 ejercicio2= new Ejercicio2();
        File fichero = new File("C:\\Users\\Santiago\\Desktop\\pruebas_acc\\binarios.dat");
        ejercicio2.crear(fichero);
        try {
			ejercicio2.copiarNoBuffer(fichero, 40);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ejercicio2.copiarBufferNormal(fichero, 100);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
}