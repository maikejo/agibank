package br.com.arquivo.processamento.router;

import br.com.arquivo.processamento.processor.EscreverArquivoProcessor;
import br.com.arquivo.processamento.processor.LerArquivoProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProcessamentoRouter extends RouteBuilder {

    public static final String ESCREVE_ARQUIVO = "direct:escreveArquivo";
    private static final String SOURCE_FOLDER = "C:\\AgiBank\\data\\in";
    private static final String DESTINATION_FOLDER = "C:\\AgiBank\\data\\out";

    @Override
    public void configure() {
        from("file:" + SOURCE_FOLDER)
            .setProperty("leituraArquivo", body())
            .log(LoggingLevel.INFO, "Recupera dados de leitura do arquivo - ${body} ")
            .process(new LerArquivoProcessor())
            .to(ESCREVE_ARQUIVO);

        from(ESCREVE_ARQUIVO)
            .log(LoggingLevel.INFO, "Realiza escrita do arquivo - ${body} ")
            .process(new EscreverArquivoProcessor())
            .end();
    }

}
