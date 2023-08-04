import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class registroPersonass {
    private JComboBox txtsigno;
    private JTextField txtnacimiento;
    private JTextField txtnombre;
    private JTextField txtcedula;
    private JTextField txtcodigo;
    private JButton botonbuscarC;
    private JButton BotonbuscarN;
    private JButton botonbuscarS;
    private JButton botonactualizar;
    private JButton botoningresar;
    private JButton botonlimpiar;
    private JButton botonborrar;
    private JPanel jpanel;
    private JTextField botonborrarr;
    private JTextField txtact;
    static final String DB_URL = "jdbc:mysql://localhost/registro";
    static final String USER = "root";
    static final String PASS = "root_bas3";

    public registroPersonass() {
        botoningresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int contador;
                int cont = 0;
                cont++;
                contador = cont;
                String codigo, cedula, nombre, fecha_naciminto, signo_zodiacal;
                codigo = txtcodigo.getText();
                cedula = txtcedula.getText();
                nombre = txtnombre.getText();
                fecha_naciminto = txtnacimiento.getText();
                signo_zodiacal = (String) txtsigno.getSelectedItem();
                System.out.println(codigo);
                System.out.println(cedula);
                System.out.println(nombre);
                System.out.println(fecha_naciminto);
                System.out.println(signo_zodiacal);
                String QUERY = "INSERT INTO R_personal(codigo,cedula,nombre,fecha_nacimiento,signo_zodiacal)" + "VALUES(?,?,?,?,?)";
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                    PreparedStatement statement = conn.prepareStatement(QUERY);
                    //Establecer valores para los parametros de la sentenciaSQL
                    statement.setInt(1, Integer.parseInt(codigo));
                    statement.setInt(2, Integer.parseInt(cedula));
                    statement.setString(3, nombre);
                    statement.setString(4, fecha_naciminto);
                    statement.setString(5, signo_zodiacal);
                    //EJECUTAR sentencia SQL row inserted en qe columna se van a insertar los datos
                    int rowInserted = statement.executeUpdate();
                    if (rowInserted > 0) {
                        System.out.println("Se genero correctamente la informacion");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        botonborrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int borrar;
                borrar = Integer.parseInt(botonborrarr.getText());
                String BORRAR_QUERY = "DELETE FROM R_personal where codigo=?";
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                    PreparedStatement statement = conn.prepareStatement(BORRAR_QUERY);
                    statement.setInt(1, borrar);
                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Se elimino la fila");
                    } else {
                        System.out.println("No se pudo eliminar la fila");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
     botonlimpiar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        String vacio= "", vacio1=" seleccionar";
        txtcodigo.setText(vacio);
        txtcedula.setText(vacio);
        txtnombre.setText(vacio);
        txtnacimiento.setText(vacio);
        txtsigno.setSelectedItem(vacio1);
        }
    });
        botonactualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int actualizar;
                actualizar = Integer.parseInt(botonactualizar.getText());
                String act_QUERY = "UPDATE FROM R_personal set codigo=?,cedula=?,nombre=?,fecha_nacimiento=?,signo_zodiacal=?";
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                    PreparedStatement statement = conn.prepareStatement(act_QUERY);
                    statement.setInt(1, actualizar);
                    statement.setInt(2,actualizar);
                    statement.setString(3, String.valueOf(actualizar));
                    statement.setString(4, String.valueOf(actualizar));
                    statement.setString(5, String.valueOf(actualizar));
                    int rowsupdate = statement.executeUpdate();
                    if (rowsupdate > 0) {
                        System.out.println("Se ha actualizado el formulario");
                    } else {
                        System.out.println("No se pudo actualizar");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);

                }
            }
            });
        }
    public static void main (String[]args){
            JFrame frame = new JFrame("Registro");
            frame.setContentPane(new registroPersonass().jpanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
}