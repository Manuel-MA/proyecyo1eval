package mysql;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vesprada
 */
public class mysql {

    boolean conectado = false;
    protected static Connection con = null;
    static Statement  stmt = null;

    public Connection Conexion() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlOdbc = "jdbc:mysql://localhost:3307/manuel";
            con = (java.sql.DriverManager.getConnection(urlOdbc, "root", "caor89"));
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se ha podido establecer la conexion" + e.getMessage());
        }
    }

    public void Desconexion(Connection con) throws Exception {
        try {
            if (con != null && conectado == true) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido cerrar la conexion" + e.getMessage());
        }
    }

    public void initTrans() throws Exception {
        try {
            if (con != null && conectado == true) {
                con.setAutoCommit(false);
                stmt = con.createStatement();
            } else {
                throw new Exception("No está conectado a la base de datos.");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido iniciar la transacción: "
                    + ex.getMessage());
        }
    }

    public void haceCommit(Connection con) throws Exception {
        try {
            if (con != null && conectado == true) {
                con.commit();
            } else {
                throw new Exception("No está conectado a la base de datos.");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar commit de la "
                    + "transacción: " + ex.getMessage());
        }
    }

    public void haceRoll(Connection con) throws Exception {
        try {
            if (con != null && conectado == true) {
                con.rollback();
            } else {
                throw new Exception("No está conectado a la base de datos.");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar rollback de la "
                    + "transacción: " + ex.getMessage());
        }
    }

    public static boolean deleteOne(String id) throws Exception {
        boolean resultado = false;
        try {
            if (con != null) {
                stmt = con.createStatement();
                String consulta = "DELETE FROM DOCUMENTOS WHERE ID ='" + id + "'";
                int result = stmt.executeUpdate(consulta);
                if (result == 1) {
                    resultado = true;
                    System.out.println("Borrado");
                } else {
                    resultado = false;
                    System.out.println("No Borrado");
                }
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            throw new Exception("No va el deleteOne " + ex.getMessage());
        }
        return resultado;
    }

    public static void insertOne(String nombre, String contenido, Date fecha, int nota, int id_usu, String etiquetas, String privado) throws Exception {
        try {
            if (con != null) {
                stmt = con.createStatement();
                String consulta = "INSERT INTO DOCUMENTOS values( null, TRIM('" + nombre + "'),TRIM('"+contenido+"'),"
                        + "TRIM('"+fecha+"'),TRIM('"+nota+"'),TRIM('"+id_usu+"'),TRIM('"+etiquetas+"'),TRIM('"+privado+"'))";
                System.out.println(consulta);
                stmt = con.prepareStatement(consulta);
                stmt.executeUpdate(consulta);
                
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            throw new Exception("No va el insertOne " + ex.getMessage());
        }
    }

    public static boolean updateOne(int id, String tabla, String titulo, String contenido, Date fecha, String etiqueta) throws Exception {
        boolean actualizado = false;
        try {
            if (con != null) {
                stmt = con.createStatement();
                String consulta = "UPDATE " + tabla + " SET titulo =TRIM('" + titulo + "'), contenido=TRIM('"+contenido+"'), fecha=TRIM('"+fecha+"'), etiquetas=TRIM('"+etiqueta+"') WHERE ID='" + id + "'";
                System.out.println(consulta);
                stmt = con.prepareStatement(consulta);
                int result = stmt.executeUpdate(consulta);
                if (result == 1) {
                    actualizado = true;
                    System.out.println("Actualizada");
                } else {
                    actualizado = false;
                    System.out.println("No Actualizada");
                }
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            throw new Exception("No va el updateOne " + ex.getMessage());
        }
        return actualizado;
    }

    public int getOne(String tabla) throws Exception {
        int n = 0;
        try {
            if (con != null) {
                stmt = con.createStatement();
                String consulta = "SELECT * FROM " + tabla + " WHERE 1=1";
                ResultSet resultSet = stmt.executeQuery(consulta);
                if (resultSet.next()) {
                    n = resultSet.getInt("id");
                } else {
                    System.out.println("No obtenida");
                }
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            throw new Exception("No va el getOne " + ex.getMessage());
        }
        return n;
    }

    public HashMap getRow(String tabla, String where) throws Exception {
        HashMap row = new HashMap();
        int n = 0;
        String nom;
        try {
            if (con != null) {
                stmt = con.createStatement();
                String consulta = "SELECT * FROM " + tabla + " WHERE " + where;
                ResultSet resultSet = stmt.executeQuery(consulta);
                if (resultSet.next()) {
                    n = resultSet.getInt("id");
                    nom = resultSet.getString("nombre");
                    row.put("id", n);
                    row.put("nombre", nom);
                } else {
                    row.put(0, 0);
                }
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            throw new Exception("No va el getRow " + ex.getMessage());
        }
        return row;
    }

    public int getCount(String tabla, String campo, String valor) throws Exception {
        int n = 0;
        try {
            if (con != null) {
                stmt = con.createStatement();
                String consulta = "SELECT COUNT(ID) as veces FROM " + tabla + " WHERE " + campo + "='" + valor + "'";
                ResultSet resultSet = stmt.executeQuery(consulta);
                if (resultSet.next()) {
                    n = resultSet.getInt("veces");
                } else {
                    System.out.println("No obtenida");
                }
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            throw new Exception("No va el getCount " + ex.getMessage());
        }
        return n;
    }

    public int getId(String tabla, ArrayList where) throws Exception {
        int id = 0;
        try {
            if (con != null) {
                stmt = con.createStatement();
                String consulta = "SELECT id FROM " + tabla + " WHERE 1=1";

                if (where != null) {
                    for (int i = 0; i < where.size(); i++) {
                        consulta += " AND " + where.get(i);
                    }
                }
                System.out.println(consulta);
                ResultSet resultSet = stmt.executeQuery(consulta);
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                } else {
                    id = 0;
                    System.out.println(id);
                }
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            throw new Exception("No va el getId " + ex.getMessage());
        }
        return id;
    }

    /**
     *
     * @param tabla
     * @param condicion
     * @param campos
     * @param limite
     * @return
     * @throws Exception
     */
    public static int getPages(String tabla, List campos, List where, int limite) throws Exception {
        int result = 0;
        String orden = "";
        String cond = "";
        if (limite == 0) {
            limite = 1;
        }
        try {
            if (con != null) {
                Statement stm = con.createStatement();
                

                if (campos != null) {
                    orden += campos.get(0);
                    for (int i = 1; i < campos.size(); i++) {
                        orden += " AND " + campos.get(i);
                    }

                }else{
                    orden = "1=1";
                }
                if (where != null) {
                    cond += where.get(0);
                    for (int i = 1; i < where.size(); i++) {
                        cond += " AND " + where.get(i);
                    }
                  
                }else{
                    cond="id";
                }
                String consulta = "SELECT * FROM " + tabla + " WHERE "+cond+" ORDER BY " + orden;
                ResultSet resultSet = stm.executeQuery(consulta);
                int reg = 0;

                while (resultSet.next()) {
                    reg++;
                }
                result = reg / limite;
                if (reg % limite > 0) {
                    result = (reg / limite) + 1;
                }
            }

        } catch (SQLException ex) {
            result = 0;
            ex.getStackTrace();
            throw new Exception("No va el getPages " + ex.getMessage());
        } finally {
            return result;
        }
    }

    public static ResultSet getPage(String tabla, List where, List order, int numpag, int limite) throws Exception {
        ResultSet resultSet = null;
        int usu = numpag * limite - limite;
        String orden = "";

        String cond = "";

        if (limite == 0) {
            limite = 1;
        }
        try {
            if (con != null) {
                stmt = con.createStatement();
                if (order != null) {
                    orden += order.get(0);
                    for (int i = 1; i < order.size(); i++) {
                        orden += " AND " + order.get(i);
                    }

                }
                if (where != null) {
                    cond += where.get(0);
                    for (int i = 1; i < where.size(); i++) {
                        cond += " AND " + where.get(i);
                    }

                }
                String consulta = "SELECT * FROM " + tabla + " WHERE " + cond + " ORDER BY " + orden
                        + " LIMIT " + usu + " , " + limite;
                System.out.println(consulta);

                resultSet = stmt.executeQuery(consulta);
//                while (resultSet.next()) {
//                    pages.add(resultSet.getString("id"));
//                }
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            throw new Exception("No va el getPage " + ex.getMessage());
        } finally {
            return resultSet;
        }

    }

    public int execute(String sql) throws Exception {
        int numFilas = 0;
        try {
            if (con != null) {
                String primer = sql.substring(0, 1);
                stmt = con.createStatement();
                switch (primer) {
                    case "U":
                    case "u":
                        stmt = con.prepareStatement(sql);
                        System.out.println(sql);
                        numFilas = stmt.executeUpdate(sql);
                        break;
                    case "I":
                    case "i":
                        stmt = con.prepareStatement(sql);
                        numFilas = stmt.executeUpdate(sql);
                        break;
                    case "D":
                    case "d":
                        numFilas = stmt.executeUpdate(sql);
                }
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
            throw new Exception("No va el Execute " + ex.getMessage());
        }
        return numFilas;
    }

    public ResultSet Get(String sql) throws Exception {
        ResultSet resultset = null;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            resultset = stmt.executeQuery();
            return resultset;
        } catch (SQLException e) {
            e.getStackTrace();
            throw new Exception("No va el Get " + e.getMessage());
        }

    }
}
