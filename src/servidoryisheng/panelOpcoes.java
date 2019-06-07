/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidoryisheng;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Thomas
 */
public class panelOpcoes extends JPanel
{    
    private JComboBox jcbs[]; 
    private JButton Enviar;
    private int random = (int)(Math.random()*4+1);            
    private String sintomas = "";
    private JTextArea JTInfoPac;
    
    public panelOpcoes()
    {    
        JTInfoPac = new JTextArea();
        JTInfoPac.setForeground(Color.black);
        JTInfoPac.setWrapStyleWord(true);
        JTInfoPac.setLineWrap(true);
        JTInfoPac.setEditable(false);       
        JScrollPane JSInfoPac = new JScrollPane(JTInfoPac);
        JSInfoPac.setBorder(BorderFactory.createLineBorder(Color.black,1));
                
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
        
        setLayout(new BorderLayout());
        
        
        //jpanel south
        JPanel jps = new JPanel();
        GridLayout gl = new GridLayout(vetSint.length+1,2);   
        jps.setLayout(gl);
        
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
            
            jps.add(vetLbl[i]);
            jps.add(jcbs[i]);
        }
        Enviar = new JButton("Enviar");
        Enviar.setForeground(Color.black);
        Enviar.setBorder(BorderFactory.createLineBorder(Color.black,1));
      
        JLabel lblX = new JLabel();
        lblX.setBorder(BorderFactory.createLineBorder(Color.black,1));
        
        
        jps.add(lblX);
        jps.add(Enviar);
        Enviar.addActionListener(new Envio());
        
        
        //jpanel center
        JPanel jpc = new JPanel(new GridLayout(1,2));
       
        URL iconURL = getClass().getResource("./images/zombie.png");
        ImageIcon icon = new ImageIcon(iconURL);
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg);
        JLabel lblImg = new JLabel(icon, JLabel.CENTER);  
        lblImg.setBorder(BorderFactory.createLineBorder(Color.black,1));
        
        jpc.add(lblImg);
        jpc.add(JSInfoPac);
        
        add(BorderLayout.CENTER,jpc);
        add(BorderLayout.SOUTH,jps);
    }
    
    //evento do botao enviar
    private class Envio implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
    	{
            JTInfoPac.append("Aguardando conexão... \n");       

            try 
            {
                ServerSocket server = new ServerSocket(Integer.parseInt("6660"));
                Socket conexao = server.accept();
                JTInfoPac.append("Medico conectado! \n");
                ObjectOutputStream out = new ObjectOutputStream(conexao.getOutputStream());

                String st = random+"";

                for(int i=0;i<jcbs.length;i++)
                {
                    st = st+",";
                    char opcao_at;
                    
                    if(jcbs[i].getSelectedItem() == "Sim")
                        opcao_at = 't';
                    else
                        opcao_at = 'f';

                    st = st+opcao_at;
                }
                    out.writeObject(st);
                }        
            catch(IOException ex)
            {}
        }         
    }
}
