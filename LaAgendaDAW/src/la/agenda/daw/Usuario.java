/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package la.agenda.daw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Alexsketit
 */
public class Usuario {

    private String nombreUsuario;
    private String clave;
    private Agenda agenda;

    public Usuario() {
        nombreUsuario = "";
        clave = "";
        agenda = new Agenda();
    }

    public boolean crearUsuario() throws IOException, UsuarioInvalidoException {
        boolean salida = false;
        do {
            Scanner datos = new Scanner(System.in);
            System.out.println("Introduce el nombre del usuario que quieres crear");
            nombreUsuario = datos.nextLine();
            validarUsuario(nombreUsuario);
            System.out.println("Ahora introduce la clave del usuario " + nombreUsuario);
            datos = new Scanner(System.in);
            clave = datos.nextLine();
            validarClave(clave);
            guardarUsuarioEnArchivo(nombreUsuario, clave);
            salida = true;
        } while (salida == false);
        return true;
    }

    public static void validarUsuario(String nombreUsuario) throws UsuarioInvalidoException {
        if (nombreUsuario.contains(" ")) {
            throw new UsuarioInvalidoException("El nombre de usuario no puede contener espacios en blanco.");
        } else if (nombreUsuario.trim().isEmpty()) {
            throw new UsuarioInvalidoException("El nombre de usuario no puede estar vacío.");
        }
    }

    public static void validarClave(String clave) throws UsuarioInvalidoException {
        if (clave.contains(" ")) {
            throw new UsuarioInvalidoException("La clave no puede contener espacios en blanco.");
        } else if (clave.trim().isEmpty()) {
            throw new UsuarioInvalidoException("La clave de usuario no puede estar vacía.");
        }
    }

    public static void guardarUsuarioEnArchivo(String nombreUsuario, String clave) throws IOException {
        try (FileWriter f1 = new FileWriter(".\\src\\la\\agenda\\daw\\Usuarios.txt", true); BufferedWriter b1 = new BufferedWriter(f1)) {
            b1.write(nombreUsuario + " ");
            b1.write(clave + " ");
        }
    }

    public boolean logUsuario2(Usuario u1) throws FileNotFoundException {
        Scanner datos = new Scanner(System.in);
        FileReader f1 = new FileReader(".\\src\\la\\agenda\\daw\\Usuarios.txt");
        BufferedReader b1 = new BufferedReader(f1);
        String linea = "";
        String apoyo = "";
        int posicion = 0;
        boolean probar = false;
        try {
            do {
                linea = b1.readLine();
                if (apoyo.equals("")) {
                    apoyo = linea;
                } else {
                    probar = true;
                }
                if (linea != null && probar) {
                    apoyo = apoyo + " " + linea;
                }
            } while (linea != null);
            String[] usuarios = apoyo.split(" ");
            System.out.println("Introduce tu nombre de usuario para iniciar sesion");
            nombreUsuario = datos.nextLine();
            validarUsuario(nombreUsuario);

            for (int x = 0; x < usuarios.length; x += 2) {
                if (usuarios[x].equals(nombreUsuario)) {
                    System.out.println("Usuario encontrado!");
                    posicion = x;
                    break;
                }

                if (x == usuarios.length - 1) {
                    System.out.println("Usuario no registrado");
                    return false;
                }
            }
            System.out.println("Introduce la clave del usuario " + nombreUsuario);
            clave = datos.nextLine();
            validarClave(clave);
            if (clave.equals(usuarios[posicion + 1])) {
                System.out.println("Contraseña correcta");
                System.out.println("Sesion iniciada. Bienvenido de nuevo " + nombreUsuario + "!");
                return true;
            } else {
                System.out.println("Usuario/a o contraseña incorrecto");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el usuario " + e.getMessage());
        } catch (UsuarioInvalidoException e) {
            System.out.println("Usuario invalido: " + e.getMessage());
        }
        return false;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Agenda getAgenda() {
        return agenda;
    }
    
    

}
