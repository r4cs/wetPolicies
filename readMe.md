# WetPolicies

### Alunos:
* #### RM 97373 Raquel Calmon
* #### RM 97306 Lau Costa
* #### RM 96553 Felipe Seiji
* #### RM 97041 Johan Marzolla
* #### RM 96542 Gustavo Ballogh

### [:: WebApp WetPolicies na Azure ::](https://wet-policies.azurewebsites.net/)
### [:: Repositório :: ](https://github.com/r4cs/wetPolicies)

### Introdução

#### O oceano é um dos recursos mais preciosos do nosso planeta, sustentando uma vasta biodiversidade e fornecendo recursos essenciais para a vida humana. No entanto, a poluição e o uso inadequado dos recursos hídricos ameaçam a saúde dos nossos mares. No Brasil, uma grande quantidade de legislação visa proteger e regular o uso dos recursos marinhos e aquáticos. Mas como entender e acompanhar todas essas proposições e as posições das diversas bancadas parlamentares em relação a elas?

### Objetivo do Projeto
#### O projeto WetPolicies foi criado para abordar essa necessidade. Nosso objetivo é centralizar e simplificar o acesso às informações sobre proposições legislativas relacionadas à proteção dos oceanos e dos recursos hídricos no Brasil, com foco nas orientações das bancadas durante as votações. Com isso, queremos facilitar a análise e a transparência das ações legislativas que impactam o meio ambiente marinho.
#### Nesse sentido, a home do site avalia inicialmente a classificação das orientações dessas bancadas perante essas legislações. 
#### Caso o usuário queira usar a api, deverá logar e terá acesso aos endpoints get.

### Escopo
#### Inicialmente, nosso projeto possuía um escopo mais amplo, incluindo detalhes sobre todos os deputados e suas votações em diversas proposições. No entanto, percebemos que esse escopo era muito vasto e poderia diluir nosso foco principal. Portanto, decidimos restringir nosso escopo para nos concentrarmos nas orientações das bancadas nas votações por proposição. Isso não só torna nosso trabalho mais gerenciável, mas também destaca a influência das bancadas políticas nas decisões legislativas ambientais.

### Estrutura do Projeto
#### Para alcançar nossos objetivos, definimos uma estrutura de banco de dados que reflete as informações mais relevantes:

* #### Proposição: Representa uma proposta legislativa específica relacionada à proteção dos oceanos e recursos hídricos.
* #### Votação: Representa uma votação em uma proposição específica.
* #### Bancada: Representa um partido ou grupo político que dá orientações de voto.
* #### OrientaçãoBancada: Representa a orientação de uma bancada para uma votação específica.

### Consumo de APIs
#### Para obter as informações necessárias, nosso projeto consome a API de Dados Abertos da Câmara dos Deputados, que fornece dados sobre proposições legislativas. Utilizamos os seguintes endpoints:

#### https://dadosabertos.camara.leg.br/api/v2/proposicoes: Este endpoint nos permite buscar proposições com base em palavras-chave relacionadas ao meio ambiente marinho e recursos hídricos.
#### https://www.camara.leg.br/SitCamaraWS/Proposicoes.asmx: Foi necessário utilizar este endpoint da api antiga da camara, tendo em vista que na api nova (dadosabertos) há um desfalque nas informações quanto as votações em proposições.

### Criação de APIs Internas
#### Além de consumir dados, nosso projeto também cria APIs internas para facilitar o acesso e manipulação das informações processadas. Algumas das APIs criadas incluem:

* #### Proposição API: Permite obter informações sobre proposições específicas, incluindo detalhes das votações.

  * #### GET /proposicoes: Retorna uma lista de proposições.
  * #### GET /proposicoes/{id}: Retorna detalhes de uma proposição específica.
  * #### Votação API: Permite acessar informações sobre votações em proposições.

  * #### GET /votacoes: Retorna uma lista de votações.
  * #### GET /votacoes/{id}: Retorna detalhes de uma votação específica.

* #### Bancada API: Permite acessar informações sobre bancadas e suas orientações.

  * #### GET /bancadas: Retorna uma lista de bancadas.
  * #### GET /bancadas/{id}: Retorna detalhes de uma bancada específica.

* #### OrientaçãoBancada API: Permite acessar as orientações das bancadas para votações específicas.

    * #### GET /orientacoes: Retorna uma lista de orientações das bancadas.
    * #### GET /orientacoes/{id}: Retorna detalhes de uma orientação específica.

### Estrutura geral do projeto
* ### Rest Api
* ### Spring Boot
* ### Spring Security
* ### Azure WebApp
* ### Azure database
* ### Audit Log :: para cada entidade, foi criado um trigger a fim de detectar alterações no código, é possível verificar essa auditoria rodando o [projeto complementar deste projeto em .net](https://github.com/r4cs/AuditLog_WetPolicies)

### Conclusão
#### O projeto WetPolicies representa um passo importante na promoção da transparência e da conscientização sobre as ações legislativas que afetam os nossos oceanos e recursos hídricos. Ao focar nas orientações das bancadas, fornecemos uma visão clara de como diferentes grupos políticos influenciam a legislação ambiental. Esperamos que essa ferramenta seja valiosa para pesquisadores, ativistas ambientais e qualquer pessoa interessada em proteger nossos recursos naturais mais valiosos.






