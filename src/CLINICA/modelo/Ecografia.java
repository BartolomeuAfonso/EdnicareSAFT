/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author bafonso
 */
public class Ecografia {
    private String designacao,ovarios,conclusao,cranio,coracao,face,abdomen,anexoFetais,biometria,ddp;
    private int codigoProduto;

    public Ecografia(String designacao, String ovarios, String conclusao, String cranio, String coracao, String face, String abdomen, String anexoFetais, String biometria, int codigoProduto) {
        this.designacao = designacao;
        this.ovarios = ovarios;
        this.conclusao = conclusao;
        this.cranio = cranio;
        this.coracao = coracao;
        this.face = face;
        this.abdomen = abdomen;
        this.anexoFetais = anexoFetais;
        this.biometria = biometria;
        this.codigoProduto = codigoProduto;
    }

    public Ecografia() {
    }
    

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getOvarios() {
        return ovarios;
    }

    public void setOvarios(String ovarios) {
        this.ovarios = ovarios;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public String getCranio() {
        return cranio;
    }

    public void setCranio(String cranio) {
        this.cranio = cranio;
    }

    public String getCoracao() {
        return coracao;
    }

    public void setCoracao(String coracao) {
        this.coracao = coracao;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getAnexoFetais() {
        return anexoFetais;
    }

    public void setAnexoFetais(String anexoFetais) {
        this.anexoFetais = anexoFetais;
    }

    public String getBiometria() {
        return biometria;
    }

    public void setBiometria(String biometria) {
        this.biometria = biometria;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDdp() {
        return ddp;
    }

    public void setDdp(String ddp) {
        this.ddp = ddp;
    }
    
    
    
    
}
