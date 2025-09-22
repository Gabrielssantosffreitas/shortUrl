# Encurtador de Link Simples

Este é um projeto de um encurtador de links simples, desenvolvido em Java com Spring Boot.

## Fluxo do Projeto

O projeto consiste em criar links encurtados, redirecionar para os links originais, e gerar relatórios sobre os links criados.

## Rotas da API

### LinkCreatorController

#### `POST /created`

Cria um novo link encurtado.

**Entrada:**

```json
{
  "url": "https://www.example.com/um/link/muito/longo/para/ser/encurtado",
  "shortUrl":"examplo",
  "estado":"0"
}
```

**Fluxo:**

1.  Recebe um `LinkDTO` com a URL original.
2.  O `LinkCreatorService` cria uma nova `LinkEntity`.
3.  A URL original é salva.
4.  Uma URL curta e única é gerada.
5.  A data de criação é registrada.
6.  O número de cliques é inicializado como 0.
7.  O estado é definido como `ATIVO`.
8.  A `LinkEntity` é salva no banco de dados.
9.  Retorna a `LinkEntity` criada.

**Saída:**

```json
{
  "id": 1,
  "urlOriginal": "https://www.example.com/um/link/muito/longo/para/ser/encurtado",
  "urlCurta": "aBcDeF",
  "dataDeCriacao": "2025-09-22T10:00:00",
  "numeroDeCliques": 0,
  "estado": "ATIVO"
}
```

---

### RedirectController

#### `GET /r/{url}`

Redireciona para a URL original.

**Entrada:**

-   `url` (path variable): A URL curta gerada.

**Fluxo:**

1.  Recebe a URL curta como um path variable.
2.  O `RedirectService` busca a `LinkEntity` correspondente no banco de dados.
3.  Se encontrada, incrementa o `numeroDeCliques`.
4.  Salva a entidade atualizada.
5.  Retorna uma resposta de redirecionamento (302) para a `urlOriginal`.
6.  Se não encontrada, retorna um erro 404.

**Saída:**

-   Redirecionamento para a URL original.

---

### RelatorioCrotroller

#### `GET /all`

Retorna todos os links cadastrados.

**Fluxo:**

1.  O `RelatorioService` busca todas as `LinkEntity` no banco de dados.
2.  Retorna uma lista de `LinkEntity`.

**Saída:**

```json
[
  {
    "id": 1,
    "url": "https://www.example.com/link1",
    "shortUrl": "link1-curto",
    "createdDate": "2025-09-22T10:00:00",
    "dateExpire": "2025-09-29T10:00:00",
    "estate": "ATIVO",
    "click": 15
  },
  {
    "id": 2,
    "url": "https://www.example.com/link2",
    "shortUrl": "link2-curto",
    "createdDate": "2025-09-21T15:30:00",
    "dateExpire": "2025-09-28T15:30:00",
    "estate": "DESATIVADO",
    "click": 5
  }
]
```

#### `GET /gruopByUrl`

Agrupa os links pela URL original.

**Fluxo:**

1.  O `RelatorioService` busca todos os links.
2.  Agrupa os links em um `Map` onde a chave é a `url` e o valor é uma lista de `LinkEntity` com a mesma URL.
3.  Retorna o `Map`.

**Saída:**

```json
{
  "https://www.example.com/link1": [
    {
      "id": 1,
      "url": "https://www.example.com/link1",
      "shortUrl": "link1-curto",
      "createdDate": "2025-09-22T10:00:00",
      "dateExpire": "2025-09-29T10:00:00",
      "estate": "ATIVO",
      "click": 15
    }
  ]
}
```

#### `GET /orderByDate`

Ordena os links por data de criação.

**Fluxo:**

1.  O `RelatorioService` busca todos os links.
2.  Ordena a lista de links pela `dataDeCriacao` em ordem decrescente.
3.  Retorna a lista ordenada.

**Saída:**
Uma lista de `LinkEntity` ordenada por data.

#### `GET /orderByClick`

Ordena os links pelo número de cliques.

**Fluxo:**

1.  O `RelatorioService` busca todos os links.
2.  Ordena a lista de links pelo `numeroDeCliques` em ordem decrescente.
3.  Retorna a lista ordenada.

**Saída:**
Uma lista de `LinkEntity` ordenada por número de cliques.

#### `GET /groupByDateCountLink`

Agrupa os links por data e conta quantos foram criados em cada data.

**Fluxo:**

1.  O `RelatorioService` busca todos os links.
2.  Cria um `Map` onde a chave é a `dataDeCriacao` e o valor é a contagem de links criados naquela data.
3.  Retorna o `Map`.

**Saída:**

```json
{
  "2025-09-22T00:00:00": 10,
  "2025-09-21T00:00:00": 5
}
```

#### `GET /groupByState`

Agrupa os links pelo estado (`ATIVO` ou `DESATIVADO`).

**Fluxo:**

1.  O `RelatorioService` busca todos os links.
2.  Cria um `Map` onde a chave é o `estado` e o valor é uma lista de `LinkEntity` com aquele estado.
3.  Retorna o `Map`.

**Saída:**

```json
{
  "ATIVO": [
    {
      "id": 1,
      "url": "https://www.example.com/link1",
      "shortUrl": "link1-curto",
      "createdDate": "2025-09-22T10:00:00",
      "dateExpire": "2025-09-29T10:00:00",
      "estate": "ATIVO",
      "click": 15
    }
  ],
  "DESATIVADO": [
    {
      "id": 2,
      "url": "https://www.example.com/link2",
      "shortUrl": "link2-curto",
      "createdDate": "2025-09-21T15:30:00",
      "dateExpire": "2025-09-28T15:30:00",
      "estate": "DESATIVADO",
      "click": 5
    }
  ]
}
```

---

### RelatorioIAController

#### `GET /ia/`

Gera um relatório com insights sobre os links usando IA.

**Fluxo:**

1.  O `RelatorioIAService` coleta os dados dos links.
2.  Formata os dados em uma string para enviar a uma API de IA (ex: Gemini).
3.  Envia os dados para a API de IA e solicita um relatório.
4.  Recebe a resposta da IA.
5.  Retorna a resposta como uma string.

**Saída:**

```
"Com base nos dados, observamos que a maioria dos links foi criada na última semana, com um pico na segunda-feira. O link mais clicado é sobre 'notícias de tecnologia', sugerindo um alto interesse neste tópico."
```
