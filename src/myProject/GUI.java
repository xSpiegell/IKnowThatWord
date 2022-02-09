package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Esta es la clase principal
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version v.1.0.0 date:4/02/2022
 */
public class GUI extends JFrame {
    public  static final String CREDITOS = "CRÉDITOS\n" +
            "-> BRAYAN STIVEN SANCHEZ LEON\n" +
            "-> MAYRA ALEJANDRA SANCHEZ SALINAS";

    public  static final String AYUDA = "Este  ejercicio  entrena  la  memoria  episódica  verbal  reciente.  Es  importante  en  tareas  donde  es  útil \nreconocer  qué información  hemos  oído  antes,  como  en conversaciones,  películas  o  ir  a  la  compra.\n"+"\n"+
            "El  juego  consiste en  presentar  al  jugador  una  secuencia  de palabras  de una  en  una,  es  decir,  aparece  una\npalabra,  dura  5  segundos  en pantalla,  luego  se  borra  y  aparece  la  siguiente.   El  jugador  deberá  memorizar\nlas palabras  que van  apareciendo.\n"+"\n"+
            "Tras la serie  de  palabras  a memorizar,  el  juego  presentará un  listado  con  el  doble  de  palabras que  se\nmostraron. Por  cada  una  las  palabras  el  jugador  deberá  indicar  si  la  palabra  estaba  o  no  contenida  en  el\nlistado  a  memorizar  y  tendrá  un  tiempo  de  7  segundos  para  responder,  en  caso  de no  hacerlo  se tomará\ncomo un  error.";

    private Header headerProject;
    private Escucha escucha;
    private JButton registro, ayuda, salir, creditos, empezar, minimizar, botonSi, botonNo;
    private JPanel alias, nivel, informacion, juego, subPanel;
    private JLabel palabraAleatoria, mostrarNivel, mensaje, mostrarAlias;
    private ImageIcon imageExplicacion;
    private Timer timer;
    private FileManager fileManager;
    private int nivelActual;
    private int seleccionarPalabra;
    private int aciertos;
    private double porcentajeAciertos;
    private ArrayList<Integer> delaysTimer;
    private String nombreUsuario;
    private JTextField linea;
    private ArrayList<String> usuarios;
    private ArrayList<String> niveles;

    /**
     * Constructor de la clase GUI
     */
    public GUI(){
        initGUI();

        // Default JFrame configuration
        this.setTitle("I Know That Word");
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        // Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // Create Listener Object and Control Object
        escucha = new Escucha();
        fileManager = new FileManager();
        // Set up JComponents
        headerProject = new Header("I know that word", Color.pink);

        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridwidth = 5;
        constraints.fill=GridBagConstraints.BOTH;
        this.add(headerProject,constraints);

        // Creación botón salir
        salir = new JButton("x");
        salir.addActionListener(escucha);
        salir.setBackground(Color.red);
        salir.setFocusable(false);
        constraints.gridx = 11;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir,constraints);

        // Creación botón ayuda
        ayuda = new JButton(" ? ");
        ayuda.addActionListener(escucha);
        ayuda.setBackground(new Color(82, 140, 255, 255));
        ayuda.setFocusable(false);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        // Creación botón minimizar
        minimizar = new JButton("-");
        minimizar.addActionListener(escucha);
        minimizar.setBackground(Color.cyan);
        minimizar.setFocusable(false);
        constraints.gridx = 10;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LAST_LINE_END;
        this.add(minimizar,constraints);

        // Creación botón créditos
        creditos = new JButton(" Créditos ");
        creditos.addActionListener(escucha);
        creditos.setBackground((new Color(194, 161, 108, 255)));
        creditos.setFocusable(false);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(creditos,constraints);

        // Creación botón empezar
        empezar = new JButton(" Iniciar ");
        empezar.addActionListener(escucha);
        empezar.setBackground(Color.yellow);
        empezar.setFocusable(false);
        empezar.setEnabled(false);
        constraints.gridx = 5;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.CENTER;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(empezar,constraints);

        // Creación botón registro
        registro = new JButton(" Registrarse / Iniciar sesión  ");
        registro.addActionListener(escucha);
        registro.setBackground(Color.ORANGE);
        registro.setFocusable(false);
        constraints.gridx = 5;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.CENTER;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(registro,constraints);

