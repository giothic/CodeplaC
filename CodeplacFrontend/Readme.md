#### Uniceplac
# CodeplaC - Competição de Programação em C

## 1. Introdução

### Visão Geral do Projeto:
O **CodeplaC** é uma plataforma online desenvolvida para facilitar a gestão e participação dos estudantes na competição de programação em C. O objetivo principal é criar um ambiente acessível e intuitivo para os participantes, permitindo a visualização do progresso, rankings e interações com os organizadores. O público-alvo são estudantes interessados em competições acadêmicas e de programação.

### Arquitetura Geral:
O projeto é baseado em uma arquitetura web tradicional, utilizando **HTML, CSS e JavaScript (react)** para o front-end, enquanto o back-end é desenvolvido em **Java**. A plataforma conta com um banco de dados **MySQL** para armazenar as informações dos participantes e resultados das competições.

---

## 2. Estrutura do Projeto

### Organização de Pastas e Arquivos:
- `front-end`: Contém os arquivos HTML, CSS e JavaScript relacionados à interface do usuário.
- `back-end`: Implementação do servidor em Java e conexão com o banco de dados MySQL.
- `database`: Scripts para criação e manutenção do banco de dados MySQL.
- `tests`: Contém scripts de testes automatizados e manuais.
- `Github`: Contém todas as sprints de organização

### Mapeamento de URLs:
- `home`: Página inicial com informações gerais sobre a competição.
- `historico`: Página que exibe o histórico das competições passadas.
- `equipe`: Página com a lista dos organizadores e participantes.
- `contato`: Página de contato para comunicação com os organizadores.

## Plataforma Web

#### Ideia de Base
A plataforma será desenvolvida para permitir a interação entre organizadores e competidores. Ela contará com um design intuitivo e responsivo, oferecendo uma experiência agradável aos usuários.

#### Funcionalidades Principais
- Inscrição Online: Os competidores e participantes poderão se inscrever diretamente na plataforma.

- Acompanhamento das competições: exibição das etapas, horários, regras e status da competição.

- Ranking (pódio): Exibição das classificações e progresso dos participantes durante o evento.

- Histórico de competições: registro das edições passadas com resultados, vencedores e estatísticas.

#### Área de administração
- Painel administrativo: Para os organizadores gerirem o evento, incluindo o controle das fases da competição, envio de notificações e monitoramento do progresso dos competidores.

- Relatórios e análises: Geração de relatórios detalhados sobre o desempenho dos competidores e o andamento da competição.

#### Módulo de Mídia e interatividade
- Galeria de fotos e Vídeos: Uma seção dedicada a colocar os melhores momentos da competição.

- Notícias e Atualizações: Seção para publicar as últimas novidades sobre as competições, como mudanças de regras, novas etapas, palestras, ou destaque dos competidores.

- Entrevistas e Cobertura de Eventos: Entrevistas e palestras com participantes, organizadores, não participantes, mostrando suas motivações e experiências.

#### Segurança e Acessibilidade
- Segurança dos Dados: Implementação de medidas de segurança para garantir a proteção dos dados pessoais dos participantes e organizadores !
![Blog](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white) 

- Acessibilidade: A plataforma será otimizada para que seja acessível a todos os usuários, independentemente de suas limitações físicas ou tecnológicas.

#### Manutenção e Escalabilidade
- Futuras atualizações: A plataforma será desenvolvida de forma escalável, permitindo a adição de novas funcionalidades, competições e melhorias sem comprometer o desempenho.

- Suporte Técnico e Manutenção: Garantia de manutenção contínua para a correção de bugs e melhorias na plataforma ao longo do tempo.

#### Integração com APIs
- API de Classificação: Um sistema para gerenciar e atualizar automaticamente as classificações dos competidores.

- Integração com Ferramentas Externas:
Potencial para integração com outras ferramentas de aprendizado e avaliação.

#### Documentação e Suporte
- Documentação para Usuários: Um manual detalhado estará disponível para orientar os participantes e organizadores sobre como utilizar a plataforma.

