package od.presentacion;

import od.datos.EstudianteDAO;
import od.dominio.Estudiante;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemaEstudiantesApp {
    public static void main(String[] args) {
       var salir = false;
       var consola = new Scanner(System.in);
       //Se crea una instancia clase servicio
        var estudianteDao =  new EstudianteDAO();
        while (!salir){
            try {
            mostrarMenu();
            salir = ejecutarOpciones(consola, estudianteDao);
            } catch (Exception e) {
                System.out.println("Ocurrio un error al ejecutar operacion: " + e.getMessage());
            }
            System.out.println();
        }//Fin de while

    }
    private static void mostrarMenu(){
        System.out.print("""
                *** Sistema de Estudiantes ***
                1. Listar Estudiantes.
                2. Buscar Estudiante.
                3. Agregar Estudiante.
                4. Modificar Estudiante.
                5. Eliminar Estudiante.
                6. Salir.
                Elige una opción:
                """);
    }//fin de mostrar menu
    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion){
            case 1 -> {
                //Listar estudiantes
                System.out.println("Listado de estudiantes...");
                var estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> {
                //Buscar estudiante por id
                System.out.println("Introduce el id_estudiante a buscar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if (encontrado)
                    System.out.println("Estudiante encontrado: " + estudiante);
                else
                    System.out.println("Estudiante NO encontrado: " + estudiante);
            }//fin de caso 2
            case 3 -> {
                //Agregar estudiante
                System.out.println("Agregar Estudiante: ");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                //Crear el objeto estudiante (sin el id)
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if (agregado)
                    System.out.println("Estudiante agregado: " + estudiante);
                else
                    System.out.println("Estudiante NO agregado: " + estudiante);
            }// fin de caso 3
            case 4 -> {
                //Modificar estudiante
                System.out.println("Modificar Estudiante: ");
                System.out.println("Id estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                var estudiante = new Estudiante(idEstudiante,nombre, apellido, telefono, email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                if(modificado)
                    System.out.println("Estudiante modificado: " + estudiante);
                else
                    System.out.println("Estudiante NO modificado: " + estudiante);
            }//fin de caso 4
            case 5 ->{
                //Eliminar estudiante
                System.out.println("Eliminar Estudiante: ");
                System.out.print("Id Estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
                if (eliminado)
                    System.out.println("Estudiante eliminado: " + estudiante);
                else
                    System.out.println("Estudiante NO eliminado: " + estudiante);
            }//fin de caso 5
            case 6 ->{
                //salir
                System.out.println("Hasta Pronto!");
                salir = true;
            }//fin de caso 6
            default -> System.out.println("Opcion no reconocida.");
        }
        return salir;
    }
}