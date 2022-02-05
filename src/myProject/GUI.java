package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {
    public  static final String CREDITOS = "CRÉDITOS\n" +
            "-> BRAYAN STIVEN SANCHEZ LEON\n" +
            "-> MAYRA ALEJANDRA SANCHEZ SALINAS";
    public  static final String AYUDA = "";
    private Header headerProject;
    private Escucha escucha;
    private JButton registro,ayuda,salir, creditos, empezar,minimizar;
    private JPanel alias, nivel, informacion, juego;

    /**
     * Constructor de la clase GUI
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
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
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(creditos,constraints);

        // Creación botón empezar
        empezar = new JButton(" Iniciar ");
        empezar.addActionListener(escucha);
        empezar.setBackground(new Color(132, 250, 101,115));
        constraints.gridx = 5;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.CENTER;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(empezar,constraints);

        // Creación botón registro
        registro = new JButton(" Registrate ");
        registro.addActionListener(escucha);
        registro.setBackground(new Color(101, 121, 250,115));
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

        // Panel nivel
        nivel = new JPanel();
        nivel.setPreferredSize(new Dimension(200,50));
        nivel.setBorder(BorderFactory.createTitledBorder("Nivel"));
        nivel.setBackground(new Color(255, 210, 142,152));

        constraints.gridx = 5;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.LINE_START;
        add(nivel,constraints);

        // Panel juego
        juego = new JPanel();
        juego.setPreferredSize(new Dimension(300,350));
        juego.setBorder(BorderFactory.createTitledBorder("Presta atención a la palabras"));
        juego.setBackground(Color.white);

        constraints.gridx = 5;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.LINE_START;
        add(juego,constraints);

        // Panel aciertos, errores y resultado
        informacion = new JPanel();
        informacion.setPreferredSize(new Dimension(200,100));
        informacion.setBorder(BorderFactory.createTitledBorder("Información"));
        informacion.setBackground(new Color(81, 221, 241,152));

        constraints.gridx = 5;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.LINE_START;
        add(informacion,constraints);
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

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == empezar){


            }else{
                if(e.getSource() == registro){

                }else{
                    if (e.getSource() == creditos){
                        // Al presionar el boton CREDITOS, salen los nombres de los programadores que estan en la variable estatica CREDITOS
                        JOptionPane.showMessageDialog(null,CREDITOS);
                    }else{
                        if (e.getSource() == ayuda){
                            // Al presionar el boton ?, salen las inidicaciones que estan en la variable estatica AYUDA
                            JOptionPane.showMessageDialog(null,AYUDA);
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
