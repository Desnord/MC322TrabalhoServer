/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidoryisheng;

import javax.swing.JFrame;

/**
 *
 * @author Thomas
 */
public class janelaPaciente extends JFrame
{    
    public janelaPaciente()
    {
        super("YiSheng - 医生 - Paciente");
        setSize(500,300);
        setLocationRelativeTo(null);
        
        panelOpcoes jpp = new panelOpcoes();
        
        add(jpp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
