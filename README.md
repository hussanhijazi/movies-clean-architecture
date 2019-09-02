# Clean Architecture 

## Api
The Movie DB API - [https://api.themoviedb.org/3/](URL).

## Código
Estou usando *Clean Architecture* baseada na implementação do Antonio Leiva e da BufferApp. 

Na primeira tela busca os filmes dos seguintes gêneros: Ação, Drama, Fantasia e Ficção. No clique no filme, passamos o mesmo para a tela de detalhes, onde buscamos os trailers do filme e colocamos numa *RecyclerView*. Além disso temos a busca pelo filme na *toolbar*. 

As requisições de busca por gênero ou termo são salvas localmente, para serem usadas quando o usuário estiver sem internet.

### Módulos
#### domain
Nesse módulo temos os *models(entities*).

#### app
Nesse módulo temos as classes *Android* e a UI.

#### data
Nesse módulo temos os *interfaces* / *datasources*, api e repositórios.

#### cache
Nesse módulo fazemos as gravação dos dados *offline*, esse módulo depende do *Android*, pois usamos *Room* para gravação dos dados. Usamos um *mapper* para transformar entidade do *Room* para entidade da aplicação.

#### usecases
Nesse módulo temos os *usescases* / *interactors*.

## Alguns pontos

### Módulo Cache
Criamos o módulo *cache* para não colocar depedência do *Android* no módulo data. O módulo *data* tem uma *interface* para ser implementada no *cache*.

### Mapper Domain/Cache
Usamos um *mapper* para que o mapemento da *@Entity* do *Room* no módulo cache para a *entity* da aplicação. Assim evitando de passar *@Entity* onde os módulos que não tem *Android*.

### Mapper Domain/View
Como não podemos usar *Parcelable* no *domain*, usamos um *mapper* para passar o filme para a tela de detalhes como *Parcelable* do *Android*. 

### Koin
Usamos o *Koin* para injeção de depêndencias, por achar ele de simples uso e atende a necessidade.

### Integração Contínua
Usamos o Bitrise.io para integração contínua.

## Testes
Temos testes nos módulos: *app*, *cache*, *data*, e *usecases*.

### app
Nesse módulo testamos os *ViewModels* e alguns testes nas *Activities*.

### cache
Nesse módulo testamos a gravação e carregamento dos dados no *Room*.

### data
Nesse módulo testamos os repositórios.

### usecases
Nesse módulo testamos os *usescases*.

## Rodar testes
Teste unitários: `./scripts/tests.sh`

Teste de ui: `./scripts/androidTests.sh`

## Lint/Detekt
Rodar lint: `./scripts/lint.sh`

## Editorconfig
Temos um .editorconfig para *code style*.

## Referências
* Artigo: https://antonioleiva.com/clean-architecture-android/
* BufferApp: https://github.com/bufferapp/clean-architecture-components-boilerplate

