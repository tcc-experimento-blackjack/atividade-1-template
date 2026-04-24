Digite seu nome: 

# ♠️ Experimento Acadêmico: Blackjack Simplificado

Este repositório contém um projeto de **Blackjack Simplificado**, desenvolvido como parte de um **experimento acadêmico** para um Trabalho de Conclusão de Curso (TCC) em Engenharia de Software.

## 🎯 Objetivo do Experimento

O estudo visa investigar **como a utilização de ferramentas de Inteligência Artificial (IA) impacta o processo de desenvolvimento de software**, com foco especial na **modelagem e implementação orientada a objetos**.

## 🤝 Participação no Experimento

Os participantes serão divididos em dois grupos, cada um com diretrizes específicas para a resolução do exercício:

### Grupo 1: Uso de IA Permitido

Participantes deste grupo estão **autorizados a utilizar ferramentas de Inteligência Artificial** durante o desenvolvimento. Exemplos incluem ChatGPT, tutoriais, artigos técnicos e blogs de programação. Essas ferramentas podem ser empregadas para esclarecer dúvidas, sugerir código, auxiliar na implementação e no raciocínio sobre o problema.

### Grupo 2: Sem Uso de IA Generativa

Participantes deste grupo **não devem utilizar ferramentas de Inteligência Artificial generativa**. No entanto, é **permitida a pesquisa convencional na internet**, utilizando recursos como documentação oficial da linguagem Java, tutoriais, Stack Overflow, blogs de programação e fóruns de discussão. O objetivo é evitar sistemas de IA que gerem respostas automaticamente.

## 📌 Fluxo da Atividade

Siga a sequência de passos abaixo para completar o experimento:

1.  **Diagrama de Classes (UML)**: Modele as classes do sistema com base no problema proposto.
2.  **Formulário Intermediário**: Registre suas percepções sobre a modelagem. 
    -   **Com IA**: https://forms.office.com/r/v7ZQL6R2yT
    -   **Sem IA**: https://forms.office.com/r/ydYjpBvSP6
3.  **Implementação do Código**: Desenvolva a solução em Java utilizando Programação Orientada a Objetos.
4.  **Formulário Final**: Avalie sua experiência, dificuldades e estratégias. 
    -   **Com IA**: https://forms.office.com/r/v7ZQL6R2yT
    -   **Sem IA**: https://forms.office.com/r/ydYjpBvSP6

<img width="500" height="150" alt="image" src="https://github.com/user-attachments/assets/00492f6e-ec2c-4144-b6f6-9d8aa2cc5bc8" />


## 🃏 O Exercício: Blackjack Simplificado

O desafio consiste em implementar um pequeno sistema em **Java**, utilizando **Programação Orientada a Objetos**, que simula uma versão simplificada do jogo Blackjack (21). O objetivo é **comparar duas mãos de cartas** (Jogador e Banca) e determinar o vencedor da rodada.

### Regras Básicas do Jogo

O Blackjack é um jogo de cartas onde o objetivo é alcançar uma pontuação **o mais próxima possível de 21**, sem ultrapassá-lo. Neste experimento, teremos um **Jogador** e uma **Banca**, cada um com uma mão de cartas. A pontuação da mão é a soma dos valores das cartas.

### 🃏 Valor das Cartas

| Carta | Valor |
| :---- | :---- |
| **2 a 10** | Valor numérico da carta |
| **J, Q, K** | 10 pontos cada |
| **A (Ás)** | 11 pontos (ou 1, se a mão estourar) |

#### Representação das Cartas

Cada carta é representada por uma notação curta: `VALOR + NAIPE`.

| Símbolo | Naipe   |
| :------ | :------ |
| C       | Copas   |
| O       | Ouros   |
| P       | Paus    |
| E       | Espadas |

**Exemplos:**

| Notação | Carta          |
| :------ | :------------- |
| 7C      | 7 de Copas     |
| 10O     | 10 de Ouros    |
| QE      | Dama de Espadas |
| AP      | Ás de Paus     |

