package es.studium.Laboratorio;

import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class EdicionArticulos extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	Choice listado = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	String[] cadena;
	int idArticuloEditar = 0;
	String NombreUsuario="";
	Utilidades utilidad = new Utilidades();
	
	public EdicionArticulos(String UserName)
	{
		NombreUsuario=UserName;
		utilidad.registrarLog(NombreUsuario,"Entrando en Editar Articulo");
		setTitle("Editar Articulo");
		setLayout(new FlowLayout());
		// Rellenar el Choice
		listado.add("Seleccionar un Articulo...");
		// Conectar BD
		conexion = bd.conectar();
		cadena = (bd.consultarArticulosChoice(conexion)).split("#");
		for(int i = 0; i < cadena.length; i++)
		{
			listado.add(cadena[i]);
		}
		add(listado);
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		add(btnAceptar);
		add(btnLimpiar);
		addWindowListener(this);
		setSize(400,100);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(btnLimpiar.equals(arg0.getSource()))
		{
			listado.select(0);
			utilidad.registrarLog(NombreUsuario,"Limpiando lista de Edicion Articulo");
		}
		else if(btnAceptar.equals(arg0.getSource()))
		{
			if(listado.getSelectedItem().equals("Seleccionar un Articulo..."))
			{
				// No hacemos nada
			}
			else
			{
				// Coger el elemento seleccionado
				String[] tabla = listado.getSelectedItem().split("-");
				// El idCliente que quiero editar est� en tabla[0]
				idArticuloEditar = Integer.parseInt(tabla[0]);
				setVisible(false);
				new ModificarArticulos(NombreUsuario, idArticuloEditar); 
			}
		}
	}
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		setVisible(false);
		utilidad.registrarLog(NombreUsuario,"Saliendo de Edicion Articulo");
	}
	@Override
	public void windowActivated(WindowEvent arg0){}
	@Override
	public void windowClosed(WindowEvent arg0){}
	@Override
	public void windowDeactivated(WindowEvent arg0){}
	@Override
	public void windowDeiconified(WindowEvent arg0){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowOpened(WindowEvent e){}
}
