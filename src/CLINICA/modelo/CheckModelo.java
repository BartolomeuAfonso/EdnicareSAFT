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
public class CheckModelo {

    private int id, codigoPaciente,codigoMedico;
    private String nome, datacheck, datavalidade, descricao, nomeMedico, funcao, resultado;

    public CheckModelo() {
    }

    public CheckModelo(int id, String nome, String datacheck, String datavalidade, String descricao, String nomeMedico) {
        this.id = id;
        this.nome = nome;
        this.datacheck = datacheck;
        this.datavalidade = datavalidade;
        this.descricao = descricao;
        this.nomeMedico = nomeMedico;
    }

    public CheckModelo(String nome, String datacheck, String datavalidade, String descricao, String nomeMedico, String funcao, int codigoPaciente) {
        this.nome = nome;
        this.datacheck = datacheck;
        this.datavalidade = datavalidade;
        this.descricao = descricao;
        this.nomeMedico = nomeMedico;
        this.funcao = funcao;
        this.codigoPaciente = codigoPaciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatacheck() {
        return datacheck;
    }

    public void setDatacheck(String datacheck) {
        this.datacheck = datacheck;
    }

    public String getDatavalidade() {
        return datavalidade;
    }

    public void setDatavalidade(String datavalidade) {
        this.datavalidade = datavalidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }
    

}