**Exemplo de Pontuação:** `10H + 9S = 19 pontos`

![Exemplo de Mão de Cartas](https://github.com/user-attachments/assets/303d08cf-6cf5-464e-9754-2b94e75f24b1)

### Estouro (Bust)

Se a soma das cartas de uma mão **ultrapassar 21 pontos**, a mão **estoura** e perde automaticamente. Exemplo: `10 + 9 + 5 = 24` (estourou).

### Regras para Determinar o Vencedor

Após calcular a pontuação de ambas as mãos:

*   **Vitória do Jogador**: O jogador tem maior pontuação e nenhuma das mãos estourou.
*   **Vitória da Banca**: A banca tem maior pontuação, ou o jogador estoura.
*   **Empate**: Ambas as mãos têm a mesma pontuação, ou ambas estouram.

## ✅ Exemplos de Saída Esperada

A seguir, alguns cenários e seus resultados esperados:

### 🟢 Exemplo 1: Vitória do Jogador

*   **Jogador**: `10C, 9O` → 19 pontos
*   **Banca**: `8P, 7E` → 15 pontos
*   **Resultado esperado**: `JOGADOR_VENCE`

### 🔵 Exemplo 2: Vitória da Banca

*   **Jogador**: `10C, 9O` → 19 pontos
*   **Banca**: `10P, QE` → 20 pontos
*   **Resultado esperado**: `BANCA_VENCE`

### 🔴 Exemplo 3: Jogador Estoura

*   **Jogador**: `10C, 9O, 5P` → 24 pontos (estourou)
*   **Banca**: `7E, 8C` → 15 pontos
*   **Resultado esperado**: `BANCA_VENCE`

### 🟡 Exemplo 4: Empate

*   **Jogador**: `10C, 9O` → 19 pontos
*   **Banca**: `10P, 9E` → 19 pontos
*   **Resultado esperado**: `EMPATE`

## 🏗️ Estrutura do Projeto

O projeto é composto pelas seguintes classes, que devem ter sua implementação completada:

*   **`Carta`**: Representa uma carta individual do baralho.
*   **`Mao`**: Representa uma mão de cartas de um participante.
*   **`AvaliadorBlackjack`**: Responsável por comparar as mãos e determinar o resultado da rodada.
*   **`ResultadoRodada`**: Uma enumeração que define os possíveis resultados da rodada (Vitória do Jogador, Vitória da Banca, Empate).

### Nomes Obrigatórios (Case Sensitive)

Para garantir a compatibilidade com os testes automatizados, os seguintes nomes devem ser utilizados exatamente como especificado:

#### Classes

*   `Carta`
*   `Mao`
*   `AvaliadorBlackjack`
*   `ResultadoRodada` (enum)

#### Métodos

*   **`Carta`**
    *   `Carta(String codigo)`
    *   `getValor()`
    *   `getNaipe()`
    *   `getValorNumerico()`

*   **`Mao`**
    *   `Mao(List<Carta> cartas)`
    *   `pontuacao()`
    *   `estourou()`

*   **`AvaliadorBlackjack`**
    *   `comparar(Mao jogador, Mao banca)`

#### Enum `ResultadoRodada`

*   `VITORIA_JOGADOR`
*   `VITORIA_BANCA`
*   `EMPATE`

## 🧪 Execução dos Testes

O projeto inclui testes automatizados para verificar o comportamento esperado do sistema. Para executá-los, utilize o seguinte comando:

```bash
mvn test
```

## ⏱️ Tempo Estimado

O exercício foi projetado para ser concluído em aproximadamente **60 a 90 minutos**.

## 📦 Entrega

Ao final da atividade, espera-se a entrega de:

*   O código-fonte implementado.
*   O diagrama de classes UML correspondente à solução.

## 🙏 Agradecimento

Agradecemos imensamente sua participação neste experimento. Sua contribuição é fundamental para a pesquisa sobre o **impacto das ferramentas de Inteligência Artificial no desenvolvimento de software**.
