/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Probook
 */
public class ResultadoRaioX {

    int id;
    private String descricao, imagem, som, video;
    private String estado, nomePaciente, raioX, resultado, dpp;
    private String ovarios,conclusao;
    private int codigoPaciente, codigoServico,codigoUser;
    // Ecografia morfologia
    private String coracao, anexofetais,orgaogenitas,inferior, superior, colunaVertebral, abdomen,torax,pescocoFetal,face, cranio, segmentocefalico, biometria;

    public ResultadoRaioX() {
        
    }

    public ResultadoRaioX(int id, String descricao, String estado, String imagem, String som, String video, int codigoPaciente, int codigoServico) {
        this.id = id;
        this.descricao = descricao;
        this.estado = estado;
        this.imagem = imagem;
        this.som = som;
        this.video = video;
        this.codigoPaciente = codigoPaciente;
        this.codigoServico = codigoServico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getSom() {
        return som;
    }

    public void setSom(String som) {
        this.som = som;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getRaioX() {
        return raioX;
    }

    public void setRaioX(String raioX) {
        this.raioX = raioX;
    }

    public int getCodigoUser() {
        return codigoUser;
    }

    public void setCodigoUser(int codigoUser) {
        this.codigoUser = codigoUser;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getDpp() {
        return dpp;
    }

    public void setDpp(String dpp) {
        this.dpp = dpp;
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

    public String getCoracao() {
        return coracao;
    }

    public void setCoracao(String coracao) {
        this.coracao = coracao;
    }

    public String getAnexofetais() {
        return anexofetais;
    }

    public void setAnexofetais(String anexofetais) {
        this.anexofetais = anexofetais;
    }

    public String getOrgaogenitas() {
        return orgaogenitas;
    }

    public void setOrgaogenitas(String orgaogenitas) {
        this.orgaogenitas = orgaogenitas;
    }

    public String getInferior() {
        return inferior;
    }

    public void setInferior(String inferior) {
        this.inferior = inferior;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getColunaVertebral() {
        return colunaVertebral;
    }

    public void setColunaVertebral(String colunaVertebral) {
        this.colunaVertebral = colunaVertebral;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getTorax() {
        return torax;
    }

    public void setTorax(String torax) {
        this.torax = torax;
    }

    public String getPescocoFetal() {
        return pescocoFetal;
    }

    public void setPescocoFetal(String pescocoFetal) {
        this.pescocoFetal = pescocoFetal;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getCranio() {
        return cranio;
    }

    public void setCranio(String cranio) {
        this.cranio = cranio;
    }

    public String getSegmentocefalico() {
        return segmentocefalico;
    }

    public void setSegmentocefalico(String segmentocefalico) {
        this.segmentocefalico = segmentocefalico;
    }

    public String getBiometria() {
        return biometria;
    }

    public void setBiometria(String biometria) {
        this.biometria = biometria;
    }
    
    

}
