/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinalpoo.models;

import java.io.Serializable;
import java.util.Date;
import trabalhofinalpoo.dados.Data;

/**
 * @author gabrielroriz
 */

public class Venda implements Serializable{
    
    float valor;
    
    String nomeDoComprador;
    
    Data dataDaVenda;
    
    int numeroCRECIResponsavel;
    
    Long codigoImovel;
    
    public Venda(float mValor, String mNomeDoComprador, int mNumeroCRECI, Long mCodImovel, Data data){
       valor = mValor;
       nomeDoComprador = mNomeDoComprador;
       numeroCRECIResponsavel = mNumeroCRECI;
       codigoImovel = mCodImovel;
       dataDaVenda =  data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getNomeDoComprador() {
        return nomeDoComprador;
    }

    public void setNomeDoComprador(String nomeDoComprador) {
        this.nomeDoComprador = nomeDoComprador;
    }

    public Data getDataDaVenda() {
        return dataDaVenda;
    }

    public void setDataDaVenda(Data dataDaVenda) {
        this.dataDaVenda = dataDaVenda;
    }
    
    public int getNumeroCRECIResponsavel() {
        return numeroCRECIResponsavel;
    }

    public void setNumeroCRECIResponsavel(int numeroCRECIResponsavel) {
        this.numeroCRECIResponsavel = numeroCRECIResponsavel;
    }
    
    public Long getCodigoImovel() {
        return codigoImovel;
    }

    public void setCodigoImovel(Long codigoImovel) {
        this.codigoImovel = codigoImovel;
    }
    
    public String toString(){
        return getDataDaVenda()
                +" - " + getNomeDoComprador()
                + " - R$" + getValor();
    }
}

