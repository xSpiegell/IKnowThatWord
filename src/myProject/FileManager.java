package myProject;

import java.io.*;
import java.util.ArrayList;

/**
 * Esta clase funciona para la lectura y escritura en documentos de txt
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version v.1.0.0 date:4/02/2022
 */

public class FileManager {
    public static final String PALABRAS="src/myProject/files/palabras.txt";
    public static final String USUARIOS="src/myProject/files/usuarios.txt";
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;
    private ArrayList<String> usuarios;
    private ArrayList<String> niveles;

    public FileManager(){
        usuarios = new ArrayList<>();
        niveles = new ArrayList<>();
    }

    /**
     * Retorna la lista de usuarios
     * @return usuarios
     */
    public ArrayList<String> getUsuarios(){
        return usuarios;
    }

    /**
     * retorna la lista de niveles de los jugadores
     * @return
     */
    public ArrayList<String> getNiveles(){
        return niveles;
    }

    /**
     * Lee el archivo palabras.txt
     * @param cantidadPalabras
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
     * Lee usuarios.txt
     */
    public void lecturaFileUsuarios(){
        try {
            fileReader = new FileReader(USUARIOS);
            input = new BufferedReader(fileReader);
            String line = input.readLine();

            // Agrega una cantidad de palabras al arraylist dependiendo del nivel
            while(line != null){
                String data = line;
                String[] datosUsuarioArray = data.split(", ");
                usuarios.add(datosUsuarioArray[0]);
                niveles.add(datosUsuarioArray[1]);
                datosUsuarioArray = new String[datosUsuarioArray.length];
                line = input.readLine();
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
    }

    /**
     * Escribe nuevos usuarios en el archivo de usuarios.txt
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

    /**
     * Reesscribe el nivel actual del jugador
     * @param nombre
     * @param nuevoNivel
     */
    public void reescribirArchivoUsuarios(String nombre, String nuevoNivel){
        // Se borran los elementos de las listas para que no hayan conflictos al reescribir el archivo de texto
        usuarios.clear();
        niveles.clear();
        lecturaFileUsuarios();
        try {
            if(usuarios.contains(nombre)){
                niveles.set(usuarios.indexOf(nombre), nuevoNivel);
            }

            fileWriter = new FileWriter(USUARIOS, false);
            output = new BufferedWriter(fileWriter);

            for(int i=0; i < usuarios.size(); i++){
                output.write(usuarios.get(i) + ", " + niveles.get(i));
                output.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}