        // Panel nombre
        alias = new JPanel();
        alias.setPreferredSize(new Dimension(200,50));
        alias.setBorder(BorderFactory.createTitledBorder("Alias"));
        alias.setBackground(new Color(178, 161, 255,152));
        alias.setLayout(new BorderLayout());

        constraints.gridx = 5;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.LINE_START;
        add(alias,constraints);

        mostrarAlias = new JLabel();
        mostrarAlias.setHorizontalAlignment(JLabel.CENTER);

        botonSi = new JButton("Si");
        botonNo = new JButton("No");
        subPanel = new JPanel();
        subPanel.add(botonSi);
        subPanel.add(botonNo);

        // Panel nivel
        nivel = new JPanel();
        nivel.setPreferredSize(new Dimension(200,50));
        nivel.setBorder(BorderFactory.createTitledBorder("Nivel"));
        nivel.setLayout(new BorderLayout());
        nivel.setBackground(new Color(255, 210, 142,152));

        constraints.gridx = 5;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(nivel,constraints);

        mostrarNivel = new JLabel();
        mostrarNivel.setHorizontalAlignment(JLabel.CENTER);

        // Panel juego
        juego = new JPanel();
        juego.setPreferredSize(new Dimension(300,250));
        juego.setBorder(BorderFactory.createTitledBorder("Presta atención a la palabras"));
        juego.setLayout(new BorderLayout());
        juego.setBackground(Color.white);

        constraints.gridx = 5;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.LINE_START;
        add(juego,constraints);

        palabraAleatoria = new JLabel();
        palabraAleatoria.setHorizontalAlignment(JLabel.CENTER);

        // Panel aciertos, errores y resultado
        informacion = new JPanel();
        informacion.setPreferredSize(new Dimension(200,100));
        informacion.setBorder(BorderFactory.createTitledBorder("Información de la partida"));
        informacion.setLayout(new BorderLayout());
        informacion.setBackground(new Color(81, 221, 241,152));

        constraints.gridx = 5;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.LINE_START;
        add(informacion,constraints);

        mensaje = new JLabel();
        mensaje.setHorizontalAlignment(JLabel.CENTER);

        delaysTimer = new ArrayList<>(2);
        delaysTimer.add(5000);
        delaysTimer.add(7000);
        timer = new Timer(delaysTimer.get(0), escucha);

