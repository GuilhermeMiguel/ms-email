# Arquitetura Hexagonal - Estrutura Proposta

## Estrutura de Pastas
```
src/main/java/com/ms/email/
├── domain/                     # Núcleo da aplicação
│   ├── model/                  # Entidades de domínio
│   ├── port/                   # Interfaces (portas)
│   │   ├── in/                 # Portas de entrada (use cases)
│   │   └── out/                # Portas de saída (repositórios, etc)
│   └── service/                # Serviços de domínio
├── infrastructure/             # Adaptadores externos
│   ├── adapter/
│   │   ├── in/                 # Adaptadores de entrada
│   │   │   ├── web/            # Controllers REST
│   │   │   └── messaging/      # Consumers
│   │   └── out/                # Adaptadores de saída
│   │       ├── persistence/    # JPA repositories
│   │       └── email/          # Email sender
│   └── config/                 # Configurações
└── application/                # Casos de uso
    └── usecase/                # Implementação dos casos de uso
```

## Conceitos Principais

### DOMAIN (Núcleo da Aplicação)
**Responsabilidade**: Contém as regras de negócio puras, sem dependências externas

**Componentes**:
- **Entidades** (`model/`): 
  - Objetos de negócio com comportamentos
  - Sem anotações de framework (JPA, etc)
  - Validações de domínio
  - Métodos que representam ações do negócio
  
- **Portas de Entrada** (`port/in/`):
  - Interfaces dos casos de uso
  - Definem COMO o mundo externo pode usar o domínio
  - Contratos que a aplicação deve implementar
  
- **Portas de Saída** (`port/out/`):
  - Interfaces para dependências externas
  - Definem O QUE o domínio precisa do mundo externo
  - Repositórios, email senders, APIs externas
  
- **Serviços de Domínio** (`service/`):
  - Lógica de negócio que não pertence a uma entidade específica
  - Operações complexas envolvendo múltiplas entidades
  - Regras de negócio puras

**Regras**:
- ❌ NÃO pode depender de outras camadas
- ❌ NÃO pode ter anotações de framework
- ✅ Apenas lógica de negócio pura
- ✅ Pode usar apenas bibliotecas padrão do Java

### APPLICATION (Casos de Uso)
**Responsabilidade**: Orquestra o domínio para executar casos de uso específicos

**Componentes**:
- **Implementações de Use Cases** (`usecase/`):
  - Implementam as interfaces definidas em `domain/port/in/`
  - Coordenam chamadas para o domínio
  - Fazem a ponte entre adaptadores de entrada e domínio
  - Gerenciam transações e fluxo de dados

**Características**:
- Conhece o domínio (usa suas entidades e serviços)
- Usa as portas de saída (interfaces) para acessar recursos externos
- Implementa as portas de entrada (interfaces de use cases)
- Contém a lógica de aplicação (não de negócio)

**Regras**:
- ✅ Pode depender do DOMAIN
- ❌ NÃO pode depender de INFRASTRUCTURE
- ✅ Usa interfaces (portas) para acessar recursos externos
- ✅ Implementa os casos de uso do sistema

### INFRASTRUCTURE (Adaptadores)
**Responsabilidade**: Conecta a aplicação com o mundo externo (frameworks, APIs, banco de dados)

**Adaptadores de Entrada** (`adapter/in/`):
- **Web** (`web/`): Controllers REST, GraphQL resolvers
- **Messaging** (`messaging/`): Consumers de filas, event listeners
- **CLI** (`cli/`): Command line interfaces
- **Responsabilidade**: Recebem requisições externas e chamam use cases
- **Fluxo**: Mundo Externo → Adaptador IN → Use Case → Domain

**Adaptadores de Saída** (`adapter/out/`):
- **Persistence** (`persistence/`): Implementações JPA, MongoDB, etc
- **Email** (`email/`): Implementações de envio de email
- **HTTP** (`http/`): Clientes para APIs externas
- **File** (`file/`): Manipulação de arquivos
- **Responsabilidade**: Implementam as portas de saída do domínio
- **Fluxo**: Domain → Use Case → Porta OUT → Adaptador OUT → Mundo Externo

**Configurações** (`config/`):
- Beans do Spring
- Configurações de banco
- Configurações de segurança
- Wiring das dependências

**Regras**:
- ✅ Pode depender de APPLICATION e DOMAIN
- ✅ Contém todas as dependências de frameworks
- ✅ Implementa as interfaces (portas) definidas no domínio
- ✅ Faz conversões entre formatos externos e domínio (DTOs ↔ Entities)

## Diferença entre IN e OUT

### 🔵 **IN (Entrada)** - Quem CHAMA o domínio
- **Controllers**: Recebem HTTP requests
- **Consumers**: Recebem mensagens de filas
- **Schedulers**: Executam tarefas agendadas
- **CLI**: Comandos de linha

**Fluxo**: `Mundo Externo → Adaptador IN → Use Case → Domain`

### 🔴 **OUT (Saída)** - Quem o domínio CHAMA
- **Repositories**: Persistência de dados
- **Email Senders**: Envio de emails
- **HTTP Clients**: Chamadas para APIs externas
- **File Systems**: Manipulação de arquivos

**Fluxo**: `Domain → Use Case → Porta OUT → Adaptador OUT → Mundo Externo`

### 💡 **Dica para lembrar**:
- **IN**: "Quem está **entrando** na aplicação?"
- **OUT**: "Para onde a aplicação está **saindo**?"

## Fluxo de Dependências

```
INFRASTRUCTURE → APPLICATION → DOMAIN
     ↑                           ↓
     └── implementa interfaces ──┘
```

- **DOMAIN**: Define interfaces (portas)
- **APPLICATION**: Usa as interfaces do domain
- **INFRASTRUCTURE**: Implementa as interfaces do domain

## Exemplo de Fluxo de Execução

1. **Controller** (infrastructure/in) recebe requisição HTTP
2. **Controller** chama **Use Case** (application)
3. **Use Case** usa **Entidades** e **Serviços de Domínio** (domain)
4. **Use Case** chama **Porta de Saída** (interface no domain)
5. **Adaptador de Saída** (infrastructure/out) implementa a porta
6. **Adaptador** acessa banco/API externa
7. Resultado volta pelo mesmo caminho invertido
