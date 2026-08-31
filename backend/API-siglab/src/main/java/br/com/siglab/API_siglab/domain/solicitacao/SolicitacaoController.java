package br.com.siglab.API_siglab.domain.solicitacao;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //indica que a classe recebe requisições HTTP e retorna respostas HTTP
@RequestMapping("/solicitacoes") //indica que a classe é um controlador REST e define o caminho base para as rotas do controlador
public class SolicitacaoController {
    // O Controller chama o Service, onde fica a regra de negócio e o acesso ao banco
    @Autowired
    private final SolicitacaoService solicitacaoService;

    //Constructor para injetar a dependência do SolicitacaoService
    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    //1. Create 
    @PostMapping
    public ResponseEntity<Solicitacao> createSplicitacao(@RequestBody Solicitacao solicitacao) {
        // Lógica para criar uma nova solicitação
        Solicitacao newSolicitacao = solicitacaoService.createSolicitacao(solicitacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSolicitacao);
    }

    //2. READ (por id) - busca solicitacao especifica
    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> getSolicitacaoById(@PathVariable("id") UUID id) {
        // Lógica para buscar a solicitação pelo ID
        Solicitacao solicitacao = solicitacaoService.getSolicitacaoById(id);
        //se existir retorna a solicitacao, caso ontrario retorna not found 
        if (solicitacao != null) {
            return ResponseEntity.ok(solicitacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //3. READ (todas as solicitacoes) 
    @GetMapping
    public ResponseEntity<List<Solicitacao>> getAllSolicitacoes() {
        List<Solicitacao> solicitacoes = solicitacaoService.getAllSolicitacoes();
        return ResponseEntity.ok(solicitacoes);
     }
    //4. UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Solicitacao> updateSolicitacaoById(@PathVariable("id") UUID id, @RequestBody Solicitacao updateSolicitacao) {
        // Lógica para atualizar a solicitação pelo ID
        Solicitacao updatedSolicitacao = solicitacaoService.atualizarSolicitacao(id, updateSolicitacao);
        if (updatedSolicitacao != null){
            return ResponseEntity.ok(updatedSolicitacao);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    //5. DELETE
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deletarSolicitacao(@PathVariable("id") UUID id){
        solicitacaoService.deletarSolicitacao(id);
        return ResponseEntity.noContent().build();
    }
}