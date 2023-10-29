import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bingo {
    private List<Cartela> cartelas;
    private List<Integer> numerosSorteados;

    public Bingo() {
        cartelas = new ArrayList<>();
        numerosSorteados = new ArrayList<>();
    }

    public boolean incluirCartela(Cartela cartela) {
        // Verificar se o jogador já possui uma cartela igual
        for (Cartela c : cartelas) {
            if (c.getJogador().equals(cartela.getJogador()) && c.getNumeros().equals(cartela.getNumeros())) {
                return false;
            }
        }
        
        cartelas.add(cartela);
        return true;
    }

    public boolean alterarNomeJogador(String jogadorAntigo, String novoNome) {
        for (Cartela cartela : cartelas) {
            if (cartela.getJogador().equals(jogadorAntigo)) {
                cartela.setJogador(novoNome);
                return true;
            }
        }
        return false;
    }

    public boolean excluirCartela(String jogadorExcluir) {
        for (Cartela cartela : cartelas) {
            if (cartela.getJogador().equals(jogadorExcluir)) {
                cartelas.remove(cartela);
                return true;
            }
        }
        return false;
    }

    public void reinicializarBingo() {
        cartelas.clear();
        numerosSorteados.clear();
    }

    public List<String> verificarNumerosSorteados(int numeroSorteado) {
        numerosSorteados.add(numeroSorteado);
        List<String> jogadoresComNumeroSorteado = new ArrayList<>();

        for (Cartela cartela : cartelas) {
            if (cartela.contemNumero(numeroSorteado) && !jogadoresComNumeroSorteado.contains(cartela.getJogador())) {
                jogadoresComNumeroSorteado.add(cartela.getJogador());
            }
        }

        return jogadoresComNumeroSorteado;
    }

    public void consultarCartelas() {
        for (int i = 0; i < cartelas.size(); i++) {
            System.out.println((i + 1) + ". " + cartelas.get(i).toString());
        }
    }

    public void consultarNumerosSorteados() {
        System.out.println("Números Sorteados (por ordem de sorteio): " + numerosSorteados.toString());
    }

    public void consultarNumerosSorteadosOrdenados() {
        List<Integer> numerosSorteadosOrdenados = new ArrayList<>(numerosSorteados);
        numerosSorteadosOrdenados.sort(null);
        System.out.println("Números Sorteados (em ordem crescente): " + numerosSorteadosOrdenados.toString());
    }

    public void consultarQuantidadeNumerosSorteados() {
        Map<String, Integer> contagemPorJogador = new HashMap<>();

        for (Cartela cartela : cartelas) {
            int contagem = 0;
            for (int numero : numerosSorteados) {
                if (cartela.contemNumero(numero)) {
                    contagem++;
                }
            }
            contagemPorJogador.put(cartela.getJogador(), contagem);
        }

        contagemPorJogador.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " números sorteados"));
    }

    public void consultarNumerosNaoSorteados() {
        Map<String, List<Integer>> numerosNaoSorteadosPorJogador = new HashMap<>();

        for (Cartela cartela : cartelas) {
            List<Integer> numerosNaoSorteados = new ArrayList<>();
            for (int numero : cartela.getNumeros()) {
                if (!numerosSorteados.contains(numero)) {
                    numerosNaoSorteados.add(numero);
                }
            }
            numerosNaoSorteadosPorJogador.put(cartela.getJogador(), numerosNaoSorteados);
        }

        numerosNaoSorteadosPorJogador.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().size() - entry1.getValue().size())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue().toString()));
    }
    public boolean verificarVencedor(String jogador) {
        for (Cartela cartela : cartelas) {
            if (cartela.getJogador().equals(jogador)) {
                List<Integer> numerosCartela = cartela.getNumeros();
                boolean venceu = true;

                // Verificar se todos os números da cartela foram sorteados
                for (int numero : numerosCartela) {
                    if (!numerosSorteados.contains(numero)) {
                        venceu = false;
                        break;
                    }
                }

                if (venceu) {
                    return true;
                }
            }
        }
        return false;
    }
}

