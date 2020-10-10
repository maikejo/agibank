package br.com.arquivo.processamento.processor;

import br.com.arquivo.processamento.model.Cliente;
import br.com.arquivo.processamento.model.Relatorio;
import br.com.arquivo.processamento.model.Vendas;
import br.com.arquivo.processamento.model.Vendedor;
import br.com.arquivo.processamento.util.LerDadosUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LerArquivoProcessor implements Processor {

    public static final String TIPO_DADO_VENDEDOR = "001";
    public static final String TIPO_DADO_CLIENTE = "002";
    public static final String TIPO_DADO_VENDAS = "003";

    public void process(Exchange exchange) throws Exception {
        File file = exchange.getIn().getBody(File.class);

        List<Cliente> listaClientes = new ArrayList<>();
        List<Vendedor> listaVendedores = new ArrayList<>();
        List<Vendas> listaVendas = new ArrayList<>();
        String linha;

        BufferedReader csvReader = new BufferedReader(new FileReader(file));
        while ((linha = csvReader.readLine()) != null) {
            String[] data = linha.split("ç");

            String id = data[0];

            if (id.equals(TIPO_DADO_VENDEDOR)) {
                Vendedor vendedor = null;
                vendedor = LerDadosUtil.lerDadosVendedor(data);
                listaVendedores.add(vendedor);
            } else if (id.equals(TIPO_DADO_CLIENTE)) {
                Cliente cliente = null;
                cliente = LerDadosUtil.lerDadosCliente(data);
                listaClientes.add(cliente);
            } else {
                Vendas vendas = null;
                vendas = LerDadosUtil.lerDadosVendas(data);
                listaVendas.add(vendas);
            }
        }
        csvReader.close();

        Integer vendaMaisCara = listaVendas.stream().map(valor -> valor.getSaleId()).max(Comparator.comparing(Integer::valueOf)).get();

        Relatorio relatorio = Relatorio.builder()
                .quantidadeClientes(listaClientes.size())
                .quantidadeVendedor(listaVendedores.size())
                .vendaMaisCara(vendaMaisCara)
                .piorVendedor("Pedro").build();

        exchange.getMessage().setBody(relatorio);
    }

}
