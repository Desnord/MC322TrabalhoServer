/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidoryisheng;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Thomas
 */
public class panelOpcoes extends JPanel
{    
    JComboBox jcbs[]; 
    JButton Enviar;
    int random = (int)(Math.random()*4+1);            
    String sintomas = "";
    JLabel lblInfo;
    
    
    public panelOpcoes()
    {    
        lblInfo = new JLabel("Aguardando conexão...");
        lblInfo.setForeground(Color.black);
        lblInfo.setBorder(BorderFactory.createLineBorder(Color.black,1));
        
        switch(random)
        {
            case 1:
                sintomas = "paralysis:,yellow tongue:,trembiling finger:,member loss:,chest pain:,severe anger";
            case 2:
                sintomas = "paralysis:,yellow tongue:,member loss:,chest pain:,trembiling finger:,severe anger:,red eye:,blue skin";
            case 3:
                sintomas = "Paralisia:,Lingua Amarela:,Dedo Tremendo:,Perda de Membro:,Dor no Peito:,Raiva Severa:,Olho vermelho:,Pele azul:";
            case 4:
                sintomas = "paralysis:,yellow tong,member loss:,chest pain:,trembling finger:,severe anger:,history bacteria:";
        }
        
        
        String vetSint[] = sintomas.split(",");
        JLabel vetLbl[] = new JLabel[vetSint.length];
        
        GridLayout gl = new GridLayout(vetSint.length+1,2);   
        setLayout(gl);
        
        jcbs = new JComboBox[vetSint.length];
        
        for(int i=0;i<vetSint.length;i++)
        {
            JComboBox jb_at = new JComboBox();
            jb_at.setForeground(Color.black);
            jb_at.setBorder(BorderFactory.createLineBorder(Color.black,1));
            
            jb_at.addItem("Sim");
            jb_at.addItem("Não");
            jcbs[i] = jb_at;
        }
        
        for(int i=0;i<vetSint.length;i++)
        {
            vetLbl[i] = new JLabel(vetSint[i]);
            vetLbl[i].setForeground(Color.black);
            vetLbl[i].setBorder(BorderFactory.createLineBorder(Color.black,1));
            
            add(vetLbl[i]);
            add(jcbs[i]);
        }
        Enviar = new JButton("Enviar");
        Enviar.setForeground(Color.black);
        Enviar.setBorder(BorderFactory.createLineBorder(Color.black,1));
        
        add(lblInfo);
        add(Enviar);
        Enviar.addActionListener(new Envio());
    }
    
    private class Envio implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
    	{
        
        try
        {
            ServerSocket server = new ServerSocket(Integer.parseInt("6660"));

            lblInfo.setText("Aguardando conexão...");
            Socket conexao = server.accept(); 
            lblInfo.setText("medico conectado!");
            ObjectOutputStream out = new ObjectOutputStream(conexao.getOutputStream());
                
            String st = random+"";
                
            for(int i=0;i<jcbs.length;i++)
            {
                st = st+",";
                boolean opcao_at;
                if(jcbs[i].getSelectedItem() == "Sim")
                    opcao_at = true;
                else
                    opcao_at = false;
                                        
                st = st+opcao_at;
            }
                
            out.writeObject(st);
        }
        catch(Exception ex)
        {}
        
        }      
    }
}
