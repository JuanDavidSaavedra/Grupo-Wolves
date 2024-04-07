# Compilación del Código

1. Abra la línea de comandos y navegue hasta la carpeta `src/`.

2. Ejecute los siguientes comandos para compilar los archivos Java necesarios:
    ```
    javac CodeWriter.java
    javac Parser.java
    javac VMtranslator.java
    ```
   Es necesario tener Java instalado en su sistema para poder compilar los archivos.

3. Después de compilar, se generarán los archivos `CodeWriter.class`, `Parser.class` y `VMtranslator.class` en la misma carpeta.

# Ejecución del Código

1. El formato del comando para ejecutar el programa es:
   ```
   java VMtranslator directory
   ```
   Donde `directory` es la ruta del directorio que contiene los archivos VM que se desean traducir.

### Ejemplos:

   a. Para traducir todos los archivos VM en el directorio `C:\Users\xuc\system\VMtranslator`, ejecute:
   ```
   java VMtranslator C:\Users\xuc\system\VMtranslator
   ```

   Después de completar la traducción, se creará un archivo `VMtranslator.asm` con el nombre del directorio en el mismo directorio donde se ejecutó el comando. Además, se imprimirá un mensaje en la línea de comandos indicando la creación del archivo.

   b. Para traducir un archivo específico, como `C:\Users\xuc\system\VMtranslator\StaticTest.vm`, ejecute:
   ```
   java VMtranslator C:\Users\xuc\system\VMtranslator\StaticTest.vm
   ```

   Después de completar la traducción, se creará un archivo `StaticTest.asm` con el nombre del archivo VM en el mismo directorio que el archivo VM. Además, se imprimirá un mensaje en la línea de comandos indicando la creación del archivo.

# Acerca de las Excepciones

1. Todos los errores de traducción se manejarán lanzando excepciones. Esto significa que si ocurre algún error durante el proceso de traducción, se informará al usuario mediante una excepción.
