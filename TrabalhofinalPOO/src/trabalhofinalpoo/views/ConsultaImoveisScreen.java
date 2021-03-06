/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpoo.views;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.text.DateFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import trabalhofinalpoo.controllers.ConsultaImoveisController;
import trabalhofinalpoo.models.Comissionado;
import trabalhofinalpoo.models.Contratado;
import trabalhofinalpoo.models.DateLabelFormatter;
import trabalhofinalpoo.models.Imovel;

/**
 *
 * @author khazyer
 */
public class ConsultaImoveisScreen implements FechamentoTelas {
    
    public static final String COMBOBOX_TIPOS_CONSULTA = "Combo Box Tipos Consulta";
    public static final String COMBOBOX_TIPOS_EDIT = "Combo Box Tipos Edit";
    
    public static final String BUTTON_VENDER = "Vender";
    public static final String BUTTON_SALVAR = "Salvar";
    public static final String BUTTON_RESTAURAR = "Restarurar";
    public static final String BUTTON_REMOVE = "Remover";
    
    JPanel pConImoveis, pOrganize, pConsulta, pEdit;
    JLabel labelConsulta, lLista, labelImovel, labelMensagemConsulta, labelMensagemEdit, lTipo1, lTipo2, lCod, lDesc, lPreco, lDate;
    JTextField textCod, textPreco, textData;
    JTextArea textArea2;
    JScrollPane scrollPane1, scrollPane2;
    JButton bReset, bSalvar, bVenda, buttonRemove;
    JComboBox boxTiposConsulta, boxTiposEdit;
    DefaultComboBoxModel comboBoxTipoEditModel, comboBoxTipoConsultaModel;
    JList lista;
    DefaultListModel itensLista;
    GridBagConstraints c;
    
    UtilDateModel model;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    
    private ConsultaImoveisController controller;
    
    MainScreen main;
    
    public ConsultaImoveisScreen(MainScreen mMain) {
        main = mMain;
        controller = new ConsultaImoveisController(this);
        
        pConImoveis = new JPanel(new BorderLayout());
        pOrganize = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        instanceConsulta();
        instanceEdit();
        c.ipadx = 0;
        c.fill = GridBagConstraints.BOTH; // Preencher nas duas direções
        c.insets = new Insets(10, 10, 10, 10);
        
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        labelConsulta = new JLabel("Consulta Imóveis");
        labelConsulta.setHorizontalAlignment(JLabel.CENTER);
        pOrganize.add(labelConsulta, c);
        
        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 1;
        pOrganize.add(pConsulta, c);
        c.gridx = 1;
        pOrganize.add(pEdit, c);
        
        pConImoveis.add(pOrganize, BorderLayout.PAGE_START);
    }
    
