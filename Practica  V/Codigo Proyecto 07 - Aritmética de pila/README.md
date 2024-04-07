# Cómo compilar el código

1. Navegue hasta la carpeta `src/`.

2. Abra el terminal en la ruta actual:
   - Haga clic derecho en el directorio que contiene la carpeta `src`.
   - Seleccione "Abrir ventana de comandos aquí" o "Abrir PowerShell aquí".

3. Ejecute los siguientes comandos para compilar los archivos Java necesarios:
    ```
    javac CodeWriter.java
    javac Parser.java
    javac VMtranslator.java
    ```
   Es necesario tener Java instalado en su sistema para poder compilar los archivos.

4. Después de compilar, se generarán los archivos `CodeWriter.class`, `Parser.class` y `VMtranslator.class` en la misma carpeta.

# Cómo ejecutar el código

1. El formato del comando es:
   ```
   java VMtranslator directory
   ```
   Donde `directory` es la ruta del directorio que contiene los archivos VM que se desean traducir.

### Ejemplo:

   - Para traducir todos los archivos VM en el directorio `C:\Users\Ipwnkidneyz\Documents\Nueva carpeta\Codigo Proyecto 07 - Aritmética de pila\src`, ejecute:
     ```
     java VMtranslator "C:\Users\Ipwnkidneyz\Documents\Nueva carpeta\Codigo Proyecto 07 - Aritmética de pila\src"
     ```

     Después de completar la traducción, se creará un archivo `VMtranslator.asm` con el nombre del directorio en el mismo directorio donde se ejecutó el comando. Además, se imprimirá un mensaje en el terminal indicando la creación del archivo.

# Acerca de las excepciones

1. Todos los errores de traducción se manejarán lanzando excepciones. Esto significa que si ocurre algún error durante el proceso de traducción, se informará al usuario mediante una excepción.
