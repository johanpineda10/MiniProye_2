import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class App{
    private ArrayList<Candidato> candidatos = new ArrayList<>();
    private JFrame frame;
    private JButton insertarButton, actualizarButton, eliminarButton, buscarButton, listarButton, votosButton, salirButton;
    private String[] ciudades = {"Cali", "Buga", "Palmira", "Tulua", "Cartago", "Bugalagrande", "Buenaventura", "Yumbo"};
    private String[] partidos = {"Partido Liberal", "Partido Conservador", "Liga", "AICO", "Partido Verde", "Unión Patriótica", "Centro Democrático", "Partido de la U", "Cambio Radical"};

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
                mostrarVentanaInsertar();


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
                    mostrarMensaje("No hay candidatos ingresados.");
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

    private void mostrarVentanaInsertar() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del candidato:");
        if (nombre == null) {
            return;
        }

        int cedula;
        while (true) {
            String cedulaStr = JOptionPane.showInputDialog("Ingrese el número de cédula:");
            if (cedulaStr == null) {
                return;
            }
            try {
                cedula = Integer.parseInt(cedulaStr);
                boolean cedulaRepetida = false;
                for (Candidato candidato : candidatos) {
                    if (candidato.getCedula() == cedula) {
                        cedulaRepetida = true;
                        break;
                    }
                }
                if (!cedulaRepetida) {
                    break;
                } else {
                    int opcion = JOptionPane.showConfirmDialog(null, "La cédula ya está registrada. ¿Desea ingresar una cédula diferente?", "Cédula Repetida", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un número de cédula válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        String ciudad = (String) JOptionPane.showInputDialog(null, "Elija la ciudad:",
                "Ciudad", JOptionPane.QUESTION_MESSAGE, null, ciudades, ciudades[0]);

        String partido = (String) JOptionPane.showInputDialog(null, "Elija el partido político:",
                "Partido Político", JOptionPane.QUESTION_MESSAGE, null, partidos, partidos[0]);

        candidatos.add(new Candidato(nombre, cedula, ciudad, partido));
        mostrarMensaje("Candidato ingresado exitosamente.");
    }
    
    private void mostrarVentanaActualizar() {
        if (candidatos.isEmpty()) {
            mostrarMensaje("No hay candidatos ingresados.");
            return;
        }

        int cedulaActualizar;
        while (true) {
            String cedulaStr = JOptionPane.showInputDialog("Ingrese la cedula del candidato a actualizar:");
            if (cedulaStr == null) {
                return;
            }
            try {
                cedulaActualizar = Integer.parseInt(cedulaStr);
                break;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Candidato candidatoAActualizar = null;

        for (Candidato candidato : candidatos) {
            if (candidato.getCedula() == cedulaActualizar) {
                candidatoAActualizar = candidato;
                break;
            }
        }

        if (candidatoAActualizar != null) {
            String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
            if (nuevoNombre == null) {
                return;
            }

            String nuevaCiudad = (String) JOptionPane.showInputDialog(null, "Elija la nueva ciudad:",
                    "Ciudad", JOptionPane.QUESTION_MESSAGE, null, ciudades, candidatoAActualizar.getCiudad());
            if (nuevaCiudad == null) {
                return;
            }

            String nuevoPartido = (String) JOptionPane.showInputDialog(null, "Elija el nuevo partido politico:",
                    "Partido Politico", JOptionPane.QUESTION_MESSAGE, null, partidos, candidatoAActualizar.getPartido());
            if (nuevoPartido == null) {
                return;
            }

            candidatoAActualizar.setNombre(nuevoNombre);
            candidatoAActualizar.setCiudad(nuevaCiudad);
            candidatoAActualizar.setPartido(nuevoPartido);
            mostrarMensaje("Candidato actualizado exitosamente.");
        } else {
            mostrarMensaje("Candidato no encontrado.");
        }
    }

    // Seguir agregando la logica de las demas funcionalidades. 

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showOptionDialog(null, mensaje, "Mensaje", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }

};