    public void instanceConsulta() {
        pConsulta = new JPanel(new GridBagLayout());
        c.ipadx = 150;
        c.fill = GridBagConstraints.BOTH; // Preencher nas duas direções
        c.insets = new Insets(10, 10, 10, 10);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        lTipo1 = new JLabel("Tipo:");
        pConsulta.add(lTipo1, c);
        
        c.gridy++;
        boxTiposConsulta = new JComboBox();
        boxTiposConsulta.setName(COMBOBOX_TIPOS_CONSULTA);
        boxTiposConsulta.addActionListener(controller);
        pConsulta.add(boxTiposConsulta, c);
        
        c.gridy++;
        lLista = new JLabel("Lista Imóveis:");
        pConsulta.add(lLista, c);
        
        c.gridy++;
        c.ipady = 100;
        lista = new JList();
        lista.addListSelectionListener(controller);
        lista.setBorder(BorderFactory.createEtchedBorder());
        scrollPane1 = new JScrollPane(lista, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pConsulta.add(scrollPane1, c);
        
        c.gridy++;
        c.ipady = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        buttonRemove = new JButton(BUTTON_REMOVE);
        buttonRemove.addActionListener(controller);
        pConsulta.add(buttonRemove, c);
        
        c.gridx = 1;
        bVenda = new JButton(BUTTON_VENDER);
        bVenda.addActionListener(controller);
        pConsulta.add(bVenda, c);
        
        c.gridy++;
        c.gridwidth = 2;
        c.gridx = 0;
        c.ipady = 10;
        
        labelMensagemConsulta = new JLabel();
        labelMensagemConsulta.setHorizontalAlignment(JLabel.CENTER);
        pConsulta.add(labelMensagemConsulta, c);
    }
    
    public void instanceEdit() {
        pEdit = new JPanel(new GridBagLayout());
        changeVisibleEdit(false);
        
        c.ipadx = 100;
        c.fill = GridBagConstraints.BOTH; // Preencher nas duas direções
        c.insets = new Insets(10, 10, 10, 10);

        //Painel do Cadastro de Imovel        
        pEdit.setLayout(new GridBagLayout());
        GridBagConstraints i = new GridBagConstraints();
        i.fill = GridBagConstraints.BOTH;
        i.insets = new Insets(10, 10, 10, 10);
        i.ipadx = 150;
        
        i.gridx = 0;
        i.gridy = 0;
        i.gridwidth = 4;
        labelImovel = new JLabel("Editar Imóvel");
        labelImovel.setHorizontalAlignment(JLabel.CENTER);
        pEdit.add(labelImovel, i);
        
        i.gridx = 0;
        i.gridy++;
        i.gridwidth = 2;
        i.ipadx = 100;
        lCod = new JLabel("Cod:");
        pEdit.add(lCod, i);
        lTipo2 = new JLabel("Tipo:");
        i.gridx = 2;
        i.ipadx = 150;
        pEdit.add(lTipo2, i);
        
        textCod = new JTextField(20);
        textCod.setEnabled(false);
        i.gridx = 0;
        i.gridy++;
        i.ipadx = 100;
        pEdit.add(textCod, i);
        boxTiposEdit = new JComboBox();
        boxTiposEdit.setName(COMBOBOX_TIPOS_EDIT);
        boxTiposEdit.setEnabled(false);
        i.gridx = 2;
        i.ipadx = 150;
        pEdit.add(boxTiposEdit, i);
        
        lDesc = new JLabel("Descrição:");
        i.gridx = 0;
        i.gridy++;
        i.gridwidth = 1;
        i.ipadx = 100;
        pEdit.add(lDesc, i);
        textArea2 = new JTextArea();
        textArea2.setBorder(BorderFactory.createEtchedBorder());
        scrollPane2 = new JScrollPane(textArea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        i.gridx = 1;
        i.gridwidth = 3;
        i.ipadx = 150;
        i.ipady = 50;
        pEdit.add(scrollPane2, i);
        i.ipady = 0;
        
        lPreco = new JLabel("Preço:");
        i.gridx = 0;
        i.gridy++;
        i.gridwidth = 1;
        i.ipadx = 100;
        pEdit.add(lPreco, i);
        textPreco = new JTextField();
        i.gridx = 1;
        i.gridwidth = 3;
        i.ipadx = 150;
        pEdit.add(textPreco, i);
        
        lDate = new JLabel("Data:");
        i.gridx = 0;
        i.gridy++;
        i.gridwidth = 1;
        i.ipadx = 100;
        pEdit.add(lDate, i);
        textData = new JTextField();
        textData.setEnabled(false);
        i.gridx = 1;
        i.gridwidth = 3;
        i.ipadx = 150;
        pEdit.add(textData, i);
        
        bReset = new JButton(BUTTON_RESTAURAR);
        bReset.addActionListener(controller);
        
        i.gridx = 0;
        i.gridy++;
        i.gridwidth = 2;
        i.ipadx = 100;
        pEdit.add(bReset, i);
        bSalvar = new JButton(BUTTON_SALVAR);
        bSalvar.addActionListener(controller);
        i.gridx = 2;
        i.ipadx = 150;
        pEdit.add(bSalvar, i);
        
        labelMensagemEdit = new JLabel();
        labelMensagemEdit.setHorizontalAlignment(JLabel.CENTER);
        
        i.gridy = +15;
        i.gridx = 0;
        i.gridwidth = 4;
        pEdit.add(labelMensagemEdit, i);
    }
    
    public JPanel getpConImoveis() {
        return pConImoveis;
    }
    
    @Override
    public void closeTela() {
        System.out.println("saiu da tela consulta imoveis screen");
    }
    
    public void showMessageConsulta(String message, boolean isError) {
        if (isError) {
            labelMensagemConsulta.setForeground(Color.RED);
        } else {
            labelMensagemConsulta.setForeground(Color.GREEN);
        }
        labelMensagemConsulta.setText(message);
    }
    
    public void showMessageEdit(String message, boolean isError) {
        if (isError) {
            labelMensagemEdit.setForeground(Color.RED);
        } else {
            labelMensagemEdit.setForeground(Color.GREEN);
        }
        labelMensagemEdit.setText(message);
    }
    
    public void clearFields() {
        updateTipoComboBoxEdit(new ArrayList<String>());
        textCod.setText("");
        textPreco.setText("");
        textArea2.setText("");
    }
    
    public void changeVisibleEdit(boolean visible) {
        pEdit.setVisible(visible);
    }
    
    public void showImovel(Imovel imovel) {
        if (pEdit.isVisible() && imovel != null) {
            textCod.setText(Long.toString(imovel.getCodigo()));
            textPreco.setText(Float.toString(imovel.getPreço()));
            textArea2.setText(imovel.getDescricao());
            textData.setText(imovel.getDataDeCadastro().toString());
            updateTipoComboBoxEdit(new ArrayList<String>(Arrays.asList(Imovel.getTipoString(imovel.getTipo()))));
        }
    }
    
    public Imovel getImovelSelected() {
        return (Imovel) lista.getSelectedValue();
    }
    
    public String getTipoSelected() {
        return (String) boxTiposConsulta.getSelectedItem();
    }
    
    public void updateTipoComboBoxConsulta(ArrayList<String> tipos) {
        DefaultComboBoxModel model = new DefaultComboBoxModel(tipos.toArray());
        boxTiposConsulta.setModel(model);
    }
    
    public void updateTipoComboBoxEdit(ArrayList<String> tipos) {
        DefaultComboBoxModel model = new DefaultComboBoxModel(tipos.toArray());
        boxTiposEdit.setModel(model);
    }
    
    public void updateList(ArrayList<Imovel> novaLista) {
        DefaultListModel modeloLista = new DefaultListModel<Imovel>();
        for (Imovel imovel : novaLista) {
            modeloLista.addElement(imovel);
        }
        
        lista.setModel(modeloLista);
    }
    
    public String getDescription() {
        return textArea2.getText();
    }
    
    public String getPreço() {
        return textPreco.getText();
    }
    
    public void callTelaDeVenda() {
        main.callTelaDeVenda(getImovelSelected());
    }
    
    @Override
    public void abrirTela() {
        controller.loadDados();
    }
}
