**Laboratório de Desenvolvimento de Software - IFRS Canoas**

Aplicação de consulta de dados meteorológicos, usando a OpenWeather API: https://openweathermap.org/api

## Backend
Desenvolvido em Java.

### Dependências
Baixar .jar e salvar no diretório /lib
* Gson 2.14.0 https://repo.maven.apache.org/maven2/com/google/code/gson/gson/2.14.0/
* MySQL Connector/J 9.7.0 https://repo.maven.apache.org/maven2/com/mysql/mysql-connector-j/9.7.0/
* JBCrypt 0.4 https://repo1.maven.org/maven2/org/mindrot/jbcrypt/0.4/
  
### Configuração de variáveis
1. Copiar o arquivo `config.properties.example`
2. Salvar como `config.properties`
3. Alterar o valor das variáveis necessárias

### Compilando e rodando
1. Entrar na raíz do projeto.
2. Executar `.\compile.ps1` no Powershell.
3. Executar `.\run.ps1` no Powershell.

### Testando a API
Baixar e importar a collection no Postman: https://github.com/zimmr/labds-weather/blob/main/docs/Clima%20API.postman_collection.json  

Ou usar a linha de comando:
```
curl "http://localhost:8080/geo?city=Porto Alegre"
```

<img width="1112" height="336" alt="request-geo-api" src="https://github.com/user-attachments/assets/904ccfcd-c65e-4abd-a371-01fd43d8bf58" />

## Frontend

Frontend da aplicação de consulta climática desenvolvido com Angular.

## Tecnologias

- Angular
- TypeScript
- Bootstrap

---

# Configuração do Ambiente

## 1. Instalar o Node.js

Baixe e instale o Node.js LTS:

https://nodejs.org

Após instalar, reinicie o VS Code ou terminal.

Verifique a instalação:

```bash
node -v
npm -v
```

---

## 2. Configurar o PowerShell (Windows)

Caso o PowerShell bloqueie comandos do npm, execute o comando abaixo como administrador:

```powershell
Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
```

---

## 3. Instalar o Angular CLI

Execute:

```bash
npm install -g @angular/cli
```

Verifique a instalação:

```bash
ng version
```

---

# Instalação do Projeto

Acesse a pasta do frontend:

```bash
cd app/frontend/weather-app
```

Instale as dependências:

```bash
npm install
```

---

# Executando o Projeto

Inicie o servidor Angular:

```bash
ng serve
```

A aplicação estará disponível em:

```text
http://localhost:4200
```

---

# Bootstrap

O projeto utiliza Bootstrap para estilização.

Caso necessário, reinstale com:

```bash
npm install bootstrap
npm install bootstrap-icons
```

