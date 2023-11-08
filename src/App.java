import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class App {
    private ArrayList<Candidato> candidatos = new ArrayList<>();
    private JFrame frame;
    private JButton insertarButton, actualizarButton, eliminarButton, buscarButton, listarButton, votosButton, salirButton;
    
    public App() {
        frame = new JFrame("Sistema de Candidatos y Votos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        insertarButton = new JButton("1. Insertar Candidato");
        actualizarButton = new JButton("2. Actualizar Candidato");
        eliminarButton = new JButton("3. Eliminar Candidato");
        buscarButton = new JButton("4. Buscar Candidato por cédula");
        listarButton = new JButton("5. Listar todos los Candidatos");
        votosButton = new JButton("6. Solicitar Votos");
        salirButton = new JButton("7. Salir");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));
        panel.add(insertarButton);
        panel.add(actualizarButton);
        panel.add(eliminarButton);
        panel.add(buscarButton);
        panel.add(listarButton);
        panel.add(votosButton);
        panel.add(salirButton);

        frame.add(panel);
        frame.setVisible(true);

        insertarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //mostrarVentanaInsertar();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //mostrarVentanaActualizar();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //mostrarVentanaEliminar();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //mostrarVentanaBuscar();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //mostrarVentanaListar();
            }
        });

        votosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (candidatos.isEmpty()) {
                    //mostrarMensaje("No hay candidatos ingresados.");
                } else {
                    //mostrarVentanaVotos();
                }
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    // Realizar la logica de las funciones.


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }
}

