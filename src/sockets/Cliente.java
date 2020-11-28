/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente extends Observable implements Runnable{
    private String host;
    private int puerto;
    private String mensaje;
    private String llave;
    private String metodo;

    public Cliente( String host, int puerto, String mensaje1, String mensaje2, String mensaje3){
        this.host=host;
        this.puerto=puerto;
        this.mensaje=mensaje1;
        this.llave=mensaje2;
        this.metodo=mensaje3;
    }


    @Override
    public void run() {

            DataInputStream in;
            DataOutputStream out;

        try {
            Socket sc = new Socket(host, puerto);

            out = new DataOutputStream(sc.getOutputStream()); //enviar mensajes
            in = new DataInputStream(sc.getInputStream());

            out.writeUTF(mensaje);
            System.out.println("mensaje: "+mensaje);
            out.writeUTF(llave);
            System.out.println("longitud del primo: "+llave);
            out.writeUTF(metodo);
            System.out.println("longitud del primo: "+metodo);
            
            
            String clave=in.readUTF();
            

            this.setChanged();//avisa que va a cambiar
            this.notifyObservers(clave); //actualiza
            this.clearChanged(); //limpia

            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}