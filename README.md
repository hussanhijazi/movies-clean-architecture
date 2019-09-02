# Clean Architecture

## Código
Estou usando *Clean Architecture* baseada na implementação do Antonio Leiva e da BufferApp.

### Módulos
#### domain
Nesse módulo temos os models(*entities*).

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
Como não podemos usar *Parcelable* no *domain*, usamos um *mapper* para passar o objeto filme para a tela de detalhes como Parcelable do Android. 

### Koin
Usamos o *Koin* para injeção de depêndencias, por achar ele de simples uso e atende a necessidade.

### Integração Contínua
Usamos o Bitrise.io para integração contínua.

## Testes
Temos testes nos módulos: *app*, *cache*, *data*, e *usecases*.

### app
Nesse módulo testamos os *ViewModels*

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

## Referências
* Artigo: https://antonioleiva.com/clean-architecture-android/
* BufferApp: https://github.com/bufferapp/clean-architecture-components-boilerplate

