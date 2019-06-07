/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidoryisheng;

import java.awt.Color;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author Thomas
 */
public class janelaPaciente extends JFrame
{    
    private static final long serialVersionUID = 1L;
    
    public janelaPaciente()
    {
        super("YiSheng - 医生 - Paciente");
        setSize(500,500);
        setLocationRelativeTo(null);
        
        panelOpcoes jpp = new panelOpcoes();
        
        jpp.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.green),BorderFactory.createLineBorder(Color.black)));  
        
        URL iconURL = getClass().getResource("./images/medic.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        
        add(jpp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
