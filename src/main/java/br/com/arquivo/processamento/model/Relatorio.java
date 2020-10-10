package br.com.arquivo.processamento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Relatorio {
    private Integer quantidadeClientes;
    private Integer quantidadeVendedor;
    private Integer vendaMaisCara;
    private String piorVendedor;
}
