# BoaViagemAulaAndroid

Funcionalidade encontradas na aplicação:

- Registro de novos usuários
- Registros de viagens
- Listagem das viagens cadastradas na tela principal:
  * Se não houver viagen, é apresentada uma mensagem informando;
  * Se houver viagens, elas são apresentadas com ordenação por Tipo e Destino ascendente;
  * Ao clicar em uma viagem na tela principal é apresentada uma tela para edição da viagem;
  * Na listagem das viagens é apresentado um campo com o total de gastos que aquela viagem teve;
  * O ícone da viagem muda conforme seu tipo;
  * Botão para deletar a viagem;
  * Botão para salvar as alterações;
  * Botão para voltar à tela principal;
  * Botão para listar os gastos.*
  
- Listagem de gastos relacionados às viagens:
  * Se não houver gastos é apresentada uma mensagem informando;
  * Há um botão para incluir novos gastos;
  * Ao clicar em um dos gastos é apresentada uma tela para edição do gasto;
  * Botão para deletar o gasto;
  * Botão para salvar as alterações;
  * Botão para voltar à tela com a lista dos gastos.*


**AJUSTES NECESSÁRIOS**
- Por não conseguir implementar um menu drop down, para tipo de viagem e tipo de gasto, os campos permaneceram para input manual
(com indicação das opções através do hint).
- Por não conseguir implementar ícone/botão de "+" na tela principal, como solicitado, utilizou-se da navegação entre Fragments e botões normais
para realizar as ações.
