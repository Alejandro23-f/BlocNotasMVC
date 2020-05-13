package es.studium.BlocNotasMVC;

import java.util.StringTokenizer;

public class BlocNotasModelo
{
	public BlocNotasModelo(){}

	public int gestionContarPalabras(String miCadena)
	{
		// Buscamos palabras separadas por " " y por \n o Intro
		StringTokenizer auxiliar1 = new StringTokenizer(miCadena, " ");
		StringTokenizer auxiliar2 = new StringTokenizer(miCadena, "\n");
		int numPalabras = auxiliar1.countTokens() + auxiliar2.countTokens();
		// Si el TextArea tiene contenido debemos quitar uno, pues cuenta uno de m�s siempre
		if(numPalabras!=0)
		{
			numPalabras--;
		}
		return numPalabras;
	}
	
	public int gestionContarLetras(String texto)
	{
		int numLetras = 0;
		char c = 0;
		// Contamos Letras. No se cuentan los espacios y ni otros s�mbolos de control
		for(int i=0; i<texto.length();i++)
		{
			c = texto.charAt(i);

			if(Character.isLetter(c))
				numLetras++;
		}
		return numLetras;
	}
	
	public int gestionContarVocales(String texto)
	{
		int numVocales = 0;
		char c = 0;
		for(int i=0; i<texto.length();i++)
		{
			// Recorremos letra a letra 
			c = texto.charAt(i);

			if(Character.isLetter(c))
			{
				// Vocales min�sculas sin tilde
				if ((c=='a')||(c=='e')||(c=='i')||(c=='o')||(c=='u'))
				{
					numVocales++;
				}
				// Vocales min�sculas con tilde
				else if ((c=='�')||(c=='�')||(c=='�')||(c=='�')||(c=='�'))
				{
					numVocales++;
				}
				// Vocales may�sculas sin tilde
				else if ((c=='A')||(c=='E')||(c=='I')||(c=='O')||(c=='U'))
				{
					numVocales++;
				}
				// Vocales may�sculas con tilde
				else if ((c=='�')||(c=='�')||(c=='�')||(c=='�')||(c=='�'))
				{
					numVocales++;
				}
			}
		}
		return numVocales;
	}
}
