/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package la.agenda.daw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alexsketit
 */
public class Contactos implements Serializable{

    private String nombre;
    private String apellido;
    private int telefono;
    private String email;
    private int edad;
    private ArrayList<Contactos> contactos;

    public Contactos(String nombre, String apellido, int telefono, String email, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.edad = edad;
        contactos = new ArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public int getEdad() {
        return edad;
    }

    public ArrayList<Contactos> getContactos() {
        return contactos;
    }
}
