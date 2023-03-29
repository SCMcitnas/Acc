package ficheros;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1 {
	public void divisionCart(int n, File fichero) throws IOException {
		FileReader lector = new FileReader(fichero);
		FileWriter escritor;
		try{
			int i;
			int archivos=0;
			while((i=lector.read())!=-1) {
				archivos++;
				File ficheroDiv= new File("C:\\Users\\Santiago\\Desktop\\texto"+archivos+".txt");
				escritor= new FileWriter(ficheroDiv);
				for(int j=0; j< n; j++) {
					escritor.write((char)i);
				}
			}
		}finally {
			if(lector!=null) {
				lector.close();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {

        Ejercicio1 ejercicio1= new Ejercicio1();
        File fichero = new File("C:\\Users\\Santiago\\Desktop\\texto.txt");
        ejercicio1.divisionCart(0,fichero);
    }
}
