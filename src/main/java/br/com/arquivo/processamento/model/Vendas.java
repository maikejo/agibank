package br.com.arquivo.processamento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Vendas {
    private Long id;
    private Integer saleId;
    private Double itemsPrice;
    private String salesManName;
}
