/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.net.Sockets;

/**
 *
 * @author josue
 */
public class Servidor extends Observable implements Runnable{

        RSA obj = new RSA();


    private int puerto;
    public Servidor (int puerto){
        this.puerto = puerto;
    }
    
    
    @Override
    public void run() {

        ServerSocket servidor = null;
        Socket sc=null;  //socket del cliente
        DataInputStream in;
        DataOutputStream out;

        
        
        try {

            System.out.println("El server ha iniciado");
            servidor =new ServerSocket(puerto);
            
            while(true){
                sc = servidor.accept();
                out=new DataOutputStream(sc.getOutputStream());
                in = new DataInputStream(sc.getInputStream()); //recibir mensaje
                
                
                String mensaje = in.readUTF(); //espera al envio
                System.out.println(mensaje);
                int llave = Integer.parseInt(in.readUTF()); //espera al envio
                System.out.println(llave);
                String cifrar = in.readUTF(); //espera al envio
                System.out.println(cifrar);
                //mete aqui el cifrado y daselo al notifyObserver
                
                obj.tamPrimo = llave;
                String textoCifrado ="";
                
              if (cifrar.equals("cifrar")) {//cifrar
                    
                    textoCifrado = obj.encriptar(mensaje).toString();
                    
                    
                }else{  //decifrar
                    
                   /* String textoDecifrado = obj.desencriptar(textoCifrado2).toString();
                    */
                }
                
                
                String todoJunto="";
                todoJunto="mensaje a querer cifrar: "+mensaje+"\n"+"longitud de la llave: "+llave+"\n"+"Numero cifrado: "+textoCifrado+"\n"+"";
                System.out.println(textoCifrado);
                //mensaje
                this.setChanged();//avisa que va a cambiar
                this.notifyObservers(todoJunto); //actualiza
                this.clearChanged(); //limpia
                
                
                
                
                
                
                
                
                
                
                this.setChanged();//avisa que va a cambiar
                this.notifyObservers(textoCifrado); //actualiza
                this.clearChanged(); //limpia
                
                
                
                
                
                
                //respuesta
                out.writeUTF(todoJunto);
                
                
                
                sc.close();
                System.out.println("Cliete desconectado");
                    
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Sockets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    /*

    public void pqndephi(){
        obj.generarPrimos();
        obj.generarClaves();
        ptxt.setText(obj.damep().toString());
        dtxt.setText(obj.damed().toString());
        qtxt.setText(obj.dameq().toString());
        ntxt.setText(obj.damen().toString());
        etxt.setText(obj.damee().toString());
        phitxt.setText(obj.dametotient().toString());
    }
*/

