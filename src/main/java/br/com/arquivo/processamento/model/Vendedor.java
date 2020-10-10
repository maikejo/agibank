package br.com.arquivo.processamento.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Vendedor {
    private Long id;
    private String cpf;
    private String name;
    private Double salary;
}
