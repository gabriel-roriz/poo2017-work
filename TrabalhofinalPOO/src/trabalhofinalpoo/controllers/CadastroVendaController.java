package trabalhofinalpoo.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import trabalhofinalpoo.views.CadastroVendaScreen;

public class CadastroVendaController implements ActionListener{
    
    CadastroVendaScreen view;
        
    public CadastroVendaController(CadastroVendaScreen mView) {
        view = mView;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() instanceof JButton){
            JButton buttonClicked = (JButton) ae.getSource();
            if(buttonClicked.getText().equals(CadastroVendaScreen.BUTTON_SAVE)){
                buttonSaveClicked();
            }
            if(buttonClicked.getText().equals(CadastroVendaScreen.BUTTON_CLEAR)){
                buttonClearClicked();
            }
        }
        
        if(ae.getSource() instanceof JComboBox){
            JComboBox jcomboBoxSelected = (JComboBox) ae.getSource();
            /*if(jcomboBoxSelected.getName().equals(CadastroCorretorScreen.JCOMBOX_CATEGORIES)){
                changeCategory();
            }*/
        }
    }
    
    private void buttonClearClicked() {
        view.clearFields();
    }

    private void buttonSaveClicked() {
        if(validateGeneralInfo()){
            view.showMessage("DEU TUDO CERTO!", false);
        } 
    }
    
    public boolean hasImovelSelected(){
        System.out.println(view.getImovel() + " " + (view.getImovel() == null ? true : false));
        return (view.getImovel() == null ? false : true); 
    }
    
    public boolean hasCorretorSelected(){
        System.out.println(view.getCorretor() + " " + (view.getCorretor() == null ? true : false));
        return (view.getCorretor() == null ? false : true); 
    }
    
    private boolean validateGeneralInfo(){
        //valida nome do comprador
        if(view.getNomeComprador().equals("")){
            view.showMessage("Digite o nome do comprador", true);
            return false;
        }
        
        //valida valor da venda
        if(!view.getValorDaVenda().equals("")){
            try {
                float valorDaVenda = Float.parseFloat(view.getValorDaVenda());
            } catch (NumberFormatException ex) {    
                view.showMessage("Digite um valor de venda válido.", true);
                return false;
            }
        } else {
            view.showMessage("Digite um valor de venda.", true);
            return false;
        }
        
        //valida corretor
        if(!hasCorretorSelected()){
            view.showMessage("Selecione um corretor.", true);
            return false;
        }
        
        //valida imovel
        if(!hasImovelSelected()){
            view.showMessage("Selecione um imóvel", true);
            return false;
        }
        
        return true;
    }
}
