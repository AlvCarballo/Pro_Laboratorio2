package es.studium.Laboratorio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidades
{
	public void registrarLog(String usuario, String mensaje)
	{
		//FileWriter tambi�n puede lanzar una excepci�n 
		try
		{
			// Destino de los datos
			FileWriter fw = new FileWriter("movimientos.log", true);
			// Buffer de escritura
			BufferedWriter bw = new BufferedWriter(fw);
			// Objeto para la escritura
			PrintWriter salida = new PrintWriter(bw);
			// Guardamos la fecha y hora actuales
			Date fechaHoraActual = new Date();
			// Formato deseado
			DateFormat fechaHoraFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			salida.print("["+fechaHoraFormato.format(fechaHoraActual)+"]");
			salida.print("["+usuario+"]");
			salida.println("["+mensaje+"]");
			// Cerrar el objeto salida, el objeto bw y el fw
			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException i)
		{
			System.out.println("Se produjo un error de Archivo");
		}
	}
	public void Ayuda()
	{
			try
			{
				Runtime.getRuntime().exec("hh.exe Ayuda.chm");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
	}
}
