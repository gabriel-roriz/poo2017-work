/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpoo.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author khazyer
 */
public class RelatorioFaturaScreen implements ActionListener {

    JPanel pFaturaPorCorretor;

    public RelatorioFaturaScreen() {
        pFaturaPorCorretor = new JPanel();
    }

    public JPanel getpFaturaPorCorretor() {
        return pFaturaPorCorretor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}