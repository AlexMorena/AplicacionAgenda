/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package la.agenda.daw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alexsketit
 */
public class Agenda implements Serializable {

    private ArrayList<Contactos> contactos;
    private String nombreAgenda;

    public Agenda() {
        contactos = new ArrayList();
    }

    public void registrarContacto() {
        Scanner datos = new Scanner(System.in);
        System.out.println("Introduce el nombre del contacto");
        String nombre = datos.nextLine();
        System.out.println("Introduce el apellido del contacto");
        String apellido = datos.next();
        System.out.println("Introduce el telefono del contacto");
        int telefono = datos.nextInt();
        System.out.println("Introduce el email del contacto");
        String email = datos.next();
        System.out.println("Introduce la edad del contacto");
        int edad = datos.nextInt();
        Contactos c1 = new Contactos(nombre, apellido, telefono, email, edad);
        contactos.add(c1);
    }

    public String infoContactos(Contactos contacto) {
        String info = "";
        info = "El nombre del contacto es: " + contacto.getNombre() + "\nSu apellido es: " + contacto.getApellido()
                + "\nSu telefono es: " + contacto.getTelefono() + "\nSu eMail es: " + contacto.getEmail() + "\nSu edad es de: " + contacto.getEdad() + " años";
        String linea = "----------------------------------------------";
        info = info + "\n" + linea;
        return info;
    }

    public void verContactos() {
        for (Contactos actual : contactos) {
            System.out.println(infoContactos(actual));
        }
    }

    public void buscarContacto() {
        Scanner datos = new Scanner(System.in);
        System.out.println("Introduce el nombre del contacto a buscar");
        String nombreContacto = datos.nextLine();
        System.out.println("Ahora introduce el apellido del contacto");
        String apellidoContacto = datos.nextLine();
        for (int x = 0; x < contactos.size(); x++) {
            if (nombreContacto.equalsIgnoreCase(contactos.get(x).getNombre()) && apellidoContacto.equalsIgnoreCase(contactos.get(x).getApellido())) {
                System.out.println("Contacto encontrado!");
                System.out.println(infoContactos(contactos.get(x)));
            } else {
                System.out.println("Contacto no registrado.");
            }
        }
    }

    public void eliminarContacto() {
        Scanner datos = new Scanner(System.in);
        System.out.println("Introduce el nombre del contacto a eliminar");
        String nombreContacto = datos.nextLine();
        System.out.println("Ahora introduce el apellido del contacto");
        String apellidoContacto = datos.nextLine();
        for (int x = 0; x < contactos.size(); x++) {
            if (nombreContacto.equals(contactos.get(x).getNombre()) && apellidoContacto.equals(contactos.get(x).getApellido())) {
                System.out.println("Contacto encontrado!");
                contactos.remove(x);
            } else {
                System.out.println("Contacto no registrado.");
            }
        }
    }

    public static Agenda guardarAgenda(String usuario, Agenda agenda) {
        try {
            FileOutputStream miFlujo = new FileOutputStream("Agenda" + usuario + ".dat");
            ObjectOutputStream miObjetoCopia = new ObjectOutputStream(miFlujo);
            miObjetoCopia.writeObject(agenda);
            System.out.println("Agenda guardada con éxito");
            miObjetoCopia.close();
            miFlujo.close();
        } catch (IOException e) {
            System.out.println("No ha sido posible guardar el garaje " + e.getMessage());
        }

        return agenda;
    }

    public static Agenda cargarAgenda(String usuario) {
        Agenda agenda = new Agenda();
        try {
            FileInputStream flujoEntrada = new FileInputStream("Agenda" + usuario + ".dat");
            ObjectInputStream ObjectCopia = new ObjectInputStream(flujoEntrada);
            agenda = (Agenda) ObjectCopia.readObject();
            System.out.println("Agenda cargada");
            ObjectCopia.close();
            flujoEntrada.close();
        } catch (FileNotFoundException e) {
            System.out.println("no se ha encontrado el archivo buscado" + e.getMessage());
        } catch (IOException e) {
            System.out.println("problemas al cargar el taller " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("surgieron problemas al cargar el taller " + e.getMessage());
        }
        return agenda;
    }
}