        nivelActual = 1;
        seleccionarPalabra = 0; // 1 si debe seleccionar las palabras que memorizo, de lo contrario 0
        aciertos = 0;
        porcentajeAciertos = 0.0;
        usuarios = new ArrayList<>();
        niveles = new ArrayList<>();
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {
        private ArrayList<String> palabrasAMemorizar;
        private ArrayList<String> listaAMemorizar;
        private ArrayList<Integer> numerosAleatorios;
        private ArrayList<Integer> numerosAleatoriosExtra;
        private Random random;
        private int palabra;
        private int aleatorio;
        private int respuesta;

        public Escucha(){
            palabrasAMemorizar = new ArrayList<>();
            numerosAleatorios = new ArrayList<>();
            numerosAleatoriosExtra = new ArrayList<>();
            listaAMemorizar = new ArrayList<>();
            random = new Random();
            palabra = 0;
            aleatorio = 0;
            respuesta = 0; // 0 si selecciono YES, de lo contrario 1
        }

        public void activarDesactivarBotonesSecundarios(String estado){
            if(estado == "activar"){
                botonSi.addActionListener(escucha);
                botonNo.addActionListener(escucha);
                botonSi.setEnabled(true);
                botonNo.setEnabled(true);
            }else{
                botonSi.setEnabled(false);
                botonNo.setEnabled(false);
            }
        }

        public boolean siguienteNivel(int nivel){
            boolean aprobacion = false;
            switch(nivel){
                case 1:
                case 2:
                    if(porcentajeAciertos >= 70){
                        aprobacion = true;
                    }
                    break;
                case 3:
                    if(porcentajeAciertos >= 75){
                        aprobacion = true;
                    }
                    break;
                case 4:
                case 5:
                    if(porcentajeAciertos >= 80){
                        aprobacion = true;
                    }
                    break;
                case 6:
                    if(porcentajeAciertos >= 85){
                        aprobacion = true;
                    }
                    break;
                case 7:
                case 8:
                    if(porcentajeAciertos >= 90){
                        aprobacion = true;
                    }
                    break;
                case 9:
                    if(porcentajeAciertos >= 95){
                        aprobacion = true;
                    }
                    break;
                case 10:
                    if(porcentajeAciertos >= 100){
                        aprobacion = true;
                    }
                    break;
            }

            if(aprobacion){
                nivelActual++;
                mensaje.setText("¡Pasaste al siguiente nivel!, obtuviste " + String.valueOf(porcentajeAciertos) + "\n" + "% en aciertos");
            }else{
                if(nivelActual == 11){
                    mensaje.setText("¡Has ganado el juego!");
                }else{
                    mensaje.setText("¡Perdiste!, obtuviste " + String.valueOf(porcentajeAciertos) + "% en aciertos");
                }
            }

            empezar.addActionListener(escucha);
            fileManager.reescribirArchivoUsuarios(nombreUsuario, String.valueOf(nivelActual)); // Reescribe el nivel del archivo usuarios.txt
            activarDesactivarBotonesSecundarios("desactivar");
            botonSi.removeActionListener(escucha);
            botonNo.removeActionListener(escucha);
            juego.removeAll();
            informacion.add(mensaje, BorderLayout.CENTER);
            palabrasAMemorizar.clear();
            listaAMemorizar.clear();
            numerosAleatorios.clear();
            numerosAleatoriosExtra.clear();
            palabra = 0;
            seleccionarPalabra = 0;
            aciertos = 0;
            porcentajeAciertos = 0;
            revalidate();
            repaint();
            return aprobacion;
        }

        public void setPalabrasAleatorias(){
            aleatorio = random.nextInt(palabrasAMemorizar.size());
            if(numerosAleatorios.size() == 0 || !numerosAleatorios.contains(aleatorio)){
                numerosAleatorios.add(aleatorio);
                listaAMemorizar.add(palabrasAMemorizar.get(aleatorio));
                palabraAleatoria.setText(palabrasAMemorizar.get(aleatorio));
                juego.add(palabraAleatoria, BorderLayout.CENTER);
            }else{
                setPalabrasAleatorias();
            }
            revalidate();
            repaint();
        }

        public void escogerPalabra() {
            aleatorio = random.nextInt(palabrasAMemorizar.size());

            if(numerosAleatoriosExtra.size() == 0 || !numerosAleatoriosExtra.contains(aleatorio)){
                numerosAleatoriosExtra.add(aleatorio);
                palabraAleatoria.setText(palabrasAMemorizar.get(aleatorio));
                juego.add(palabraAleatoria, BorderLayout.CENTER);
            }else{
                escogerPalabra();
            }
            revalidate();
            repaint();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(nivelActual){
                case 1: palabrasAMemorizar = fileManager.lecturaFilePalabras(20);
                        porcentajeAciertos = (aciertos/20.0) * 100.0;
                        break;
                case 2: palabrasAMemorizar = fileManager.lecturaFilePalabras(40);
                        porcentajeAciertos = (aciertos/40.0) * 100.0;
                        break;
                case 3: palabrasAMemorizar = fileManager.lecturaFilePalabras(50);
                        porcentajeAciertos = (aciertos/50.0) * 100.0;
                        break;
                case 4: palabrasAMemorizar = fileManager.lecturaFilePalabras(60);
                        porcentajeAciertos = (aciertos/60.0) * 100.0;
                        break;
                case 5: palabrasAMemorizar = fileManager.lecturaFilePalabras(70);
                        porcentajeAciertos = (aciertos/70.0) * 100.0;
                        break;
                case 6: palabrasAMemorizar = fileManager.lecturaFilePalabras(80);
                        porcentajeAciertos = (aciertos/80.0) * 100.0;
                        break;
                case 7: palabrasAMemorizar = fileManager.lecturaFilePalabras(100);
                        porcentajeAciertos = (aciertos/100.0) * 100.0;
                        break;
                case 8: palabrasAMemorizar = fileManager.lecturaFilePalabras(120);
                        porcentajeAciertos = (aciertos/120.0) * 100.0;
                        break;
                case 9: palabrasAMemorizar = fileManager.lecturaFilePalabras(140);
                        porcentajeAciertos = (aciertos/140.0) * 100.0;
                        break;
                case 10: palabrasAMemorizar = fileManager.lecturaFilePalabras(200);
                        porcentajeAciertos = (aciertos/200.0) * 100.0;
                        break;
            }

            if(e.getSource() == empezar){
                if(seleccionarPalabra == 0){
                    timer.setInitialDelay(delaysTimer.get(0));
                    timer.setDelay(delaysTimer.get(0));
                    timer.restart();
                    timer.start();
                    setPalabrasAleatorias();
                }else{
                    juego.add(subPanel, BorderLayout.SOUTH);
                    timer.setInitialDelay(delaysTimer.get(1));
                    timer.setDelay(delaysTimer.get(1));
                    timer.restart();
                    timer.start();
                    escogerPalabra();
                }

                informacion.removeAll();
                mostrarNivel.setText(String.valueOf(nivelActual));
                nivel.add(mostrarNivel, BorderLayout.CENTER);
                empezar.setEnabled(false);
                empezar.removeActionListener(escucha);
                revalidate();
                repaint();
            }else{
                if(e.getSource() == timer){
                    empezar.setEnabled(false);
                    palabra++;
                    if(seleccionarPalabra == 0){
                        if(palabra < palabrasAMemorizar.size() / 2){
                            setPalabrasAleatorias();
                        }else{
                            timer.stop();
                            palabra = 0;
                            seleccionarPalabra = 1;
                            activarDesactivarBotonesSecundarios("activar");
                            empezar.setEnabled(true);
                            empezar.addActionListener(escucha);
                            mensaje.setText("Continua para seleccionar las palabras que memorizaste");
                            informacion.add(mensaje, BorderLayout.CENTER);
                        }
                    }else{
                        botonSi.setEnabled(true);
                        botonNo.setEnabled(true);
                        if(palabra < palabrasAMemorizar.size()){
                            escogerPalabra();
                        }else{
                            timer.stop();
                            botonSi.removeActionListener(escucha);
                            botonNo.removeActionListener(escucha);

                            if(siguienteNivel(nivelActual)){
                                empezar.setEnabled(true);
                            }else{
                                empezar.setEnabled(false);
                            }
                        }
                    }
                }else{
                    if(e.getSource() == registro){
                        // Crea o busca un usuario en el juego
                        nombreUsuario = JOptionPane.showInputDialog(null,"Ingrese su nombre o su alias","Registro",1);
                        linea = new JTextField();
                        fileManager.lecturaFileUsuarios();
                        usuarios = fileManager.getUsuarios();
                        niveles = fileManager.getNiveles();

                        if (!usuarios.contains(nombreUsuario)){
                            linea.setText(nombreUsuario + ", " + String.valueOf(nivelActual));
                            fileManager.escribirFile(linea.getText());
                        }else{
                            nivelActual = Integer.parseInt(niveles.get(usuarios.indexOf(nombreUsuario)));
                        }

                        empezar.setEnabled(true);
                        registro.setEnabled(false);
                        mostrarAlias.setText(nombreUsuario);
                        alias.add(mostrarAlias);
                        revalidate();
                        repaint();
                    }else{
                        if (e.getSource() == creditos){
                            // Al presionar el botón CREDITOS, salen los nombres de los programadores que estan en la variable estatica CREDITOS
                            JOptionPane.showMessageDialog(null,CREDITOS,"Créditos",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            if (e.getSource() == ayuda){
                                // Al presionar el botón ?, salen las indicaciones que están en la variable estatica AYUDA
                                imageExplicacion = new ImageIcon(getClass().getResource("/utilidades/tabla por nivel.PNG"));
                                JOptionPane.showMessageDialog(null,AYUDA,"Explicación del juego", JOptionPane.PLAIN_MESSAGE, imageExplicacion);
                            }else{
                                if (e.getSource() == minimizar){
                                    // Minimiza el Jframe
                                    setExtendedState(JFrame.CROSSHAIR_CURSOR);
                                }else{
                                    if(e.getSource() == salir){
                                        // Cierra el JFrame
                                        System.exit(0);
                                    }else{
                                        if(e.getSource() == botonSi && listaAMemorizar.contains(palabraAleatoria.getText())){
                                            aciertos++;
                                        }else{
                                            if(e.getSource() == botonNo && listaAMemorizar.contains(palabraAleatoria.getText()) == false){
                                                aciertos++;
                                            }
                                        }
                                        activarDesactivarBotonesSecundarios("desactivar");
                                        revalidate();
                                        repaint();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}