import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("campeonato-brasileiro-full.csv");

        quantidadePartidas(path);

    }



    //Exercicio1
    private static void quantidadePartidas(Path path) throws IOException {
        Stream<String> stream;
        stream = Files.lines(path);
                int soma = (int) stream
                .skip(1)
                .map(table -> mapToTabela(table))
                .count();
        System.out.println("De 2003 à 2021 foram realizadas " + soma + " partidas");
    }

    private static ResultadosCampeonato mapToTabela (String resultadosCampeonato){

        String [] coluna = resultadosCampeonato.split(",");

        ResultadosCampeonato tabela = new ResultadosCampeonato();
        tabela.setRodada(Integer.parseInt(coluna [1]));
        tabela.setData(LocalDate.parse(coluna[2]));
        tabela.setMandante(coluna[5]);
        tabela.setVisitante(coluna[6]);
        tabela.setVencedor(coluna[11]);
        tabela.setArena(coluna[12]);
        tabela.setMandantePlacar(Integer.parseInt(coluna[13]));
        tabela.setVisitantePlacar(Integer.parseInt(coluna[14]));
        tabela.setMandanteEstado(coluna[15]);
        tabela.setVisitanteEstado(coluna[16]);
        tabela.setVencedorEstado(coluna[17]);

        return tabela;
    }
}
