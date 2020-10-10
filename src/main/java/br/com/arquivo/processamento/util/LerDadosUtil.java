package br.com.arquivo.processamento.util;

import br.com.arquivo.processamento.model.Cliente;
import br.com.arquivo.processamento.model.Vendas;
import br.com.arquivo.processamento.model.Vendedor;

public class LerDadosUtil {

    public static Vendedor lerDadosVendedor(String[] metadata) {
        long id = Long.parseLong(metadata[0]);
        String cnpj = metadata[1];
        String name = metadata[2];
        double salary = Double.parseDouble( metadata[3]);
        return new Vendedor(id, cnpj, name,salary);
    }

    public static Cliente lerDadosCliente(String[] metadata) {
        long id = Long.parseLong(metadata[0]);
        String cnpj = metadata[1];
        String name = metadata[2];
        return new Cliente(id, cnpj, name);
    }

    public static Vendas lerDadosVendas(String[] metadata) {
        long id = Long.parseLong(metadata[0]);
        Integer saleId = Integer.parseInt(metadata[1]);
        double itemsPrice = Double.parseDouble( metadata[2]);
        String salesManName = metadata[3];
        return new Vendas(id, saleId, itemsPrice,salesManName);
    }
}
