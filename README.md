# Venix Group Plugin

O **Venix Group Plugin** é um plugin para Minecraft desenvolvido para facilitar o gerenciamento de grupos e permissões utilizando a API do LuckPerms. Este plugin oferece uma maneira eficiente e personalizada de gerenciar permissões e status de jogadores em seu servidor Minecraft.

## Funcionalidades

- **Gerenciamento de Grupos**: Adicione e remova grupos de jogadores com comandos simples.
- **Compatibilidade**: Funciona com a API do LuckPerms para uma integração completa com o sistema de permissões do servidor.
- **Gerenciamento de Tags**: Altere sua tag quantas vezes quiser e como quiser.

## Comandos

- `/group <adicionar/remover> <player> <group>`: Adiciona ou remove um grupo de um jogador.
  - **Adicionar**: Concede o grupo ao jogador e pode ativar efeitos VIP.
  - **Remover**: Remove o grupo do jogador.
- `/tag <nome>`: Aplica diretamente uma tag ao jogador, se ele tiver permissão para usá-la.
  - **Altere**: Altere sua tag com base nas permissões vinculadas em você atráves do LuckPerms.

### Exemplos de Uso

- Para adicionar o grupo `vip` a um jogador chamado `Steve`, use: `/group adicionar Steve vip`
- Para remover o grupo `mod` de um jogador chamado `Alex`, use: `/group remover Alex mod`
- Para aplicar diretamente a tag admin: `/tag admin`.

## Configuração

1. **Configuração de VIPs**: Configure quais grupos são considerados VIPs e ajuste os títulos e arquivo de configuração.
2. **Banco de dados**: Configure seu banco de dados para armazenar sua tag em tempo real.
3. **Criação de tags**: Crie quantas tags você desejar e vincule elas ao seu LuckPerms através de suas permissões.

## Dependências

- **Spigot API**: `1.8.8-R0.1-SNAPSHOT`
- **LuckPerms API**: `5.4`

## Instalação

1. Faça o download do arquivo JAR do plugin.
2. Coloque o arquivo JAR na pasta `plugins` do seu servidor Minecraft.
3. Reinicie o servidor para carregar o plugin.

## Contato

Para mais informações, você pode entrar em contato com o desenvolvedor através do **DISCORD**: `devlaas` ou criar uma issue no repositório.


