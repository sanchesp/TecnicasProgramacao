import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Path path = Paths.get("campeonato-brasileiro-full.csv");

        Stream<String> stream = null;

        //Exercicio8
        try {
            stream = Files.lines(path);
            String vitoriaMandante = String.valueOf(stream
                    .skip(1)
                    .map(table -> mapToTabela(table))
                    .filter((mandante) -> mandante.getMandante().equals(mandante.getVencedor()))
                    .count());
            System.out.println(vitoriaMandante);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        //Exercicio9
        try {
            stream = Files.lines(path);
            String vitoriaVisitante = String.valueOf(stream
                    .skip(1)
                    .map(table -> mapToTabela(table))
                    .filter((visitante) -> visitante.getVisitante().equals(visitante.getVencedor()))
                    .count());
                        System.out.println(vitoriaVisitante);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }



    }





    //Exercicio1
    private static void quantidadePartidas(Path path) throws IOException {
        Stream<String> stream = Files.lines(path);
        int soma = (int) stream
                .skip(1)
                .map(table -> mapToTabela(table))
                .count();
        System.out.println("De 2003 à 2021 foram realizadas " + soma + " partidas");
    }
    //Exercicio3
    private static void quantidadePartidas10E15(Path path) throws IOException {
        Stream<String> stream;
        stream = Files.lines(path);
        int soma = (int) stream
                .skip(1)
                .map(table -> mapToTabela(table))
                .filter(table -> table.getData().getYear() >= 2010)
                .filter(table -> table.getData().getYear() <= 2015)
                .count();
        System.out.println("De 2010 à 2015 foram realizadas " + soma + " partidas");
    }
    //Exercicio4
    private static void maxGols(Path path) throws IOException {
        Stream<String> stream = Files.lines(path);
        int maxGols = (int) stream
                .skip(1)
                .map(table -> mapToTabela(table))
                .mapToInt(gols -> gols.getMandantePlacar() + gols.getVisitantePlacar())
                .max().getAsInt();
        System.out.println(maxGols);
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
