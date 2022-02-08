package myProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase funciona para la lectura y escritura en documentos de txt
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version v.1.0.0 date:4/02/2022
 */

public class FileManager {
    public static final String PALABRAS="src/myProject/files/palabras.txt"; //Se asigna la direccion del txt a una variable
    public static final String USUARIOS="src/myProject/files/usuarios.txt"; //Se asigna la direccion del txt a una va
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;
    private File file;
    private Scanner scan;
    private ArrayList<String> usuarios;
    private ArrayList<String> niveles;

    public FileManager(){
        usuarios = new ArrayList<>();
        niveles = new ArrayList<>();
    }

    public ArrayList<String> getUsuarios(){
        return usuarios;
    }

    public ArrayList<String> getNiveles(){
        return niveles;
    }

    /**
     * Sirve para leer el archivo de txt de palabras
     * @return frases
     */
    public ArrayList<String> lecturaFilePalabras(int cantidadPalabras){
        ArrayList<String> frases = new ArrayList<>();
        int contador = 1;

        try {
            fileReader = new FileReader(PALABRAS);
            input = new BufferedReader(fileReader);
            String line = input.readLine();

            // Agrega una cantidad de palabras al arraylist dependiendo del nivel
            while(line != null && contador <= cantidadPalabras){
                frases.add(line);
                line = input.readLine();
                contador++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return frases;
    }
    /**
     * Sirve para leer el archivo de txt de usuarios
     */
    public void lecturaFileUsuarios(){
        try {
            file = new File(USUARIOS);
            scan = new Scanner(file);

            while(scan.hasNextLine()){
                String data = scan.nextLine();
                String[] datosUsuarioArray = data.split(", ");
                usuarios.add(datosUsuarioArray[0]);
                niveles.add(datosUsuarioArray[1]);
                datosUsuarioArray = new String[datosUsuarioArray.length];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            scan.close();
        }
    }
    /**
     * Sirve para escribir en el archivo de usuarios
     * @param line
     */
    public void escribirFile(String line){
        try {
            fileWriter = new FileWriter(USUARIOS,true);
            output = new BufferedWriter(fileWriter);
            output.write(line);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}