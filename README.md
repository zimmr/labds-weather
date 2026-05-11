**Laboratório de Desenvolvimento de Software - IFRS Canoas**

Aplicação de consulta de dados meteorológicos, usando a OpenWeather API: https://openweathermap.org/api

## Backend
Desenvolvido em Java.

### Dependências
Baixar .jar e salvar no diretório /lib
* Gson 2.14.0 https://repo.maven.apache.org/maven2/com/google/code/gson/gson/2.14.0/
  
### Configuração de variáveis
1. Copiar o arquivo `config.properties.example`
2. Salvar como `config.properties`
3. Alterar o valor das variáveis necessárias

### Compilando e rodando
1. Entrar na raíz do projeto.
2. Executar `.\compile.ps1` no Powershell.
3. Executar `.\run.ps1` no Powershell.

### Testando a API
```
curl "http://localhost:8080/geo?city=Porto Alegre"
```

<img width="1112" height="336" alt="request-geo-api" src="https://github.com/user-attachments/assets/904ccfcd-c65e-4abd-a371-01fd43d8bf58" />

## Frontend
### Dependências

### Configuração de variáveis

### Compilando e rodando

### Testando a aplicação

