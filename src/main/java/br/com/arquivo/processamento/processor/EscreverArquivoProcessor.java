package br.com.arquivo.processamento.processor;

import br.com.arquivo.processamento.model.Cliente;
import br.com.arquivo.processamento.model.Relatorio;
import br.com.arquivo.processamento.model.Vendas;
import br.com.arquivo.processamento.model.Vendedor;
import com.opencsv.CSVWriter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EscreverArquivoProcessor implements Processor {

    private static final String DESTINATION_NAME = "_relatorio_dados.dat";
    private static final String SOURCE_FOLDER = "C:\\AgiBank\\data\\out\\";

    public void process(Exchange exchange) throws Exception {
        Relatorio arquivoRelatorio = exchange.getIn().getBody(Relatorio.class);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
        String nomeArquivoRelatorio = dateFormat.format(date) + DESTINATION_NAME;

        String arquivo = SOURCE_FOLDER + nomeArquivoRelatorio;
        CSVWriter writer = new CSVWriter(new FileWriter(arquivo));

        List<String[]> data = new ArrayList<String[]>();
        data.add(new String[] {"QTD CLIENTES", arquivoRelatorio.getQuantidadeClientes().toString()});
        data.add(new String[] {"QTD VENDEDOR", arquivoRelatorio.getQuantidadeVendedor().toString()});
        data.add(new String[] {"ID VENDA MAIS CARA", arquivoRelatorio.getVendaMaisCara().toString()});
        data.add(new String[] {"PIOR VENDEDOR", arquivoRelatorio.getPiorVendedor()});

        writer.writeAll(data);
        writer.close();

        exchange.getIn().setHeader(Exchange.FILE_NAME, nomeArquivoRelatorio);
    }

}
