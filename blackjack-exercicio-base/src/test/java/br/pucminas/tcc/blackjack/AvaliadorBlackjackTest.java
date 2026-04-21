package br.pucminas.tcc.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvaliadorBlackjackTest {

    // =====================================================================
    // TESTES DE CARTA
    // =====================================================================

    @Test
    void deveInterpretarCartaNumericaCorretamente() {
        Carta carta = new Carta("7C");

        assertEquals("7", carta.getValor(),
                "Erro em getValor() para 7C. Esperado: 7 | Obtido: " + carta.getValor());
        assertEquals("C", carta.getNaipe(),
                "Erro em getNaipe() para 7C. Esperado: C | Obtido: " + carta.getNaipe());
        assertEquals(7, carta.getValorNumerico(),
                "Erro em getValorNumerico() para 7C. Esperado: 7 | Obtido: " + carta.getValorNumerico());
    }

    @Test
    void deveInterpretarCartaDezCorretamente() {
        Carta carta = new Carta("10O");

        assertEquals("10", carta.getValor(),
                "Erro em getValor() para 10O. Esperado: 10 | Obtido: " + carta.getValor());
        assertEquals("O", carta.getNaipe(),
                "Erro em getNaipe() para 10O. Esperado: O | Obtido: " + carta.getNaipe());
        assertEquals(10, carta.getValorNumerico(),
                "Erro em getValorNumerico() para 10O. Esperado: 10 | Obtido: " + carta.getValorNumerico());
    }

    @Test
    void figurasDevemValerDez() {
        Carta j = new Carta("JC");
        Carta q = new Carta("QO");
        Carta k = new Carta("KE");

        assertEquals(10, j.getValorNumerico(),
                "Erro para JC. Esperado: 10 | Obtido: " + j.getValorNumerico());
        assertEquals(10, q.getValorNumerico(),
                "Erro para QO. Esperado: 10 | Obtido: " + q.getValorNumerico());
        assertEquals(10, k.getValorNumerico(),
                "Erro para KE. Esperado: 10 | Obtido: " + k.getValorNumerico());
    }

    @Test
    void asDeveValerOnzeIsoladamente() {
        Carta as = new Carta("AP");

        assertEquals(11, as.getValorNumerico(),
                "Erro para AP. Esperado: 11 | Obtido: " + as.getValorNumerico());
    }

    @Test
    void deveLancarExcecaoParaCartaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> new Carta("1C"),
                "Esperado IllegalArgumentException para carta inválida '1C'.");
        assertThrows(IllegalArgumentException.class, () -> new Carta("10X"),
                "Esperado IllegalArgumentException para naipe inválido '10X'.");
        assertThrows(IllegalArgumentException.class, () -> new Carta(""),
                "Esperado IllegalArgumentException para string vazia.");
    }

    // =====================================================================
    // TESTES DE MÃO — PONTUAÇÃO SIMPLES (SEM ÁS)
    // =====================================================================

    @Test
    void deveSomarPontuacaoSemAs() {
        // 10 + 5 + 6 = 21
        Mao mao = new Mao(List.of(
                new Carta("10C"),
                new Carta("5O"),
                new Carta("6E")
        ));

        assertEquals(21, mao.pontuacao(),
                "Mão [10C, 5O, 6E] deveria somar 21.");
        assertFalse(mao.estourou(),
                "Mão com 21 pontos não deve ser considerada estourada.");
    }

    @Test
    void deveDetectarEstouroSemAs() {
        // 10 + 9 + 5 = 24 → estouro
        Mao mao = new Mao(List.of(
                new Carta("10C"),
                new Carta("9O"),
                new Carta("5P")
        ));

        assertTrue(mao.estourou(),
                "Mão [10C, 9O, 5P] com 24 pontos deveria ser considerada estourada.");
        assertEquals(24, mao.pontuacao(),
                "Mão [10C, 9O, 5P] deveria somar 24.");
    }

    // =====================================================================
    // TESTES DE MÃO — ÁS VALENDO 11 (sem estouro)
    // =====================================================================

    @Test
    void asDeveValerOnzeQuandoNaoEstoura() {
        // A(11) + 10 = 21 → Ás vale 11
        Mao mao = new Mao(List.of(
                new Carta("AC"),
                new Carta("10O")
        ));

        assertEquals(21, mao.pontuacao(),
                "Mão [AC, 10O]: Ás deve valer 11, totalizando 21.");
        assertFalse(mao.estourou(),
                "Mão com 21 pontos não deve ser considerada estourada.");
    }

    @Test
    void asDeveValerOnzeComCartasBaixas() {
        // A(11) + 5 + 3 = 19 → Ás vale 11
        Mao mao = new Mao(List.of(
                new Carta("AE"),
                new Carta("5C"),
                new Carta("3O")
        ));

        assertEquals(19, mao.pontuacao(),
                "Mão [AE, 5C, 3O]: Ás deve valer 11, totalizando 19.");
        assertFalse(mao.estourou(),
                "Mão com 19 pontos não deve ser considerada estourada.");
    }

    // =====================================================================
    // TESTES DE MÃO — ÁS VALENDO 1 (para evitar estouro)
    // =====================================================================

    @Test
    void asDeveValerUmParaEvitarEstouro() {
        // A(11) + 10 + 5 = 26 → estoura; A(1) + 10 + 5 = 16 → válido
        Mao mao = new Mao(List.of(
                new Carta("AP"),
                new Carta("10C"),
                new Carta("5O")
        ));

        assertEquals(16, mao.pontuacao(),
                "Mão [AP, 10C, 5O]: Ás deve valer 1 para evitar estouro, totalizando 16.");
        assertFalse(mao.estourou(),
                "Mão com Ás ajustado para 1 não deve ser considerada estourada.");
    }

    @Test
    void asDeveValerUmComDuasCartas() {
        // A(11) + 9 + 8 = 28 → estoura; A(1) + 9 + 8 = 18 → válido
        Mao mao = new Mao(List.of(
                new Carta("AC"),
                new Carta("9E"),
                new Carta("8P")
        ));

        assertEquals(18, mao.pontuacao(),
                "Mão [AC, 9E, 8P]: Ás deve valer 1 para evitar estouro, totalizando 18.");
        assertFalse(mao.estourou(),
                "Mão com Ás ajustado para 1 não deve ser considerada estourada.");
    }

    @Test
    void asDeveValerUmMesmoComFigura() {
        // A(11) + K(10) + 5 = 26 → estoura; A(1) + K(10) + 5 = 16 → válido
        Mao mao = new Mao(List.of(
                new Carta("AO"),
                new Carta("KC"),
                new Carta("5E")
        ));

        assertEquals(16, mao.pontuacao(),
                "Mão [AO, KC, 5E]: Ás deve valer 1 para evitar estouro, totalizando 16.");
        assertFalse(mao.estourou(),
                "Mão com Ás ajustado para 1 não deve ser considerada estourada.");
    }

    @Test
    void doisAsesUmDeveValerOnzeEOutroUm() {
        // A(11) + A(11) = 22 → estoura; A(11) + A(1) = 12 → válido
        Mao mao = new Mao(List.of(
                new Carta("AC"),
                new Carta("AO")
        ));

        assertEquals(12, mao.pontuacao(),
                "Mão [AC, AO]: Um Ás deve valer 11 e o outro 1, totalizando 12.");
        assertFalse(mao.estourou(),
                "Mão com dois Ases não deve ser considerada estourada.");
    }

    @Test
    void maoEstouraQuandoAsNaoPodeEvitar() {
        // A(1) + 10 + 9 + 8 = 28 → mesmo com Ás = 1, estoura
        Mao mao = new Mao(List.of(
                new Carta("AP"),
                new Carta("10C"),
                new Carta("9O"),
                new Carta("8E")
        ));

        assertTrue(mao.estourou(),
                "Mão [AP, 10C, 9O, 8E]: mesmo com Ás = 1, deve ser considerada estourada.");
        assertEquals(28, mao.pontuacao(),
                "Mão [AP, 10C, 9O, 8E] com Ás = 1 deveria somar 28.");
    }

    // =====================================================================
    // TESTES DO AVALIADOR — VITÓRIA DO JOGADOR
    // =====================================================================

    @Test
    void jogadorVenceComMaiorPontuacaoSemEstouro() {
        // Jogador: 10 + 9 = 19 | Banca: 8 + 8 = 16
        Mao jogador = new Mao(List.of(new Carta("10C"), new Carta("9E")));
        Mao banca   = new Mao(List.of(new Carta("8O"),  new Carta("8P")));

        ResultadoRodada resultado = new AvaliadorBlackjack().comparar(jogador, banca);

        assertEquals(ResultadoRodada.VITORIA_JOGADOR, resultado,
                "Jogador [10C, 9E=19] deve vencer banca [8O, 8P=16].");
    }

    @Test
    void jogadorVenceQuandoBancaEstoura() {
        // Jogador: 10 + 8 = 18 | Banca: 10 + 8 + 5 = 23 (estouro)
        Mao jogador = new Mao(List.of(new Carta("10C"), new Carta("8E")));
        Mao banca   = new Mao(List.of(new Carta("10O"), new Carta("8P"), new Carta("5C")));

        ResultadoRodada resultado = new AvaliadorBlackjack().comparar(jogador, banca);

        assertEquals(ResultadoRodada.VITORIA_JOGADOR, resultado,
                "Jogador [10C, 8E=18] deve vencer quando banca [10O, 8P, 5C] estoura.");
    }

    @Test
    void jogadorVenceComBlackjack() {
        // Jogador: A(11) + 10 = 21 | Banca: 10 + 9 = 19
        Mao jogador = new Mao(List.of(new Carta("AC"),  new Carta("10O")));
        Mao banca   = new Mao(List.of(new Carta("10C"), new Carta("9E")));

        ResultadoRodada resultado = new AvaliadorBlackjack().comparar(jogador, banca);

        assertEquals(ResultadoRodada.VITORIA_JOGADOR, resultado,
                "Jogador com blackjack [AC, 10O=21] deve vencer banca [10C, 9E=19].");
    }

    @Test
    void jogadorVenceComAsAjustadoParaUm() {
        // Jogador: A(1) + 10 + 5 = 16 | Banca: 10 + 4 = 14
        Mao jogador = new Mao(List.of(new Carta("AP"),  new Carta("10C"), new Carta("5O")));
        Mao banca   = new Mao(List.of(new Carta("10E"), new Carta("4P")));

        ResultadoRodada resultado = new AvaliadorBlackjack().comparar(jogador, banca);

        assertEquals(ResultadoRodada.VITORIA_JOGADOR, resultado,
                "Jogador [AP, 10C, 5O=16 com Ás=1] deve vencer banca [10E, 4P=14].");
    }

    // =====================================================================
    // TESTES DO AVALIADOR — VITÓRIA DA BANCA
    // =====================================================================

    @Test
    void bancaVenceComMaiorPontuacaoSemEstouro() {
        // Jogador: 8 + 7 = 15 | Banca: 10 + 9 = 19
        Mao jogador = new Mao(List.of(new Carta("8C"),  new Carta("7E")));
        Mao banca   = new Mao(List.of(new Carta("10O"), new Carta("9P")));

        ResultadoRodada resultado = new AvaliadorBlackjack().comparar(jogador, banca);

        assertEquals(ResultadoRodada.VITORIA_BANCA, resultado,
                "Banca [10O, 9P=19] deve vencer jogador [8C, 7E=15].");
    }

    @Test
    void bancaVenceQuandoJogadorEstoura() {
        // Jogador: 10 + 9 + 5 = 24 (estouro) | Banca: 8 + 8 = 16
        Mao jogador = new Mao(List.of(new Carta("10C"), new Carta("9E"), new Carta("5O")));
        Mao banca   = new Mao(List.of(new Carta("8O"),  new Carta("8P")));

        ResultadoRodada resultado = new AvaliadorBlackjack().comparar(jogador, banca);

        assertEquals(ResultadoRodada.VITORIA_BANCA, resultado,
                "Banca deve vencer quando jogador [10C, 9E, 5O] estoura.");
    }

    @Test
    void bancaVenceComBlackjack() {
        // Jogador: 10 + 9 = 19 | Banca: A(11) + 10 = 21
        Mao jogador = new Mao(List.of(new Carta("10C"), new Carta("9E")));
        Mao banca   = new Mao(List.of(new Carta("AO"),  new Carta("10P")));

        ResultadoRodada resultado = new AvaliadorBlackjack().comparar(jogador, banca);

        assertEquals(ResultadoRodada.VITORIA_BANCA, resultado,
                "Banca com blackjack [AO, 10P=21] deve vencer jogador [10C, 9E=19].");
    }

    // =====================================================================
    // TESTES DO AVALIADOR — EMPATE
    // =====================================================================

    @Test
    void empatePontuacoesIguaisSemAs() {
        // Jogador: 10 + 8 = 18 | Banca: 9 + 9 = 18
        Mao jogador = new Mao(List.of(new Carta("10C"), new Carta("8E")));
        Mao banca   = new Mao(List.of(new Carta("9O"),  new Carta("9P")));

        ResultadoRodada resultado = new AvaliadorBlackjack().comparar(jogador, banca);

        assertEquals(ResultadoRodada.EMPATE, resultado,
                "Empate esperado: jogador [10C, 8E=18] e banca [9O, 9P=18].");
    }

    @Test
    void empateQuandoAmbosEstouram() {
        // Jogador: 10 + 8 + 5 = 23 | Banca: 9 + 9 + 8 = 26
        Mao jogador = new Mao(List.of(new Carta("10C"), new Carta("8E"), new Carta("5O")));
        Mao banca   = new Mao(List.of(new Carta("9O"),  new Carta("9P"), new Carta("8C")));

        ResultadoRodada resultado = new AvaliadorBlackjack().comparar(jogador, banca);

        assertEquals(ResultadoRodada.EMPATE, resultado,
                "Empate esperado quando ambos estouraram.");
    }

    @Test
    void empateComBlackjackDosLados() {
        // Jogador: A(11) + 10 = 21 | Banca: A(11) + K(10) = 21
        Mao jogador = new Mao(List.of(new Carta("AC"),  new Carta("10O")));
        Mao banca   = new Mao(List.of(new Carta("AE"),  new Carta("KC")));

        ResultadoRodada resultado = new AvaliadorBlackjack().comparar(jogador, banca);

        assertEquals(ResultadoRodada.EMPATE, resultado,
                "Empate esperado: ambos com blackjack (21).");
    }

    @Test
    void empateComAsAjustadoEmAmbosSLados() {
        // Jogador: A(1) + 10 + 6 = 17 | Banca: A(1) + 9 + 7 = 17
        Mao jogador = new Mao(List.of(new Carta("AC"),  new Carta("10O"), new Carta("6E")));
        Mao banca   = new Mao(List.of(new Carta("AO"),  new Carta("9C"),  new Carta("7P")));

        ResultadoRodada resultado = new AvaliadorBlackjack().comparar(jogador, banca);

        assertEquals(ResultadoRodada.EMPATE, resultado,
                "Empate esperado: jogador [AC, 10O, 6E=17 Ás=1] e banca [AO, 9C, 7P=17 Ás=1].");
    }
}