
package entidades;

import java.util.Date;
import java.util.Objects;


public class Movimentos {
  
     private int idMovimento;
    private int quantidade;
    private int quantidadeAntes;
    private int quantidadeDepois;
    private Date dataEntrada;
    private int idUsuario;
    private int idArmazem;
    private String tipoMovimento;
     private int idproduto;

    public Movimentos() {
    }

    public Movimentos(int idMovimento, int quantidade, int quantidadeAntes, int quantidadeDepois, Date dataEntrada, int idUsuario, int idArmazem, String tipoMovimento, int idproduto) {
        this.idMovimento = idMovimento;
        this.quantidade = quantidade;
        this.quantidadeAntes = quantidadeAntes;
        this.quantidadeDepois = quantidadeDepois;
        this.dataEntrada = dataEntrada;
        this.idUsuario = idUsuario;
        this.idArmazem = idArmazem;
        this.tipoMovimento = tipoMovimento;
        this.idproduto = idproduto;
    }

    public Movimentos(int quantidade, int quantidadeAntes, int quantidadeDepois, Date dataEntrada, int idUsuario, int idArmazem, String tipoMovimento, int idproduto) {
        this.quantidade = quantidade;
        this.quantidadeAntes = quantidadeAntes;
        this.quantidadeDepois = quantidadeDepois;
        this.dataEntrada = dataEntrada;
        this.idUsuario = idUsuario;
        this.idArmazem = idArmazem;
        this.tipoMovimento = tipoMovimento;
        this.idproduto = idproduto;
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeAntes() {
        return quantidadeAntes;
    }

    public void setQuantidadeAntes(int quantidadeAntes) {
        this.quantidadeAntes = quantidadeAntes;
    }

    public int getQuantidadeDepois() {
        return quantidadeDepois;
    }

    public void setQuantidadeDepois(int quantidadeDepois) {
        this.quantidadeDepois = quantidadeDepois;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdArmazem() {
        return idArmazem;
    }

    public void setIdArmazem(int idArmazem) {
        this.idArmazem = idArmazem;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idMovimento);
        hash = 67 * hash + this.quantidade;
        hash = 67 * hash + Objects.hashCode(this.dataEntrada);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movimentos other = (Movimentos) obj;
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (!Objects.equals(this.idMovimento, other.idMovimento)) {
            return false;
        }
        if (!Objects.equals(this.dataEntrada, other.dataEntrada)) {
            return false;
        }
        return true;
    }   

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }
    
    
    
    
    
}
