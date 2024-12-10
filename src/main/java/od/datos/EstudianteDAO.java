package od.datos;

import od.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static od.conexion.Conexion.getConexion;
//DAO - Data Access Object
public class EstudianteDAO {

    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("ocurrio un error al cerrar conexion: " + e.getMessage());
            }
        }
        return estudiantes;
    }
    //findById
    public boolean buscarEstudiantePorId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if (rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error al buscar estudiante: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }
    public static void main(String[] args) {
        var estudianteDao = new EstudianteDAO();
        //Listar los estudiantes
        System.out.println("Listado estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);
        //Buscar por ID
        var estudiante1 = new Estudiante(3);
        System.out.println("Estudiante antes de la busqueda " + estudiante1);
        var encontrado = estudianteDao.buscarEstudiantePorId(estudiante1);
        if (encontrado)
            System.out.println("Estudiante econtrado: " + estudiante1);
        else {
            System.out.println("No se encontro estudiante: " + estudiante1.getIdEstudiante());
        }

    }


}
