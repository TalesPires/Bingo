import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class JogoBingo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bingo bingo = new Bingo();
        Random random = new Random();
        Set<Integer> numerosSorteados = new HashSet<>();
        List<String> vencedores = new ArrayList<>();

        while (true) {
            System.out.println("\n === BINGO ===");
            System.out.println("1. Incluir Cartela");
            System.out.println("2. Alterar Nome do Jogador");
            System.out.println("3. Excluir Cartela");
            System.out.println("4. Reinicializar Bingo");
            System.out.println("5. Sortear Próximo Número");
            System.out.println("6. Consultas");
            System.out.println("7. Sair");
            System.out.println("Escolha uma opção: \n");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa a quebra de linha

            switch (opcao) {
                case 1:
                    if(numerosSorteados.size() > 0){
                        System.out.println("\nNão é possível adicionar uma cartela após o inicio do sorteio " +
                        "somente a alteração de nome de uma carteira já existente é possível\n");
                    }else{   
                    // Incluir Cartela
                    System.out.print("Nome do Jogador: ");
                    String nome = scanner.nextLine();
                    Cartela cartela = new Cartela(nome);
                    if (bingo.incluirCartela(cartela)) {
                        System.out.println("Cartela incluída com sucesso.");
                    } else {
                        System.out.println("Jogador já possui uma cartela ou conjunto de números idênticos.");
                    }
                    }
                    break;
                case 2:
                    // Alterar Nome do Jogador
                    System.out.print("Nome do Jogador a ser alterado: ");
                    String jogadorAntigo = scanner.nextLine();
                    System.out.print("Novo Nome do Jogador: ");
                    String novoNome = scanner.nextLine();
                    if (bingo.alterarNomeJogador(jogadorAntigo, novoNome)) {
                        System.out.println("Nome do jogador alterado com sucesso.");
                    } else {
                        System.out.println("Jogador não encontrado ou novo nome já existe.");
                    }
                    break;
                case 3:
                    // Excluir Cartela
                    if(numerosSorteados.size() > 0){
                        System.out.println("\nNão é possível adicionar uma cartela após o inicio do sorteio " +
                        "somente a alteração de nome de uma carteira já existente é possível\n");
                    }else{ 
                    
                    System.out.print("Nome do Jogador a ser excluído: ");
                    String jogadorExcluir = scanner.nextLine();
                    if (bingo.excluirCartela(jogadorExcluir)) {
                        System.out.println("Cartela excluída com sucesso.");
                    } else {
                        System.out.println("Jogador não encontrado.");
                    }
                    }
                    break;
                case 4:
                    // Reinicializar Bingo
                    bingo.reinicializarBingo();
                    numerosSorteados.clear();
                    vencedores.clear();
                    System.out.println("Bingo reinicializado.");
                    break;
                case 5:
                    // Sortear Próximo Número
                    if (numerosSorteados.size() < 99) {
                        int numeroSorteado;
                        do {
                            numeroSorteado = random.nextInt(99) + 1;
                        } while (numerosSorteados.contains(numeroSorteado));
                        numerosSorteados.add(numeroSorteado);

                        List<String> jogadoresComNumeroSorteado = bingo.verificarNumerosSorteados(numeroSorteado);
                        if (!jogadoresComNumeroSorteado.isEmpty()) {
                            System.out.println("Número sorteado: " + numeroSorteado);
                            System.out.println("Jogador(es) com este número: " + jogadoresComNumeroSorteado);   
                            // Verificar se há vencedores
                            for (String jogador : jogadoresComNumeroSorteado) {
                                if (!vencedores.contains(jogador) && bingo.verificarVencedor(jogador)) {
                                    vencedores.add(jogador);
                                    System.out.println("Vencedor: " + jogador);
                                }    
                            }   
                        } 
                        
                    System.out.println("Número sorteado: " + numeroSorteado);

                    if (numerosSorteados.size() == 99){
                        System.out.println("Todos os números foram sorteados");
                        }
                    } 
                    if (!vencedores.isEmpty()) {
                            System.out.println("Jogo encerrado! Vencedor(es): " + vencedores);
                            System.out.print("Deseja reinicializar o jogo (S/N)? Você pode conferir os dados do jogo atual se não reinicializa-lo: ");
                            String resposta = scanner.nextLine();
                            if (resposta.equalsIgnoreCase("S")) {
                                bingo.reinicializarBingo();
                                numerosSorteados.clear();
                                vencedores.clear();
                                System.out.println("Bingo reinicializado.");
                            } 
                        }
                    
                    break;
                case 6:
                System.out.println("=== Consultas ===");
                System.out.println("1. Nomes dos Jogadores e suas Cartelas (por ordem de inclusão)");
                System.out.println("2. Números Sorteados (por ordem de sorteio)");
                System.out.println("3. Números Sorteados (em ordem crescente)");
                System.out.println("4. Quantidade de Números Sorteados por Jogador (em ordem decrescente)");
                System.out.println("5. Números Não Sorteados por Jogador (em ordem decrescente)");
                System.out.print("Escolha uma opção de consulta: \n");
                int opcaoConsulta = scanner.nextInt();
                scanner.nextLine(); // Limpa a quebra de linha

                switch (opcaoConsulta) {
                    case 1:
                        bingo.consultarCartelas();
                        break;
                    case 2:
                        bingo.consultarNumerosSorteados();
                        break;
                    case 3:
                        bingo.consultarNumerosSorteadosOrdenados();
                        break;
                    case 4:
                        bingo.consultarQuantidadeNumerosSorteados();
                        break;
                    case 5:
                        bingo.consultarNumerosNaoSorteados();
                        break;
                    default:
                        System.out.println("Opção de consulta inválida.");
                        break;
                    }
                    break;
                case 7:
                    // Sair
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
