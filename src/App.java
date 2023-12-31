import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;
import java.util.List;

public class App{
    private ArrayList<Candidato> candidatos = new ArrayList<>();
    private JFrame frame;
    private String[] ciudades = {"Cali", "Buga", "Palmira", "Tulua", "Cartago", "Bugalagrande", "Buenaventura", "Yumbo"};
    private String[] partidos = {"Partido Liberal", "Partido Conservador", "Liga", "AICO", "Partido Verde", "Union Patriótica", "Centro Democratico", "Partido de la U", "Cambio Radical"};
    private JMenuBar barra;
    private JMenu creacion, visualizar, votos, salir;
    private JMenuItem crear, modificar, eliminar, verNombre, verTodos, sali, canVotos, resulVotos, conFinal;

    public App() {
        frame = new JFrame("Sistema de Candidatos y Votos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 250);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        
        barra = new JMenuBar();
        creacion = new JMenu("CREACION");
        creacion.setFont(new Font("Times New Roman", Font.BOLD, 20));
        visualizar = new JMenu("VISUALIZAR");
        visualizar.setFont(new Font("Times New Roman", Font.BOLD, 20));
        votos = new JMenu("VOTOS");
        votos.setFont(new Font("Times New Roman", Font.BOLD, 20));
        salir = new JMenu("SALIR");
        salir.setFont(new Font("Times New Roman", Font.BOLD, 20));

        crear = new JMenuItem("Crear candidato");
        crear.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        modificar = new JMenuItem("Modificar candidato");
        modificar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        eliminar = new JMenuItem("Eliminar candidato");
        eliminar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        verNombre = new JMenuItem("Buscar candidato");
        verNombre.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        verTodos = new JMenuItem("Todos los candidatos");
        verTodos.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        sali = new JMenuItem("Salir");
        sali.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        canVotos = new JMenuItem("Cantidad votos");
        canVotos.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        resulVotos = new JMenuItem("Resultado votos");
        resulVotos.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        conFinal = new JMenuItem("Conteo Final");
        conFinal.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        creacion.add(crear);
        creacion.add(modificar);
        creacion.add(eliminar);
        visualizar.add(verNombre);
        visualizar.add(verTodos);
        votos.add(canVotos);
        votos.add(resulVotos);
        votos.add(conFinal);
        salir.add(sali);

        barra.add(creacion);
        barra.add(visualizar);
        barra.add(votos);
        barra.add(salir);

        panel.add(barra);
        frame.add(panel);
        frame.setVisible(true);

        crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaInsertar();
            }
        });

        modificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaActualizar();
            }
        });

        eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaEliminar();
            }
        });

        verNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaBuscar();
            }
        });

        verTodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaListar();
            }
        });

        canVotos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (candidatos.isEmpty()) {
                    mostrarMensaje("No hay candidatos ingresados.");
                } else {
                    mostrarVentanaVotos();
                }
            }
        });

        resulVotos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (candidatos.isEmpty()) {
                    mostrarMensaje("No hay candidatos ingresados.");
                } else {
                    mostrarVentanaResultados();
                }
            }
        });

        conFinal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (candidatos.isEmpty()) {
                    mostrarMensaje("No hay candidatos ingresados.");
                } else {
                    mostrarVentanaGanador();
                }
            }
        });

        sali.addActionListener(new ActionListener() {
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
            String cedulaStr = JOptionPane.showInputDialog("Ingrese el numero de cedula:");
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
                    int opcion = JOptionPane.showConfirmDialog(null, "La cedula ya esta registrada. ¿Desea ingresar una cedula diferente?", "Cedula Repetida", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        String ciudad = (String) JOptionPane.showInputDialog(null, "Elija la ciudad:",
                "Ciudad", JOptionPane.QUESTION_MESSAGE, null, ciudades, ciudades[0]);

        String partido = (String) JOptionPane.showInputDialog(null, "Elija el partido politico:",
                "Partido Politico", JOptionPane.QUESTION_MESSAGE, null, partidos, partidos[0]);

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

    private void mostrarVentanaEliminar() {
        if (candidatos.isEmpty()) {
            mostrarMensaje("No hay candidatos ingresados.");
            return;
        }

        int cedulaEliminar;
        while (true) {
            String cedulaStr = JOptionPane.showInputDialog("Ingrese la cedula del candidato a eliminar:");
            if (cedulaStr == null) {
                return;
            }
            try {
                cedulaEliminar = Integer.parseInt(cedulaStr);
                break;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Candidato candidatoAEliminar = null;

        for (Candidato candidato : candidatos) {
            if (candidato.getCedula() == cedulaEliminar) {
                candidatoAEliminar = candidato;
                break;
            }
        }

        if (candidatoAEliminar != null) {
            candidatos.remove(candidatoAEliminar);
            mostrarMensaje("Candidato eliminado exitosamente.");
        } else {
            mostrarMensaje("Candidato no encontrado.");
        }
    }

    private void mostrarVentanaBuscar() {
        if (candidatos.isEmpty()) {
            mostrarMensaje("No hay candidatos ingresados.");
            return;
        }

        int cedulaBuscar;
        while (true) {
            String cedulaStr = JOptionPane.showInputDialog("Ingrese la cedula del candidato a buscar:");
            if (cedulaStr == null) {
                return;
            }
            try {
                cedulaBuscar = Integer.parseInt(cedulaStr);
                break;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un número de cedula valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Candidato candidatoABuscar = null;

        for (Candidato candidato : candidatos) {
            if (candidato.getCedula() == cedulaBuscar) {
                candidatoABuscar = candidato;
                break;
            }
        }

        if (candidatoABuscar != null) {
            mostrarMensaje("Candidato encontrado:\nNombre: " + candidatoABuscar.getNombre() + "\nCedula: " + candidatoABuscar.getCedula() + "\nCiudad: " + candidatoABuscar.getCiudad() + "\nPartido Político: " + candidatoABuscar.getPartido());
        } else {
            mostrarMensaje("Candidato no encontrado.");
        }
    }

    private void mostrarVentanaListar() {
        if (candidatos.isEmpty()) {
            mostrarMensaje("No hay candidatos ingresados.");
        } else {
            StringBuilder listaCandidatos = new StringBuilder("Lista de todos los candidatos:\n");

            for (Candidato candidato : candidatos) {
                listaCandidatos.append("Nombre: ").append(candidato.getNombre()).append("\n");
                listaCandidatos.append("Cedula: ").append(candidato.getCedula()).append("\n");
                listaCandidatos.append("Ciudad: ").append(candidato.getCiudad()).append("\n");
                listaCandidatos.append("Partido Politico: ").append(candidato.getPartido()).append("\n");
                listaCandidatos.append("--------------------\n");
            }

            JTextArea textArea = new JTextArea(listaCandidatos.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(null, scrollPane);
        }
    }

    private void mostrarVentanaVotos() {
        for (Candidato candidato : candidatos) {
            String votosStr = JOptionPane.showInputDialog("Ingrese la cantidad de votos para " + candidato.getNombre() + ":");
            if (votosStr == null) {
                return;
            }

            try {
                int votosIngresar = Integer.parseInt(votosStr);
                candidato.setVotos(votosIngresar);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de votos valido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        mostrarMensaje("Votos ingresados exitosamente.");
        mostrarVentanaResultados();
    }

    private void mostrarVentanaResultados() {
        candidatos.sort((c1, c2) -> Integer.compare(c2.getVotos(), c1.getVotos()));
        StringBuilder resultados = new StringBuilder("Resultados de las Elecciones (de mayor a menor número de votos):\n");
    
        for (Candidato candidato : candidatos) {
            resultados.append("Nombre: ").append(candidato.getNombre()).append("\n");
            resultados.append("Cédula: ").append(candidato.getCedula()).append("\n");
            resultados.append("Partido Político: ").append(candidato.getPartido()).append("\n");
            resultados.append("Votos: ").append(candidato.getVotos()).append("\n\n");
        }
    
        JTextArea textArea = new JTextArea(resultados.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
    
        JFrame resultadosFrame = new JFrame("Resultados de las Elecciones");
        resultadosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultadosFrame.getContentPane().add(scrollPane);
        resultadosFrame.pack();
        resultadosFrame.setVisible(true);
    
        mostrarVentanaGanador();
        mostrarCiudadesConMenosCandidatos();
    }
    
    private void mostrarVentanaGanador() {
        Candidato ganador = candidatos.get(0);
    
        StringBuilder mensajeGanador = new StringBuilder("Ganador de las Elecciones:\n");
        mensajeGanador.append("Nombre: ").append(ganador.getNombre()).append("\n");
        mensajeGanador.append("Cédula: ").append(ganador.getCedula()).append("\n");
        mensajeGanador.append("Partido Político: ").append(ganador.getPartido()).append("\n");
    
        JOptionPane.showMessageDialog(null, mensajeGanador.toString(), "Ganador de las Elecciones", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showOptionDialog(null, mensaje, "Mensaje", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
    }
    

    
    private void mostrarCiudadesConMenosCandidatos() {
    Map<String, Integer> ciudadCandidatosCount = new HashMap<>();
    
    for (Candidato candidato : candidatos) {
        String ciudad = candidato.getCiudad();
        ciudadCandidatosCount.put(ciudad, ciudadCandidatosCount.getOrDefault(ciudad, 0) + 1);
    }
    
    List<Map.Entry<String, Integer>> sortedCiudades = ciudadCandidatosCount.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toList());
    
    int numCiudadesAMostrar = Math.min(3, sortedCiudades.size());
    
    StringBuilder ciudadesConMenosCandidatos = new StringBuilder("Ciudades con menos candidatos inscritos:\n");
    
    for (int i = 0; i < numCiudadesAMostrar; i++) {
        String ciudad = sortedCiudades.get(i).getKey();
        int candidatosCount = sortedCiudades.get(i).getValue();
        ciudadesConMenosCandidatos.append(ciudad).append(": ").append(candidatosCount).append(" candidatos\n");
    }
    
    JOptionPane.showMessageDialog(null, ciudadesConMenosCandidatos.toString());
}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }
};