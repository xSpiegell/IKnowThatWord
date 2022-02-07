package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Esta es la clase principal
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version v.1.0.0 date:4/02/2022
 */
public class GUI extends JFrame {
    public  static final String CREDITOS="CRÉDITOS\n" +
            "-> BRAYAN STIVEN SANCHEZ LEON\n" +
            "-> MAYRA ALEJANDRA SANCHEZ SALINAS";
    public  static final String AYUDA="Este  ejercicio  entrena  la  memoria  episódica  verbal  reciente.  Es  importante  en  tareas  donde  es  útil \nreconocer  qué información  hemos  oído  antes,  como  en conversaciones,  películas  o  ir  a  la  compra.\n"+"\n"+
            "El  juego  consiste en  presentar  al  jugador  una  secuencia  de palabras  de una  en  una,  es  decir,  aparece  una\npalabra,  dura  5  segundos  en pantalla,  luego  se  borra  y  aparece  la  siguiente.   El  jugador  deberá  memorizar\nlas palabras  que van  apareciendo.\n"+"\n"+
            "Tras la serie  de  palabras  a memorizar,  el  juego  presentará un  listado  con  el  doble  de  palabras que  se\nmostraron. Por  cada  una  las  palabras  el  jugador  deberá  indicar  si  la  palabra  estaba  o  no  contenida  en  el\nlistado  a  memorizar  y  tendrá  un  tiempo  de  7  segundos  para  responder,  en  caso  de no  hacerlo  se tomará\ncomo un  error.";

    private Header headerProject;
    private Escucha escucha;
    private JButton registro,ayuda,salir, creditos, empezar,minimizar;
    private JPanel alias, nivel, informacion, juego;
    private ImageIcon imageExplicacion;
    private String nombreUsuario;
    private JTextField linea;
    private FileManager fileManager;
    private ArrayList<String> usuario = new ArrayList<String>();

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
        constraints.fill = GridBagConstraints.BOTH;
        this.add(headerProject,constraints);

        // Creación botón salir
        salir = new JButton("x");
        salir.addActionListener(escucha);
        salir.setBackground(Color.red);
        salir.setFocusable(false);
        constraints.gridx = 11;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(salir,constraints);

        // Creación botón ayuda
        ayuda = new JButton(" ? ");
        ayuda.addActionListener(escucha);
        ayuda.setBackground(new Color(82, 140, 255, 255));
        ayuda.setFocusable(false);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        // Creación botón minimizar
        minimizar = new JButton("-");
        minimizar.addActionListener(escucha);
        minimizar.setBackground(Color.cyan);
        minimizar.setFocusable(false);
        constraints.gridx = 10;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LAST_LINE_END;
        this.add(minimizar,constraints);

        // Creación botón créditos
        creditos = new JButton(" Créditos ");
        creditos.addActionListener(escucha);
        creditos.setBackground((new Color(194, 161, 108, 255)));
        creditos.setFocusable(false);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(creditos,constraints);

        // Creación botón registro
        registro = new JButton(" Registrarse / Iniciar sesión  ");
        registro.addActionListener(escucha);
        registro.setBackground(Color.ORANGE);
        registro.setFocusable(false);
        constraints.gridx = 5;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(registro,constraints);

        // Panel nombre
        alias = new JPanel();
        alias.setPreferredSize(new Dimension(200,50));
        alias.setBorder(BorderFactory.createTitledBorder("Alias"));
        alias.setBackground(new Color(178, 161, 255,152));

        constraints.gridx = 5;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(alias,constraints);

        // Panel nivel
        nivel = new JPanel();
        nivel.setPreferredSize(new Dimension(200,50));
        nivel.setBorder(BorderFactory.createTitledBorder("Nivel"));
        nivel.setBackground(new Color(255, 210, 142,152));

        constraints.gridx = 5;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(nivel,constraints);

        // Panel juego
        juego = new JPanel();
        juego.setPreferredSize(new Dimension(300,250));
        juego.setBorder(BorderFactory.createTitledBorder("Presta atención a la palabras"));
        juego.setBackground(Color.white);

        constraints.gridx = 5;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(juego,constraints);

        // Creación botón empezar
        empezar = new JButton(" Iniciar ");
        empezar.addActionListener(escucha);
        empezar.setBackground(Color.yellow);
        empezar.setFocusable(false);
        empezar.setEnabled(false);

        constraints.gridx = 5;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(empezar,constraints);

        // Panel aciertos, errores y resultado
        informacion = new JPanel();
        informacion.setPreferredSize(new Dimension(200,100));
        informacion.setBorder(BorderFactory.createTitledBorder("Información de la partida"));
        informacion.setBackground(new Color(81, 221, 241,152));

        constraints.gridx = 5;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
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
                    // Si existe usuario entonces deshabilita botón registro y habilita el botón de inicio, si no existe el usuario entonces lo agrega al archivo txt
                    nombreUsuario = JOptionPane.showInputDialog(null,"Ingrese su nombre o su alias","Registro",1);
                    usuario = fileManager.lecturaFileUsuarios();
                    if (usuario.contains(nombreUsuario)){
                        empezar.setEnabled(true);
                        registro.setEnabled(false);
                    }else{
                        linea = new JTextField();
                        linea.setText(nombreUsuario);
                        fileManager.escribirFile(linea.getText());
                    }

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
                                // Sirve para minimizar el JFrame
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
