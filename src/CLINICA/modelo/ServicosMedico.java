/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

import java.math.BigDecimal;

/**
 *
 * @author El Router
 */
public class ServicosMedico {
    
    private Long idServico;
    
    private int idEspecialidade;

    private String designacao;
    private BigDecimal impostoServico;
    private BigDecimal valorCobrar;

    public ServicosMedico() {
    }
    
    

    public ServicosMedico(Long idServico, int idEspecialidade, String designacao, BigDecimal impostoServico, BigDecimal valorCobrar) {
        this.idServico = idServico;
        this.idEspecialidade = idEspecialidade;
        this.designacao = designacao;
        this.impostoServico = impostoServico;
        this.valorCobrar = valorCobrar;
    }

    public ServicosMedico(int idEspecialidade, String designacao, BigDecimal impostoServico, BigDecimal valorCobrar) {
        this.idEspecialidade = idEspecialidade;
        this.designacao = designacao;
        this.impostoServico = impostoServico;
        this.valorCobrar = valorCobrar;
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public BigDecimal getImpostoServico() {
        return impostoServico;
    }

    public void setImpostoServico(BigDecimal impostoServico) {
        this.impostoServico = impostoServico;
    }

    public BigDecimal getValorCobrar() {
        return valorCobrar;
    }

    public void setValorCobrar(BigDecimal valorCobrar) {
        this.valorCobrar = valorCobrar;
    }
    
   
    
    
}
