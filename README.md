# 📧 MS-Email - Microserviço de Email

Microserviço para envio de emails desenvolvido com **Arquitetura Hexagonal** (Ports & Adapters) usando Spring Boot.

## 🏗️ Arquitetura

Este projeto implementa a **Arquitetura Hexagonal**, garantindo:
- ✅ Domínio isolado e livre de dependências externas
- ✅ Inversão de dependência através de portas e adaptadores
- ✅ Alta testabilidade e flexibilidade
- ✅ Fácil manutenção e evolução

📖 **[Documentação completa da arquitetura](./doc/hexagonal_structure.md)**

## 🚀 Funcionalidades

- **Envio de emails** via SMTP
- **Persistência** de histórico de emails
- **Status de entrega** (PROCESSING, SENT, ERROR)
- **API REST** para consulta e envio
- **Consumidor RabbitMQ** para processamento assíncrono
- **Tratamento de erros** centralizado

## 🛠️ Tecnologias

- **Java 11+**
- **Spring Boot** (Web, Data JPA, Mail, AMQP)
- **PostgreSQL** / H2 Database
- **RabbitMQ**
- **Maven**
- **Lombok**

## 📁 Estrutura do Projeto

```
src/main/java/com/ms/email/
├── domain/                     # 🔵 Núcleo da aplicação
│   ├── model/                  # Entidades e enums
│   ├── port/in/                # Interfaces de casos de uso
│   ├── port/out/               # Interfaces para infraestrutura
│   └── exception/              # Exceptions de domínio
├── application/                # 🟡 Casos de uso
│   └── usecase/                # Implementações dos casos de uso
└── infrastructure/             # 🔴 Adaptadores externos
    ├── adapter/
    │   ├── controllers/        # REST Controllers
    │   ├── consumers/          # RabbitMQ Consumers
    │   └── out/                # Adaptadores de saída
    ├── config/                 # Configurações Spring
    ├── handler/                # Exception handlers
    └── mapper/                 # Conversões entre camadas
```

## 🔌 API Endpoints

### Enviar Email
```http
POST /api/v1/emails
Content-Type: application/json

{
  "ownerRef": "user123",
  "emailFrom": "sender@example.com",
  "emailTo": "recipient@example.com",
  "subject": "Assunto do email",
  "text": "Conteúdo do email"
}
```

### Listar Emails
```http
GET /api/v1/emails?page=0&size=10
```

### Buscar Email por ID
```http
GET /api/v1/emails/{emailId}
```

## ⚙️ Configuração

### Banco de Dados
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ms-email
spring.datasource.username=postgres
spring.datasource.password=password
```

### Email SMTP
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-password
```

### RabbitMQ
```properties
spring.rabbitmq.addresses=amqp://localhost:5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=123456
```

## 🚀 Como Executar

1. **Clone o repositório**
```bash
git clone <repository-url>
cd ms-email
```

2. **Configure o banco de dados e RabbitMQ**

3. **Execute a aplicação**
```bash
./mvnw spring-boot:run
```

4. **Acesse a API**
```
http://localhost:8080/api/v1/emails
```

## 🧪 Testes

```bash
./mvnw test
```

## 📚 Documentação Adicional

- 📖 **[Arquitetura Hexagonal](./doc/hexagonal_structure.md)** - Guia completo da estrutura
- 🔧 **[Configuração](./doc/configuration.md)** - Configurações detalhadas *(em breve)*
- 🧪 **[Testes](./doc/testing.md)** - Estratégias de teste *(em breve)*

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

⭐ **Desenvolvido com Arquitetura Hexagonal para máxima flexibilidade e testabilidade!**
