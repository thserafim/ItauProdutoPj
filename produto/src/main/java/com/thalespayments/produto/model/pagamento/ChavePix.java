package com.thalespayments.produto.model.pagamento;


import jakarta.persistence.*;

@Entity
@Table(name = "chaves_pix")
public class ChavePix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoChavePix tipo;

    @Column(nullable = false, unique = true)
    private String valor;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public TipoChavePix getTipo(){
        return tipo;
    }
    public void setTipo(TipoChavePix tipo){
        this.tipo = tipo;
    }
    public String getValor(){
        return valor;
    }
    public void setValor(String valor){
        this.valor = valor;
    }
}
