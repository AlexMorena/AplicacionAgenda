/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package la.agenda.daw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author Alexsketit
 */
public class LaAgendaDAW {

    public static void segundoMenu(String usuario, Agenda agenda) {
        Scanner datos = new Scanner(System.in);
        boolean salida = false;
        do {
            System.out.println("Introduce una opcion");
            System.out.println("1.Registrar Contactos " + "\n2.Ver Contactos " + "\n3.Buscar Contacto " + "\n4.Eliminar Contacto " + " \n5.Salir");
            int opcion = datos.nextInt();
            switch (opcion) {
                case 1:
                    agenda.registrarContacto();
                    break;
                case 2:
                    agenda.verContactos();
                    break;
                case 3:
                    agenda.buscarContacto();
                    break;
                case 4:
                    agenda.eliminarContacto();
                    break;
                case 5:
                    System.out.println("Gracias por usar el programa");
                    salida = true;
                    agenda = Agenda.guardarAgenda(usuario, agenda);
                    break;
                default:
                    System.out.println("Introduce una opcion valida");
            }
        } while (salida == false);

    }

    public static void menu(Agenda agenda) {
        Scanner datos = new Scanner(System.in);
        boolean valido;
        boolean log;
        boolean salida = false;
        Usuario u1 = new Usuario();
        System.out.println("------------ Bienvenido a la Agenda de Daw ------------");
        do {
            try {
                System.out.println("Que desea hacer? " + "\n1.Crear nuevo usuario " + "\n2.Iniciar sesion " + "\n3.Salir");
                datos = new Scanner(System.in);
                int opcion = datos.nextInt();
                switch (opcion) {
                    case 1:
                        valido = u1.crearUsuario();
                        if (valido) {
                            System.out.println("Usuario registrado");
                            segundoMenu(u1.getNombreUsuario(), agenda);
                            salida = true;
                        }
                        break;
                    case 2:
                        log = u1.logUsuario2(u1);
                        if (log) {
                            System.out.println("Inicio de sesion realizado. Bienvenido!");
                            agenda = Agenda.cargarAgenda(u1.getNombreUsuario());
                            segundoMenu(u1.getNombreUsuario(), agenda);
                            salida = true;
                        } else {
                            System.out.println("No se ha podido iniciar sesion");
                        }
                        break;
                    case 3:
                        System.out.println("Saliendo de la agenda");
                        salida = true;
                        break;
                    default:
                        System.out.println("Introduce una opcion valida");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Archivo invalido: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error al guardar el usuario " + e.getMessage());
            } catch (UsuarioInvalidoException e) {
                System.out.println("Usuario invalido: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Introduce un dato valido,error " + e.getMessage());
            }
        } while (salida == false);
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        menu(agenda);
    }

}
