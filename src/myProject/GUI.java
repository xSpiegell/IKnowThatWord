package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
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
    private JLabel palabraAleatoria, mostrarNivel, mensaje;
    private ImageIcon imageExplicacion;
    private Timer timer;
    private FileManager fileManager;
    private int nivelActual;
    private int seleccionarPalabra;
    private int aciertos;
    private double porcentajeAciertos;
    private ArrayList<Integer> delaysTimer;

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
        constraints.gridx = 5;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.CENTER;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(empezar,constraints);

        // Creación botón registro
        registro = new JButton(" Registrate ");
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

        constraints.gridx = 5;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.LINE_START;
        add(alias,constraints);

        subPanel = new JPanel();
        botonSi = new JButton("Si");
        botonNo = new JButton("No");

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
        juego.setPreferredSize(new Dimension(300,350));
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
        informacion.setBorder(BorderFactory.createTitledBorder("Información"));
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
        private EscuchaBotonesSecundarios escuchaBotonesSecundarios;
        private int palabra;
        private int aleatorio;
        private int respuesta;

        private class EscuchaBotonesSecundarios implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == botonSi && listaAMemorizar.contains(palabraAleatoria.getText())){
                    aciertos++;
                }else{
                    if(e.getSource() == botonNo && listaAMemorizar.contains(palabraAleatoria.getText()) == false){
                        aciertos++;
                    }
                }
                botonSi.setEnabled(false);
                botonNo.setEnabled(false);
                System.out.print(aciertos);
            }
        }

        public Escucha(){
            palabrasAMemorizar = new ArrayList<>();
            numerosAleatorios = new ArrayList<>();
            numerosAleatoriosExtra = new ArrayList<>();
            listaAMemorizar = new ArrayList<>();
            random = new Random();
            escuchaBotonesSecundarios = new EscuchaBotonesSecundarios();
            palabra = 0;
            aleatorio = 0;
            respuesta = 0; // 0 si selecciono YES, de lo contrario 1
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
                mensaje.setText("¡Pasaste al siguiente nivel!, obtuviste " + String.valueOf(porcentajeAciertos) + "% en aciertos");
            }else{
                mensaje.setText("¡Perdiste!, obtuviste " + String.valueOf(porcentajeAciertos) + "% en aciertos");
            }
            informacion.add(mensaje, BorderLayout.CENTER);
            revalidate();
            repaint();
            return aprobacion;
        }

        public void setPalabrasAleatorias(){
            aleatorio = random.nextInt(palabrasAMemorizar.size());
            if(numerosAleatorios.size() == 0){
                numerosAleatorios.add(aleatorio);
                listaAMemorizar.add(palabrasAMemorizar.get(aleatorio));
                palabraAleatoria.setText(palabrasAMemorizar.get(aleatorio));
                juego.add(palabraAleatoria, BorderLayout.CENTER);
            }else{
                if(!numerosAleatorios.contains(aleatorio)){
                    numerosAleatorios.add(aleatorio);
                    listaAMemorizar.add(palabrasAMemorizar.get(aleatorio));
                    palabraAleatoria.setText(palabrasAMemorizar.get(aleatorio));
                    juego.add(palabraAleatoria, BorderLayout.CENTER);
                }else{
                    setPalabrasAleatorias();
                }
            }
            revalidate();
            repaint();

            System.out.println(numerosAleatorios.toString());
        }

        public void escogerPalabra() {
            aleatorio = random.nextInt(palabrasAMemorizar.size());

            if(numerosAleatoriosExtra.size() == 0){
                numerosAleatoriosExtra.add(aleatorio);
                palabraAleatoria.setText(palabrasAMemorizar.get(aleatorio));
                juego.add(palabraAleatoria, BorderLayout.CENTER);
            }else{
                if(!numerosAleatoriosExtra.contains(aleatorio)){
                    numerosAleatoriosExtra.add(aleatorio);
                    palabraAleatoria.setText(palabrasAMemorizar.get(aleatorio));
                    juego.add(palabraAleatoria, BorderLayout.CENTER);
                }else{
                    escogerPalabra();
                }
            }
            revalidate();
            repaint();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(nivelActual){
                case 1: palabrasAMemorizar = fileManager.lecturaFile(20);
                        porcentajeAciertos = (aciertos/20.0) * 100.0;
                        break;
                case 2: palabrasAMemorizar = fileManager.lecturaFile(40);
                        porcentajeAciertos = (aciertos/40.0) * 100.0;
                        break;
                case 3: palabrasAMemorizar = fileManager.lecturaFile(50);
                        porcentajeAciertos = (aciertos/50.0) * 100.0;
                        break;
                case 4: palabrasAMemorizar = fileManager.lecturaFile(60);
                        porcentajeAciertos = (aciertos/60.0) * 100.0;
                        break;
                case 5: palabrasAMemorizar = fileManager.lecturaFile(70);
                        porcentajeAciertos = (aciertos/70.0) * 100.0;
                        break;
                case 6: palabrasAMemorizar = fileManager.lecturaFile(80);
                        porcentajeAciertos = (aciertos/80.0) * 100.0;
                        break;
                case 7: palabrasAMemorizar = fileManager.lecturaFile(100);
                        porcentajeAciertos = (aciertos/100.0) * 100.0;
                        break;
                case 8: palabrasAMemorizar = fileManager.lecturaFile(120);
                        porcentajeAciertos = (aciertos/120.0) * 100.0;
                        break;
                case 9: palabrasAMemorizar = fileManager.lecturaFile(140);
                        porcentajeAciertos = (aciertos/140.0) * 100.0;
                        break;
                case 10: palabrasAMemorizar = fileManager.lecturaFile(200);
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
                    timer.setInitialDelay(delaysTimer.get(1));
                    timer.setDelay(delaysTimer.get(1));
                    timer.restart();
                    timer.start();
                    escogerPalabra();
                }

                mostrarNivel.setText(String.valueOf(nivelActual));
                nivel.add(mostrarNivel, BorderLayout.CENTER);
                empezar.setEnabled(false);
                empezar.removeActionListener(escucha);
                revalidate();
                repaint();
            }else{
                if(e.getSource() == timer){
                    botonSi.setEnabled(true);
                    botonNo.setEnabled(true);
                    empezar.setEnabled(false);
                    palabra++;
                    if(seleccionarPalabra == 0){
                        if(palabra < palabrasAMemorizar.size() / 2){
                            setPalabrasAleatorias();
                        }else{
                            timer.stop();
                            palabra = 0;
                            seleccionarPalabra = 1;
                            empezar.setEnabled(true);
                            empezar.addActionListener(escucha);

                            // Inicializa los botones secundarios
                            botonSi.addActionListener(escuchaBotonesSecundarios);
                            botonNo.addActionListener(escuchaBotonesSecundarios);
                            subPanel.add(botonSi);
                            subPanel.add(botonNo);
                            juego.add(subPanel, BorderLayout.SOUTH);
                        }
                    }else{
                        botonSi.setEnabled(true);
                        botonSi.setEnabled(true);
                        if(palabra < palabrasAMemorizar.size()){
                            escogerPalabra();
                        }else{
                            timer.stop();
                            timer.setInitialDelay(delaysTimer.get(0));
                            timer.setDelay(delaysTimer.get(0));
                            timer.restart();
                            palabra = 0;
                            seleccionarPalabra = 0;
                            empezar.addActionListener(escucha);

                            // Deshabilita los botones secundarios
                            botonSi.removeActionListener(escuchaBotonesSecundarios);
                            botonNo.removeActionListener(escuchaBotonesSecundarios);
                            botonSi.setEnabled(false);
                            botonNo.setEnabled(false);

                            if(siguienteNivel(nivelActual)){
                                empezar.setEnabled(true);
                            }else{
                                empezar.setEnabled(false);
                            }
                        }
                    }
                }else{
                    if(e.getSource() == registro){

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
                                    // Sirve para minimizar el Jframe
                                    setExtendedState(JFrame.CROSSHAIR_CURSOR);
                                }else{
                                    // Sirve para cerrar el Jframe
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
