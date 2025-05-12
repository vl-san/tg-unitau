# GitHub API de captura de métricas - TG-UNITAU

##Passo a passo para rodar a aplicação:

1. Instale o Docker:  
https://www.docker.com/products/docker-desktop

2. Clone este repositório:

```bash
git clone https://github.com/vl-san/tg-unitau.git
```

3. Rode o container:
cd local-do-repositório

docker compose up --build

4.Acesse no navegador:
Interface: http://localhost:8080/view

## Como gerar o Token de Acesso do GitHub

1. Acesse sua conta no GitHub e vá para **Settings**.
2. No menu à esquerda, clique em **Developer settings**.
3. Selecione **Personal access tokens**, **Fine-grained tokens** e clique em **Generate new token**.
4. Não há necessidade de pedir permissões.
5. Clique em **Generate token** e **copie o token gerado**.
