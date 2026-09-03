# Prova de Conceito (PoC) - Cálculo Dinâmico com Spring EL (SpEL)

Esta Prova de Conceito (PoC) demonstra uma arquitetura orientada a metadados para motores de cálculo tributário (como a Declaração de Regimes Específicos - DeRE). A solução desacopla as fórmulas matemáticas do código-fonte (nível DDL), permitindo que sejam cadastradas, alteradas e avaliadas dinamicamente em tempo de execução via banco de dados (nível DML).

## Stack Tecnológico

*   **Java 21**
*   **Spring Boot 3** (Web, Data JPA)
*   **Spring Expression Language (SpEL)**
*   **Banco de Dados H2** (Em memória)
*   **Lombok**

## Como o Motor Funciona

1.  **Mapeamento (`TB_VARIAVEL_MAPEAMENTO`)**: O sistema consolida os saldos de contas contábeis (`TB_BALANCETE_MAP`) baseando-se em seus códigos de tributação (`TB_COD_TRIB`), gerando variáveis consolidadas (ex: `vRecTrib`, `vDespDedut`).
2.  **Injeção de Variáveis**: Essas variáveis, junto com parâmetros do sistema (como alíquotas), são injetadas em um contexto seguro de avaliação (`SimpleEvaluationContext`).
3.  **Avaliação Dinâmica (`TB_FORMULA`)**: O Spring EL resgata a expressão matemática do banco de dados correspondente ao Código de Base de Cálculo (ex: `1005`) e a avalia.
4.  **Auditoria (`TB_AUDITORIA_CALCULO_ITEM`)**: O resultado final e o *snapshot* exato das variáveis de entrada no momento do cálculo são gravados para rastreabilidade fiscal.

## Como Executar a Aplicação

### 1. Iniciar o Projeto
O projeto utiliza um banco de dados H2 em memória, que é populado automaticamente na inicialização pelo arquivo `data.sql`.

Execute a classe principal `PocSpelDereApplication` na sua IDE ou use o Maven:
```bash
mvn spring-boot:run

```

### 2. Acessar o Console do H2 (Opcional)

Para visualizar as tabelas e dados pré-cadastrados (Tabela 11 e Tabela 12 da DeRE), acesse:

* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:deredb`
* **Username:** `sa`
* **Password:** *(deixe em branco)*

## Testando os Endpoints (API REST)

Você pode testar a API utilizando `curl`, Postman, Insomnia ou Thunder Client.

### Teste 1: Executar o Cálculo

Aciona o motor de cálculo para a Base de Cálculo `1005` (Crédito, Câmbio e TVM - Remunerado por Margem).

* **Endpoint:** `POST http://localhost:8080/api/calculo/1005`
* **O que acontece:** O SpEL avalia a fórmula `(#vRecTrib - #vDespDedut) / (1 + ((#pIBSMun + #pIBSUF + #pCBS) / 100))`. Com os dados do `data.sql` ((650.000 - 50.000) / 1.12), o resultado esperado é `535714.29`.

**Exemplo com cURL:**

```bash
curl -X POST http://localhost:8080/api/calculo/1005

```

### Teste 2: Consultar a Auditoria

Lista todos os cálculos executados, exibindo o *snapshot* das variáveis de entrada no momento exato do processamento.

* **Endpoint:** `GET http://localhost:8080/api/calculo/auditoria`

**Exemplo com cURL:**

```bash
curl -X GET http://localhost:8080/api/calculo/auditoria

```

**Retorno Esperado:**

```json
[
  {
    "id": 1,
    "codBc": "1005",
    "jsonVariaveisEntrada": "{pIBSMun=2.0, pCBS=9.0, pIBSUF=1.0, vDespDedut=50000.00, vRecTrib=650000.00}",
    "valorResultado": 535714.29,
    "dtProcessamento": "2026-09-03T15:30:00.00000"
  }
]

```
