package ficheros;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio1 {
	public void divisionCart(int n, File fichero) throws IOException {
		FileReader lector = new FileReader(fichero);
		
			int i;
			int archivos=0;
			char a[] = new char[n];
			while((i=lector.read(a))!=-1) {
				archivos++;
				File ficheroDiv= new File("C:\\Users\\Santiago\\Desktop\\texto"+archivos+".txt");
				ficheroDiv.createNewFile();
				try(FileWriter escritor= new FileWriter(ficheroDiv)){
					escritor.write(new String(a,0,i));
				}
			}
	}
	
	public void divisionLineas(int l,File fichero) throws IOException{
		try(Scanner sc= new Scanner(fichero)){
			
			int archivos=0;
			String mesg;
			
			while(sc.hasNext()) {
				archivos++;
				File ficheroDiv= new File("C:\\Users\\Santiago\\Desktop\\texto"+archivos+".txt");
				
				for(int i=0; i<l; i++) {
					mesg=sc.nextLine();
					try(PrintWriter pw = new PrintWriter(new FileWriter(ficheroDiv,true))){
						pw.print(mesg+"\n");
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {

        Ejercicio1 ejercicio1= new Ejercicio1();
        File fichero = new File("C:\\Users\\Santiago\\Desktop\\texto.txt");
        //ejercicio1.divisionCart(3,fichero);
        ejercicio1.divisionLineas(1,fichero);
    }
}
