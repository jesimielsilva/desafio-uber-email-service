# 📧 Uber Email Service

Este projeto é um **serviço de envio de e-mails** construído em **Java + Spring Boot**, inspirado no desafio proposto no vídeo [Building Uber Backend Clone - Email Service](https://www.youtube.com/watch?v=eFgeO9M9lLw&t=3099s).  

O objetivo é demonstrar como aplicar **Clean Architecture** em um microserviço real, desacoplando responsabilidades e tornando o sistema testável, escalável e fácil de manter.

---

## 🏗️ Arquitetura Utilizada (Clean Architecture)

A aplicação segue os princípios da **Clean Architecture**, separando regras de negócio de detalhes de infraestrutura.  

📂 Estrutura de pacotes:

```
com.github.jesimielsilva.email_service
├── adapters                     # Camada de entrada/saída (interfaces externas)
│   └── EmailSenderGateway
│
├── application                  # Casos de uso da aplicação
│   └── EmailSenderService
│
├── controllers                  # Interface REST (exposição via API)
│   └── EmailSenderController
│
├── core                         # Núcleo da aplicação (entidades e exceções)
│   ├── exceptions
│   │   └── EmailServiceException
│   ├── EmailRequest
│   └── EmailSenderUseCase
│
├── infra                        # Implementações de infraestrutura
│   └── ses                      # Integração com AWS SES
│       ├── AwsSesConfig
│       └── SesEmailSender
│
└── EmailServiceApplication      # Classe principal (Spring Boot)

```

## 🔑 Descrição das Camadas

- **core**  
  Contém as entidades (`EmailRequest`) e exceções (`EmailServiceException`).  
  Também define a interface de caso de uso `EmailSenderUseCase`.

- **application**  
  Implementa os casos de uso, orquestrando as regras de envio de e-mail (`EmailSenderService`).

- **adapters**  
  Define as portas de saída (gateways) para envio de e-mails (`EmailSenderGateway`).

- **infra**  
  Implementa os detalhes de infraestrutura.  
  Exemplo: integração com **AWS SES** através de `SesEmailSender` e configuração em `AwsSesConfig`.

- **controllers**  
  Camada de entrada da aplicação, responsável pela comunicação REST.  
  Exemplo: `EmailSenderController` expõe o endpoint `/api/email`.

---

## ⚙️ Configuração

### 1. Dependências
O projeto usa **Maven** e o `spring-boot-starter`.  
No `pom.xml` já estão inclusos:
- Spring Web
- Spring Context
- AWS SDK for SES
- Spring Boot Starter Test

### 2. Configuração do `application.properties`
Defina suas credenciais da AWS para utilizar o SES:

```properties
spring.application.name=email-service

cloud.aws.region.static=us-east-2
cloud.aws.credentials.access-key=SEU_ACCESS_KEY
cloud.aws.credentials.secret-key=SUA_SECRET_KEY

```



