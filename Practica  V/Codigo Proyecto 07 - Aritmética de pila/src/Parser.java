import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ipwnkidneyz
 */
/**
 * Maneja el análisis de un único archivo .vm y encapsula el acceso al código de entrada.
 * Lee comandos VM, los analiza y proporciona acceso conveniente a sus componentes.
 * Además, elimina todos los espacios en blanco y comentarios.
 */
public class Parser {
    private Scanner cmds;
    private String currentCmd;
    public static final int ARITHMETIC = 0;
    public static final int PUSH = 1;
    public static final int POP = 2;
    public static final int LABEL = 3;
    public static final int GOTO = 4;
    public static final int IF = 5;
    public static final int FUNCTION = 6;
    public static final int RETURN = 7;
    public static final int CALL = 8;
    public static final ArrayList<String> arithmeticCmds = new ArrayList<String>();
    private int argType;
    private String argument1;
    private int argument2;

    static {

        arithmeticCmds.add("add");arithmeticCmds.add("sub");arithmeticCmds.add("neg");arithmeticCmds.add("eq");arithmeticCmds.add("gt");
        arithmeticCmds.add("lt");arithmeticCmds.add("and");arithmeticCmds.add("or");arithmeticCmds.add("not");

    }

    /**
     * Abre el archivo de entrada y se prepara para analizarlo.
     * @param fileIn
     */
    public Parser(File fileIn) {

        argType = -1;
        argument1 = "";
        argument2 = -1;

        try {
            cmds = new Scanner(fileIn);

            String preprocessed = "";
            String line = "";

            while(cmds.hasNext()){

                line = noComments(cmds.nextLine()).trim();

                if (line.length() > 0) {
                    preprocessed += line + "\n";
                }
            }

            cmds = new Scanner(preprocessed.trim());

        } catch (FileNotFoundException e) {
            System.out.println("¡Archivo no encontrado!");
        }

    }


    /**
     * ¿Hay más comandos para leer?
     * @return
     */
    public boolean hasMoreCommands(){

       return cmds.hasNextLine();
    }

    /**
     * Lee el siguiente comando de la entrada y lo hace el comando actual.
     * Solo se llama cuando hasMoreCommands() devuelve true.
     */
    public void advance(){

        currentCmd = cmds.nextLine();
        argument1 = "";//inicializar arg1
        argument2 = -1;//inicializar arg2

        String[] segs = currentCmd.split(" ");

        if (segs.length > 3){

            throw new IllegalArgumentException("¡Demasiados argumentos!");

        }

        if (arithmeticCmds.contains(segs[0])){

            argType = ARITHMETIC;
            argument1 = segs[0];

        }else if (segs[0].equals("return")) {

            argType = RETURN;
            argument1 = segs[0];

        }else {

            argument1 = segs[1];

            if(segs[0].equals("push")){

                argType = PUSH;

            }else if(segs[0].equals("pop")){

                argType = POP;

            }else if(segs[0].equals("label")){

                argType = LABEL;

            }else if(segs[0].equals("if")){

                argType = IF;

            }else if (segs[0].equals("goto")){

                argType = GOTO;

            }else if (segs[0].equals("function")){

                argType = FUNCTION;

            }else if (segs[0].equals("call")){

                argType = CALL;

            }else {

                throw new IllegalArgumentException("¡Tipo de comando desconocido!");

            }

            if (argType == PUSH || argType == POP || argType == FUNCTION || argType == CALL){

                try {

                    argument2 = Integer.parseInt(segs[2]);

                }catch (Exception e){

                    throw new IllegalArgumentException("¡El argumento 2 no es un entero!");

                }

            }
        }

    }

    /**
     * Devuelve el tipo del comando actual.
     * Se devuelve ARITHMETIC para todos los comandos de tipo ARITHMETIC.
     * @return
     */
    public int commandType(){

        if (argType != -1) {

            return argType;

        }else {

            throw new IllegalStateException("¡Ningún comando!");

        }

    }

    /**
     * Devuelve el primer argumento del comando actual.
     * Cuando es ARITHMETIC, se devuelve a sí mismo.
     * Cuando es RETURN, no debería ser llamado.
     * @return
     */
    public String arg1(){

        if (commandType() != RETURN){

            return argument1;

        }else {

            throw new IllegalStateException("¡No se puede obtener arg1 de un comando de tipo RETURN!");

        }

    }

    /**
     * Devuelve el segundo argumento del comando actual.
     * Se llama cuando es PUSH, POP, FUNCTION o CALL.
     * @return
     */
    public int arg2(){

        if (commandType() == PUSH || commandType() == POP || commandType() == FUNCTION || commandType() == CALL){

            return argument2;

        }else {

            throw new IllegalStateException("¡No se puede obtener arg2!");

        }

    }

    /**
     * Elimina los comentarios (Cadena después de "//") de una cadena.
     * @param strIn
     * @return
     */
    public static String noComments(String strIn){

        int position = strIn.indexOf("//");

        if (position != -1){

            strIn = strIn.substring(0, position);

        }

        return strIn;
    }

    /**
     * Elimina los espacios de una cadena.
     * @param strIn
     * @return
     */
    public static String noSpaces(String strIn){
        String result = "";

        if (strIn.length() != 0){

            String[] segs = strIn.split(" ");

            for (String s: segs){
                result += s;
            }
        }

        return result;
    }

    /**
     * Obtiene la extensión de un nombre de archivo.
     * @param fileName
     * @return
     */
    public static String getExt(String fileName){

        int index = fileName.lastIndexOf('.');

        if (index != -1){

            return fileName.substring(index);

        }else {

            return "";

        }
    }
}
