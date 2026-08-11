backend/

src/

pom.xml

Dockerfile

README.md

Dentro do Java:

src/main/java/br/ufpe/siglab/

config/

controller/

service/

repository/

domain/

dto/

mapper/

exception/

security/

validation/

util/

Observe que utilizei domain em vez de entity.

Isso força você a pensar primeiro no domínio.

Dentro de domain:

domain/

usuario/

equipamento/

categoria/

solicitacao/

historico/

Cada módulo possui sua própria organização.

Por exemplo:

solicitacao/

Solicitacao.java

SolicitacaoRepository.java

SolicitacaoService.java

SolicitacaoController.java

SolicitacaoMapper.java

dto/

padrão reconhecido como :package by feature