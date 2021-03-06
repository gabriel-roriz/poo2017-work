/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpoo.views;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import trabalhofinalpoo.controllers.CadastroVendaController;
import trabalhofinalpoo.dados.Dados;
import trabalhofinalpoo.models.Comissionado;
import trabalhofinalpoo.models.Contratado;
import trabalhofinalpoo.models.Corretor;
import trabalhofinalpoo.models.Imovel;

/**
 *
 * @author khazyer
 */
public class CadastroVendaScreen implements FechamentoTelas {

    public static final String BUTTON_SAVE = "Salvar";
    public static final String BUTTON_CLEAR = "Limpar";

    JPanel pCadVenda, pCad;
    JLabel labelCadVenda, lNomeComprador, lValorVenda, lCorretor, lImovel, labelMensagem;
    JTextField textNomeComprador, textValorVenda;
    JComboBox boxCorretor, boxImovel;
    DefaultComboBoxModel modelImovel;
    JButton limpar, cadastrar;
    GridBagConstraints c;

    CadastroVendaController controller;

    public CadastroVendaScreen() {
        controller = new CadastroVendaController(this);
        instanceView();
    }

    public void instanceView() {
        pCadVenda = new JPanel();
        pCadVenda.setLayout(new BorderLayout());
        pCad = new JPanel();
        pCad.setLayout(new GridBagLayout());

        labelCadVenda = new JLabel("Cadastro de Venda");
        labelCadVenda.setHorizontalAlignment(JLabel.CENTER);
        lNomeComprador = new JLabel("Nome Comprador:");
        lValorVenda = new JLabel("Valor da Venda");
        lCorretor = new JLabel("Corretor:");
        lImovel = new JLabel("Imovel");
        labelMensagem = new JLabel();
        labelMensagem.setHorizontalAlignment(JLabel.CENTER);

        textNomeComprador = new JTextField();
        textValorVenda = new JTextField();

        String[] cat1 = {"--", "joão", "Maria", "José"};
        boxCorretor = new JComboBox();
        boxCorretor.addActionListener(controller);
        String[] cat2 = {"--", "AP 125", "Casa Branca", "Kitnet 106"};
        boxImovel = new JComboBox();
        boxImovel.addActionListener(controller);

        limpar = new JButton(BUTTON_CLEAR);
        limpar.addActionListener(controller);
        cadastrar = new JButton(BUTTON_SAVE);
        cadastrar.addActionListener(controller);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH; // Preencher nas duas direções
        c.insets = new Insets(10, 10, 10, 10);
        c.ipadx = 150;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        pCad.add(labelCadVenda, c);

        c.gridx = 0;
        c.gridy++;
        c.gridheight = 1;
        c.gridwidth = 1;
        pCad.add(lNomeComprador, c);
        c.gridx = 1;
        pCad.add(textNomeComprador, c);

        c.gridx = 0;
        c.gridy++;
        pCad.add(lImovel, c);
        c.gridx = 1;
        pCad.add(boxImovel, c);

        c.gridx = 0;
        c.gridy++;
        pCad.add(lValorVenda, c);
        c.gridx = 1;
        pCad.add(textValorVenda, c);

        c.gridx = 0;
        c.gridy++;
        pCad.add(lCorretor, c);
        c.gridx = 1;
        pCad.add(boxCorretor, c);

        c.gridx = 0;
        c.gridy = 10;
        pCad.add(limpar, c);
        c.gridx = 1;
        pCad.add(cadastrar, c);

        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        pCad.add(labelMensagem, c);
        pCadVenda.add(pCad, BorderLayout.PAGE_START);
    }
    
    public void setImovel(Imovel imovel){
       modelImovel.setSelectedItem(imovel);
    }

    public String getNomeComprador() {
        return textNomeComprador.getText();
    }

    public String getValorDaVenda() {
        return textValorVenda.getText();
    }

    public Imovel getImovel() {
        if (boxImovel.getSelectedItem().toString().equals("--")) {
            return null;
        } else {
           return (Imovel) boxImovel.getSelectedItem();
        }
        
    }

    public Corretor getCorretor() {
        if (boxCorretor.getSelectedItem().toString().equals("--")) {
            return null;
        } else {
            return (Corretor) boxCorretor.getSelectedItem();
        }
    }

    public JPanel getPanel() {
        return pCadVenda;
    }

    public void clearFields() {
        labelMensagem.setText("");
        textNomeComprador.setText("");
        textValorVenda.setText("");
        boxImovel.setSelectedItem("--");
        boxCorretor.setSelectedItem("--");
    }
    
    public void updateCorretorComboBox(ArrayList<Corretor> corretores){
        DefaultComboBoxModel model = new DefaultComboBoxModel( corretores.toArray() );
        boxCorretor.setModel(model);
    }
    
    public void updateImoveisComboBox(ArrayList<Imovel> imoveis) {
        modelImovel = new DefaultComboBoxModel( imoveis.toArray() );
        boxImovel.setModel(modelImovel);
    }

    public void showMessage(String message, boolean isError) {
        if (isError) {
            labelMensagem.setForeground(Color.RED);
        } else {
            clearFields();
            labelMensagem.setForeground(Color.GREEN);
        }
        labelMensagem.setText(message);
    }

    @Override
    public void closeTela() {
        System.out.println("saiu da tela cadastro vendas screen");
    }

    @Override
    public void abrirTela() {
        clearFields();
        controller.loadDados();
    }    

}
