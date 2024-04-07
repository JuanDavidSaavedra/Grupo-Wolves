# Cómo compilar el código
## 1.Vaya a la carpeta src/ 

## 2.Abra el terminal en la ruta actual

## 3.Ejecute el comando "javac CodeWriter.java".
	      "javac Parser.java
	      "javac VMtranslator.java
	      (es necesario tener instalado java)

## 4.Después de compilar, habrá un archivo "CodeWriter.class", un "Parser.class" y un "VMtranslator.class" en la ruta actual.

# Cómo ejecutar el código

## 1.El formato del comando es "java VMtranslator directory".

## Ejemplo:
    ### 1: java VMtranslator "C:\Users\Ipwnkidneyz\Documents\Nueva carpeta\Codigo Proyecto 07 - Aritmética de pila\src"
	
    ### 2.Si el argumento es un directorio, después de terminar la traducción, habrá un archivo VMtranslator.asm que se llama por el nombre del directorio y este archivo estará en este directorio.

    ### Y habrá un mensaje impreso en el terminal "Archivo creado : C:\Users\Ipwnkidneyz\Documents\Nueva carpeta\Codigo Proyecto 07 - Aritmética de pila\MemoryAccess\BasicTest/archivoTraducido.asm."

# Acerca de las excepciones: 

## 1.todos los errores de traducción se presentarán lanzando Excepciones. 



