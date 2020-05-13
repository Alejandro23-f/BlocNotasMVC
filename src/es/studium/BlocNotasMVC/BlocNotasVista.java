package es.studium.BlocNotasMVC;

import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;

public class BlocNotasVista extends Frame
{
	private static final long serialVersionUID = 1L;

	// En primer lugar creamos la barra de men�
	MenuBar barraMenu = new MenuBar();

	// Ahora creamos los elementos principales del men�
	Menu archivo = new Menu("Archivo");
	Menu gestion = new Menu("Gesti�n");

	// MenuItem del men� archivo
	MenuItem archivoNuevo = new MenuItem("Nuevo");
	MenuItem archivoAbrir = new MenuItem("Abrir");
	MenuItem archivoGuardar = new MenuItem("Guardar");
	MenuItem archivoSalir = new MenuItem("Salir");

	// MenuItem del men� gestion
	MenuItem gestionContarPalabras = new MenuItem("Contar palabras");
	MenuItem gestionContarLetras = new MenuItem("Contar letras");
	MenuItem gestionContarVocales = new MenuItem("Contar vocales");

	// Creamos el textarea
	TextArea ta = new TextArea(20,60);

	// Creamos la ventana de di�logo
	Dialog d = new Dialog(this, "", true);

	// Y una etiqueta
	Label lblDialogo = new Label();
	
	// FileDialog
	FileDialog fd = new FileDialog(this, "Indicar ubicaci�n y nombre del archivo");

	public BlocNotasVista()
	{
		// SIN Layout para que ocupe toda la pantalla y que se adapte siempre que esta cambie de tama�o
		setTitle("Bloc de Notas MVC");

		// Establecemos la barra de men�
		setMenuBar(barraMenu);

		// A�adimos los elementos al men� archivo
		archivo.add(archivoNuevo);
		archivo.add(archivoAbrir);
		archivo.add(archivoGuardar);
		archivo.addSeparator();
		archivo.add(archivoSalir);

		// A�adimos los elementos al men� gestion
		gestion.add(gestionContarPalabras);
		gestion.add(gestionContarLetras);
		gestion.add(gestionContarVocales);

		// Por �ltimo agregamos los elementos archivo y gestion a la barra
		barraMenu.add(archivo);
		barraMenu.add(gestion);

		// A�adimos el textarea
		this.add(ta);

		setSize(450,400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void mostrarDialogo(String titulo, String mensajeLbl)
	{
		d.setLayout(new FlowLayout());
		d.setTitle(titulo);
		lblDialogo.setText(mensajeLbl);            
		d.add(lblDialogo);
		d.setSize(200,150);
		d.setLocationRelativeTo(null);
		d.setVisible(true);
	}
}
