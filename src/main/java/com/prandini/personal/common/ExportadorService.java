package com.prandini.personal.common;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class ExportadorService {

    public static final String UTF8_BOM = new String(new byte[] {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}, StandardCharsets.UTF_8);

    /**
     * Metodo responsavel por criar um arquivo CSV apartir de um STREAM
     * é necessario que o metodo que foi usar essa funcionalidade seja anotado com @Transacional
     *
     *
     * @param stream - Lista de dados recarredos do banco
     * @param getters - Key: Headers do arquivo CSV, Value: Funcao que deve ser utilizada para recuperar a informacao
     * @return - Nome do arquivo gerado
     * @param <S> - Entidade que esta sendo referenciada no stream
     * Exceptions:
     * - CampoNaoEncontradoException
     * - ErroAoCriarArquivoException
     * - ErroInesperadoAoGerarArquivoCSVException
     *
     * Exemplo:
     *   LinkedHashMap<String, Function<Aluno, String>> getters = new LinkedHashMap<>();
     *   getters.put("Id", a -> a.getId().toString());
     *   getters.put("Nome", a -> a.getNome().concat("  Teste"));
     *   getters.put("Idade", a -> a.getIdade().toString());
     *   String nomeArquivo = ExportadorService.exportarDadosParaCSVMap(alunoRepository.findAllStream(), getters);
     */
    public static <S> String exportarDadosParaCSVMap(Stream<S> stream, LinkedHashMap<String, Function<S,String>> getters, String nomeArquivo){
        String[] headers = getters.keySet().toArray(new String[0]);
        headers[0] = UTF8_BOM + headers[0];
        try (FileWriter fileWriter = new FileWriter(nomeArquivo);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(headers).withDelimiter(';'))) {
            stream.forEach(entity -> {
                try {
                    for (Function<S, String> getter : getters.values()) {
                        csvPrinter.print(getter.apply(entity));
                    }
                    csvPrinter.println();
                } catch (Exception e) {
                    throw new RuntimeException("Campo informado não esta presente no registro.", e);
                }
            });
            csvPrinter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar o arquivo. " + e.getMessage(), e);
        }catch (Exception e){
            throw new RuntimeException("Erro ao criar o arquivo. " + e.getMessage() , e);
        }
        return nomeArquivo;
    }
}