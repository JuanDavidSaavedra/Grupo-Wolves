# Cómo compilar el código

1. Navegue hasta la carpeta `src_Java/`.

2. Abra el terminal en la ruta actual:
   - Haga clic derecho en el directorio que contiene la carpeta `src_Java`.
   - Abra el cmd con "Windows + R, y escriba 'CMD'" para la ventana de comandos o "Escriba cmd en el directorio en donde está `src`".

3. Ejecute los siguientes comandos para compilar los archivos Java necesarios:
    ```
    javac CodeWriter.java
    javac CommandType.java
    javac Parser.java
    javac VirtualMemory.java
    javac VMtranslator.java
    ```
   Es necesario tener Java instalado en su sistema para poder compilar los archivos.

4. Después de compilar, se generarán los archivos `CodeWriter.class`, `CommandType.class`, `Parser.class`, `VirtualMemory.class` y `VMtranslator.class` en la misma carpeta.

# Cómo ejecutar el código

1. El formato del comando es:
   ```
   java VMtranslator directory
   ```
   Donde `directory` es la ruta del directorio que contiene los archivos VM que se desean traducir.

### Ejemplo:

   - Para traducir todos los archivos VM en el directorio `C:\Users\Ipwnkidneyz\Documents\Nueva carpeta\Codigo Proyecto 08 - Programas de control\src_Java`, ejecute:
     ```
     java VMtranslator "C:\Users\Ipwnkidneyz\Documents\Nueva carpeta\Codigo Proyecto 08 - Programas de control\MemoryAccess\StaticsTest"
     ```

     Después de completar la traducción, se creará un archivo `.asm` con el nombre del directorio en el mismo directorio donde se ejecutó el comando (en este caso `StaticsTest.asm`). Además, se imprimirá un mensaje en el terminal indicando la creación del archivo.
---

# Cómo ejecutar el código alternativo (`VMTranslator.py`)