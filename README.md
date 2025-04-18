# Venix Group Plugin

O **Venix Group Plugin** é um plugin para Minecraft desenvolvido para facilitar o gerenciamento de grupos e permissões utilizando a API do LuckPerms. Este plugin oferece uma maneira eficiente e personalizada de gerenciar permissões e status de jogadores em seu servidor Minecraft.

## Funcionalidades

- **Gerenciamento de Grupos**: Adicione e remova grupos de jogadores com comandos simples.
- **Compatibilidade**: Funciona com a API do LuckPerms para uma integração completa com o sistema de permissões do servidor.

## Comandos

- `/group <adicionar/remover> <player> <group>`: Adiciona ou remove um grupo de um jogador.
  - **Adicionar**: Concede o grupo ao jogador e pode ativar efeitos VIP.
  - **Remover**: Remove o grupo do jogador.

### Exemplos de Uso

- Para adicionar o grupo `vip` a um jogador chamado `Steve`, use: `/group adicionar Steve vip`
- Para remover o grupo `mod` de um jogador chamado `Alex`, use: `/group remover Alex mod`


## Configuração

1. **Configuração de VIPs**: Configure quais grupos são considerados VIPs e ajuste os títulos e arquivo de configuração.

## Dependências

- **Spigot API**: `1.8.8-R0.1-SNAPSHOT`
- **LuckPerms API**: `5.4`

## Instalação

1. Faça o download do arquivo JAR do plugin.
2. Coloque o arquivo JAR na pasta `plugins` do seu servidor Minecraft.
3. Reinicie o servidor para carregar o plugin.

## Contato

Para mais informações, você pode entrar em contato com o desenvolvedor através do **DISCORD**: `devlaas` ou criar uma issue no repositório.