- Suporte técnico: Haverá um canal de suporte para ajudar a resolver problemas técnicos que possam surgir durante o uso da plataforma.

## Cookies
#### Descrição:
- "Utilizamos cookies para melhorar sua experiência com nosso site, personalizar conteúdos e analisar nosso tráfego. Ao continuar navegando, você concorda com o uso de cookies?"

---
## 3. Seções 
### Home:
### Frase Principal:
*Desafie-se e avance suas habilidades de programação. Participe das competições dentro da CodeplaC e mostre o que você é capaz de fazer!*

### Subtítulo de Destaque:
*Plataforma interativa para competições de programação na Uniceplac*

---

### Seções da Home:


#### 1. *Faça sua inscrição ou login Agora*
Descrição: 

Participe das edições que estão por vir e prepare-se para enfrentar suas próprias capacidades! não perca a oportunidade de participar e mostrar suas habilidades ou assistir a esses belíssimos eventos!

#### Botão:
- **[Cadastre-se](#login)**

---

#### 2. *Sobre a competição*
Descrição:

A CodeplaC é um projeto criado para os estudantes que desejam aprimorar suas habilidades em programação, mais especificamente em C, em um ambiente desafiador e colaborativo.

#### Botão:
- **[eventos](#eventos)**

---

#### 3. *Classificações em Tempo Real*
Descrição:

Acompanhe o progresso dos competidores depois de cada competição. Veja onde você está no ranking e quanto precisa para alcançar o topo.

#### Botão:
- **[Ver histórico](#histórico)** e **[Média](Média)**

---

#### 4. Contatos: 
*Entre em contato:* Tem dúvidas? Fale com a nossa equipe. - - **[Contatos](#Contatos)**

#### 5. Imagens:
Descrição:

Confira as fotos dos melhores momentos da competição. Desde a preparação dos competidores até as cerimônias de premiação, veja o que está acontecendo por trás das divulgações!

- **[Mídia](#Mídia)**

## 4. Configuração e Instalação

### Pré-requisitos:
- **Software:** Visual Studio Code, JDK 11+, MySQL Server.

## 5. FrontEnd
Para o Front-End do projeto CodeplaC, você utilizará uma combinação de Bootstrap, CSS, Javascript e Html para construir uma interface dinâmica, moderna e responsiva. O uso do Spring Boot irá facilitar a construção de layouts que se adaptam a diferentes dispositivos, enquanto o CSS e o JavaScript serão usados para personalizar a aparência e o comportamento da página.

## 6. BackEnd do projeto CodeplaC
O back-end do projeto Codeplac, será a camada responsável por toda a lógica de negócio, processamento de dados e comunicação com o banco de dados. Ele funcionará como o "cérebro" do sistema, lidando com solicitações dos usuários (via front-end), processando essas solicitaçãos e retornando respostas adequadas. No CodeplaC, o back-end será essencial para gerenciar competições, inscrições, classificações, interações com os competidores, e mais.

### Funcionalidades do Back-End
1. Gerenciamento de competição:
- Criação, atualização e exclusão de competições.
- Gestao de etapas da competição, com seus horários, regras e requisitos.

2. Inscrições e autenticação de Usuários:
- Manter um sistema de cadastro de usuários onde eles possam se inscrever e autenticar (login).
- Gestão de permissões, garantindo que apenas usuários autenticados possam acessar certas áreas ou funcionalidades da plataforma.

3. Gerenciamento de Resultados:
- Armazenar e processar as pontuações dos competidores, gerando rankings em tempo real.
- Disponibilizar relatórios sobre o desemprenho dos competidores e progresso da competição.

4. Comunicação entre Front-end e Back-end (apenas ideia):
- API RESTful para servir e processar dados.
- Suporte a operações CRUD (Create, Read, Update, Delete) para manipulação de dados relacionados à competição, participantes, resultados, entre outros.

5. Relatórios e Análises:
- Gerar relatórios automáticos para os organizadores, com estatísticas das competições e desempenho dos competidores.

6. Segurança:
- Implementar autenticação e autorização segura, com sistemas de proteção como JWT (JSON Web Tokens) para garantir que apenas usuários autorizados possam acessar e manipular certos dados.

- Proteção contra vulnerabilidades comuns, como SQL injection e Cross-Site Scripting(XSS).

## 7. Estrutura de Tecnologias (Apenas ideia por enquanto)
1. **Linguagem e Frameworks**

O Back-End do projeto CodeplaC será desenvolvido em Java utilizando o Spring Boot, um framework poderoso e flexível que facilita o desenvolvimento de APIs e sistemas robustos. Ele fornecerá as ferramentas necessárias para lidar com a lógica do servidor, manipulação de dados, autenticação e muito mais.

Java: A linguagem principal do back-end. Escolhida por sua robustez, escalabilidade e extensa comunidade de desenvolvedores.

Spring Boot: Um framework Java que permite criar aplicações com facilidade, especialmente APIs RESTful. Inclui suporte integrado para segurança, controle de dependências e injeção de dependências.

2. **Banco de Dados**

Será utilizado o MySQL como banco de dados relacional para armazenar informações como:
- Dados dos competidores
- Resultados e pontuações das competições
- Dados administrativos, como fases, horários, regras e usuários administradores.

O Spring Boot facilita a conexão e manipulação de dados em MySQL por meio do **JPA/Hibernate**, que fornece um mapeamento objeto-relacional (ORM), permitindo trabalhar com objetos Java ao invés de diretamente com tabelas de banco de dados.

3. **API RESTful**
- O back-end será exposto via uma **API RESTful** que permitirá a comunicação entre os front0end e o back-end. As APIs seguirão padrões REST para garantir interoperabilidade e facilitar o consumo dos serviços no front-end.

Exemplos de Endpoints REST:
- `POST /api/inscricao`: Endpoint para registrar um novo participante na competição.

- `GET /api/competicoes`: Retorna a lista de competições ativas e informações detalhadas.

- `GET /api/ranking`: Retorna o ranking em tempo real dos competidores.

- `POST /api/auth/login`: Autenticação de usuários.

- `PUT /api/competicoes/{id}`: Atualiza uma competição existente.

- `DELETE /api/competicoes/{id}`: Remove uma competição.

4. Autenticação e Segurança

Para proteger as rotas sensíveis e os dados dos usuários, o sistema utilizará **JWT (JSON Web Token)** para autenticação. Isso permitirá que os usuários façam login com segurança e que cada solicitação autenticada contenha um token que será validade pelo back-end.


## Benefícios do Back-End no projeto CodeplaC
- Centralização de Dados: Todas as informações da competição estarão centralizadas e acessíveis através da API.

- Facilidade de gereanciamento: O back-end permite que os organizadores administrem a competição de forma simples e eficaz.

- Escalabilidade: O sistema pode ser escalado para comportar novas funcionalidades ou até mesmo outras competições no futuro.

- Segurança: Implementações de segurança robustas garantem a proteção dos dados dos usuários e da plataforma. 

## 8. Ideias para o Front-End Baseado na logo
1. **Paleta de Cores:**
- Baseada na logo, podemos definir uma paleta de cores que inclua:
- Azul mais escuro (#0C6975) para o cabeçalho e botões importantes.
- Azul mais claro (#00BFC1) para detalhes e destaques.
- Branco (#FFFFFF) para textos e elementos de contraste.

2. **Tipografia:**
- Podemos escolher uma tipografia moderna e limpa como Consolas, Monstserrat ou Roboto, para manter o design leve e elegante, em harmonia com a logo.

3. **Design do layout:**
- Cabeçalho: O logo será centralizado ou colocado no canto esquerdo, com links de navegação principais ao lado.

- Botões: Utilizaremos o estilo das bordas arredondadas e cores da logo para criar botões interativos.

- Rodapé: O rodapé incluirá novamente a logo, informações de contacto e links úteis.

## 9. Como será utilizado o Trello
#### 1. **Criação de Quadros e listas:**
- Quadro Principal: crie um quadro para o projeto CodeplaC.

- Listas: Organize listas para diferentes fases do projeto, como "Backlog", "Em Andamento", "Revisão", e "Concluído".

#### 2. **Cartões:**
- Tarefas: Criar cartões para tarefas específicas dentro de cada lista. Por exemplo, "Desenvolver interface de Login" ou "implementar funcionalidade de Submissão".

- Descrições e Checklists: Adicione descrições detalhadas e checklists nos cartões para garantir que todos os aspectios da tarefa sejam cobertos.

#### 3. **Etiquetas e Prioridades:**
- Etiquetas: Usar etiquetas para categorizar tarefas por tipo, prioridade ou área de trabalho (por exemplo, "Front-end", "Back-end", "Testes").

- Prioridades: Marque tarefas como alta, média ou baixa prioridade para ajudar na gestão do tempo.

#### 4. **Atribuições e Datas:**
- Membros: Atribuir cartões aos membros da equipe responsáveis pelas tarefas.

- Datas de Entrega: Definir datas de entrega para cada tarefa para manter o cronograma do projeto em dia.

#### 5. **Anexos e Comentários:**
- Anexos: Anexar documentos, imagens, ou arquivos relevantes aos cartões.

- Comentários: Usar os comentários para atualizações de progresso e para comunicação entre os membros da equipe.

#### 6. **Integração e Automação:**
- Power-Ups: Considerar usar Power-Ups do Trello para integração com outras ferramenta.

- Automação: Utilize automações para mover cartões entre listas com base em ações específicas (por exemplo, mover um cartão para "Concluído" quando a checklist estiver completa).

## Lista: Equipe - ADS
#### 1. Ágatha Ariell - Scrum Master e desenvolvedora Front-End
- Responsabilidades:
- Gerenciar o progresso do projeto e facilitar reuniões (Scrum Master).

#### 2. Rhawan Henrique - Product Owner 
- Responsabilidades:
- Definir e priorizar requisitos do produto.
- Garantir que a equipe esteja alinhada com os objetivos do projeto.

#### 3. Gabriel Haddad e Pedro Júlio - Quality Assurance
- Responsabilidades:
- Testar o software para identificar bugs e problemas.
- Garantir que o produto final atenda aos padrões de qualidade.

#### 4. Junia Kálida - Desenvolvedor Back-End
- Responsabilidades:
- Desenvolver e manter a lógica de servidor e funcionalidades do back-end.
- Integrar APIs e gerenciar banco de dados.

#### 5. Pedro Roque e Thawan Campos - Desenvolvedor
- Responsabilidades:
- Desenvolver e implementar funcionalidades adicionais.
- Colaborar no desenvolvimento de front-end e back-end.


## 10. Reuniões por semana:
- Uma reunião semanal 
- Entre quarta e domingo.

## 11. Cronograma inicial

| Etapa                       | Descrição                                                                                     | Período         |
|-----------------------------|-----------------------------------------------------------------------------------------------|-----------------|
| **Planejamento**             | Definição de escopo, levantamento de requisitos.                                               | 1ª - 3ª semana  |
| **Design da Interface**      | Criação de wireframes e protótipos da interface.                                               | 1ª - 3ª semana  |
| **Desenvolvimento Inicial**  | Implementação do front-end e back-end básico.                                                  | 5ª - 6ª semana  |
| **Integração de Funcionalidades** | Desenvolvimento da área específica onde os competidores podem visualizar as etapas do evento, seus horários, regras e status. | 6ª - 7ª semana  |
| **Testes e QA**              | Testes de qualidade, ajuste de bugs e melhorias.                                               | 8ª - 9ª semana  |
| **Lançamento da Base**       | Implantação da plataforma e avaliação final.                                                   | 10ª - 14ª semana|
| **Conclusão**                | Revisão e fechamento do projeto.                                                              | 15ª semana      |

