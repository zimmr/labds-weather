# Weather App - Frontend

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