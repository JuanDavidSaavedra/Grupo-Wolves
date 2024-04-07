import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Ipwnkidneyz
 */
/**
 * Clase principal que traduce archivos .vm a código ensamblador de Hack (.asm)
 */
public class VMtranslator {

    /**
     * Retorna todos los archivos .vm en un directorio
     * @param dir
     * @return
     */
    public static ArrayList<File> getVMFiles(File dir){

        File[] files = dir.listFiles();

        ArrayList<File> result = new ArrayList<File>();

        for (File f:files){

            if (f.getName().endsWith(".vm")){

                result.add(f);

            }

        }

        return result;

    }

    public static void main(String[] args) {

        if (args.length != 1){

            System.out.println("Uso: java VMtranslator [nombre_archivo|directorio]");

        }else {

            File fileIn = new File(args[0]);

            String fileOutPath = "";

            File fileOut;

            CodeWriter writer;

            ArrayList<File> vmFiles = new ArrayList<File>();

            if (fileIn.isFile()) {

                // Si es un solo archivo, verifica si es un archivo .vm
                String path = fileIn.getAbsolutePath();

                if (!Parser.getExt(path).equals(".vm")) {

                    throw new IllegalArgumentException("¡Se requiere un archivo .vm!");

                }

                vmFiles.add(fileIn);

                fileOutPath = fileIn.getAbsolutePath().substring(0, fileIn.getAbsolutePath().lastIndexOf(".")) + ".asm";

            } else if (fileIn.isDirectory()) {

                // Si es un directorio, obtiene todos los archivos .vm en este directorio
                vmFiles = getVMFiles(fileIn);

                // Si no hay archivos .vm en este directorio
                if (vmFiles.size() == 0) {

                    throw new IllegalArgumentException("No hay archivos .vm en este directorio");

                }

                fileOutPath = fileIn.getAbsolutePath() + "/" +  fileIn.getName() + ".asm";
            }

            fileOut = new File(fileOutPath);
            writer = new CodeWriter(fileOut);

            for (File f : vmFiles) {

                Parser parser = new Parser(f);

                int type = -1;

                // Comienza el análisis
                while (parser.hasMoreCommands()) {

                    parser.advance();

                    type = parser.commandType();

                    if (type == Parser.ARITHMETIC) {

                        writer.writeArithmetic(parser.arg1());

                    } else if (type == Parser.POP || type == Parser.PUSH) {

                        writer.writePushPop(type, parser.arg1(), parser.arg2());

                    }

                }

            }

            // Guarda el archivo
            writer.close();

            System.out.println("Archivo creado: " + fileOutPath);
        }
    }

}
