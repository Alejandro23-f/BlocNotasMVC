package es.studium.BlocNotasMVC;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BlocNotasControlador implements WindowListener, ActionListener
{
	BlocNotasVista bnv = null;
	BlocNotasModelo bnm = null;

	public BlocNotasControlador(BlocNotasVista bnv, BlocNotasModelo bnm)
	{
		this.bnv = bnv;
		this.bnm = bnm;
		// Escuchador de la ventana
		bnv.addWindowListener(this);
		// Para poder cerrar el Di�logo
		bnv.d.addWindowListener(this);
		// Listeners de las opciones de los submen�s
		bnv.archivoNuevo.addActionListener(this);
		bnv.archivoAbrir.addActionListener(this);
		bnv.archivoGuardar.addActionListener(this);
		bnv.archivoSalir.addActionListener(this);

		bnv.gestionContarPalabras.addActionListener(this);
		bnv.gestionContarLetras.addActionListener(this);
		bnv.gestionContarVocales.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae) 
	{
		Object a;
		a = ae.getSource();
		// Archivo Nuevo
		if(a.equals(bnv.archivoNuevo))
		{
			// Borramos el textarea
			bnv.ta.selectAll();
			bnv.ta.setText("");
		}
		// Archivo Abrir
		else if(a.equals(bnv.archivoAbrir))
		{
			bnv.fd.setMode(FileDialog.LOAD);
			// Establecer el Tipo de fichero
			bnv.fd.setFile("*.txt");
			// Mostrar el FiledDialog fd
			bnv.fd.setVisible(true);
			// Elaboramos la ruta al fichero y el nombre con los datos aportados por el usuario
			String filename = bnv.fd.getDirectory()+bnv.fd.getFile();

			// Si el archivo NO existe
			if (filename == "")
			{
				// Mostrar el Di�logo
				bnv.mostrarDialogo("Archivo No encontrado", "Archivo No encontrado");
				bnv.d.addWindowListener(this);
			}
			else
			{
				try
				{
					// Buffer de lectura enlazado al FileReader que enlaza con el fichero f�sico
					BufferedReader entrada = new BufferedReader(new FileReader(filename));
					String s;
					// Vaciamos el textarea
					bnv.ta.selectAll();
					bnv.ta.setText("");
					// Bucle para sacar toda la informaci�n del archivo l�nea a l�nea
					while((s=entrada.readLine())!=null)
					{
						// A�adimos el texto al final del textarea
						bnv.ta.append(s);
						// A�adimos un salto de l�nea en el textarea para que cada l�nea aparezca por separado
						bnv.ta.append("\n");
					}
					// Cerrar el objeto entrada
					entrada.close();
				}
				catch(IOException i)
				{
					// Mostrar el Di�logo
					bnv.mostrarDialogo("Archivo No encontrado","Archivo No encontrado");
					bnv.d.addWindowListener(this);
				}
			}
		}
		// Archivo Guardar
		else if(a.equals(bnv.archivoGuardar))
		{
			try
			{
				bnv.fd.setMode(FileDialog.SAVE);
				// Establecer el Tipo de fichero
				bnv.fd.setFile("*.txt");
				// Mostrar el FiledDialog fd
				bnv.fd.setVisible(true);
				// Elaboramos la ruta al fichero y el nombre con los datos aportados por el usuario
				String filename = bnv.fd.getDirectory()+bnv.fd.getFile();
				// Buffer de escritura enlazado al FileWriter que enlaza con el fichero f�sico
				BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
				// Objeto para la escritura en el Buffer
				PrintWriter salida = new PrintWriter(bw);
				// Copia en el archivo el contenido del textarea
				salida.println(bnv.ta.getText());
				// Cerrar objetos de salida
				bw.close();
				salida.close();

				// Mostrar el Di�logo
				bnv.mostrarDialogo("Archivo guardado","Guardado archivo " + bnv.fd.getFile());
				bnv.d.addWindowListener(this);
			}
			catch(IOException i)
			{
				// Mostrar el Di�logo
				bnv.mostrarDialogo("Error al guardar Archivo", "Error al guardar Archivo");
				bnv.d.addWindowListener(this);
			}            
		}
		// Archivo Salir
		else if(a.equals(bnv.archivoSalir))
		{
			System.exit(0);
		}
		else if(a.equals(bnv.gestionContarPalabras))
		{
			// Sacamos el contenido del TextArea
			String miCadena = bnv.ta.getText();
			int numPalabras = bnm.gestionContarPalabras(miCadena);

			// Mostrar el Di�logo
			bnv.mostrarDialogo("Contador de Palabras","Hay " + numPalabras + " palabras en el texto.");
			bnv.d.addWindowListener(this);
		}
		else if(a.equals(bnv.gestionContarLetras))
		{
			int numLetras = 0;

			// Sacamos el contenido del TextArea
			String texto = bnv.ta.getText();
			numLetras = bnm.gestionContarLetras(texto);

			// Mostrar el Di�logo
			bnv.mostrarDialogo("Contador de Letras", "Hay " + numLetras + " letras en el texto.");
			bnv.d.addWindowListener(this);
		}
		else 
		{
			int numVocales = 0;

			// Sacamos el contenido del TextArea
			String texto = bnv.ta.getText();
			numVocales = bnm.gestionContarVocales(texto);

			// Mostrar el Di�logo
			bnv.mostrarDialogo("Contador de Vocales", "Hay " + numVocales + " vocales en el texto.");
			bnv.d.addWindowListener(this);
		}      
	}
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) 
	{
		// Si el di�logo tiene el foco
		if(bnv.d.hasFocus())
		{
			// Ocultamos la ventana
			bnv.d.setVisible(false);
		}
		// Si no tiene el foco, cerramos el programa
		else
		{
			System.exit(0);
		}
	}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
}
