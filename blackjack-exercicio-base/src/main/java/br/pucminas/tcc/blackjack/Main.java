package br.pucminas.tcc.blackjack;

public class Main {

    public static void main(String[] args) {

        /*
         * ATENÇÃO:
         * - Não altere o nome das classes, métodos ou assinaturas já fornecidas.
         *   Os testes automatizados dependem exatamente desses nomes.
         *
         * - Utilize esta classe Main apenas como apoio para testar manualmente
         *   a saída do seu código.
         *
         * - Você pode modificar as cartas e cenários aqui livremente para validar
         *   sua implementação, sem impactar os testes.
         */

        Mao jogador = new Mao();
        jogador.adicionarCarta(new Carta("10C"));
        jogador.adicionarCarta(new Carta("9E"));

        Mao banca = new Mao();
        banca.adicionarCarta(new Carta("8P"));
        banca.adicionarCarta(new Carta("8O"));

        AvaliadorBlackjack avaliador = new AvaliadorBlackjack();
        ResultadoRodada resultado = avaliador.comparar(jogador, banca);

        System.out.println("Pontuação do jogador: " + jogador.pontuacao());
        System.out.println("Pontuação da banca: " + banca.pontuacao());
        System.out.println("Resultado: " + resultado);
    }
}