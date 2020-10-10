package br.com.arquivo.processamento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Cliente implements Serializable {
    private Long id;
    private String cnpj;
    private String name;
}
