# <img src="https://github.com/user-attachments/assets/facb5d05-09b4-4096-afe4-ee873a92e40f" alt="safezone-icon" width="70"/> Safezone - Sistema de Monitoramento de Ocorrências e Alertas Naturais 🌍

Safezone é um sistema web desenvolvido com **Spring MVC** para monitorar, registrar e notificar ocorrências e alertas naturais. Ele permite a visualização geográfica das ocorrências via Google Maps e utiliza **RabbitMQ** para envio de alertas em tempo real. Além disso, possui registro de logs persistentes no **MongoDB**, permitindo rastreabilidade de todas as ações executadas nos controllers da API e o armazenamento das informações no **Oracle SQL**.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring MVC**
- **Spring Data JPA**
- **Spring Data MongoDB**
- **Spring AMQP (RabbitMQ)**
- **Thymeleaf**
- **OAuth 2.0**
- **Oracle SQL**
- **MongoDB**
- **RabbitMQ**
- **Docker Compose**

## ⚙️ Funcionalidades

- Cadastro, edição e remoção de **Ocorrências**, **Áreas de Riscos** e **Campanhas**
- Criação e listagem de **Alertas** relacionados às ocorrências
- Visualização da localização via **Google Maps**
- Envio de alertas para fila do **RabbitMQ**
- Consumo de mensagens do RabbitMQ por um consumidor separado
- Registro de logs automáticos de requisições e exceções via **Aspect-Oriented Programming (AOP)** e persistência no **MongoDB**
- Dados sendo salvos no **Oracle SQL**
- Interface web utilizando **Thymeleaf**

## 🚀 Link do deploy
https://safezone-agent-hehncbf7eac2chez.brazilsouth-01.azurewebsites.net

## 📦 Estrutura do Projeto
![image](https://github.com/user-attachments/assets/8a195684-52a1-4fcd-8967-9ab0a29b61c8)

## 🚨 IMPORTANTE
![image](https://github.com/user-attachments/assets/da273bed-2bb2-4589-9939-c12652b38ff6)
- **Assim que o navegador solicitar a localização, clique em 'Permitir'**

# 🎞️ Vídeo de demonstração
[<img src="https://github.com/user-attachments/assets/facb5d05-09b4-4096-afe4-ee873a92e40f" width="50%">](https://www.youtube.com/watch?v=qFiJH4VpClw)



