package br.com.siglab.API_siglab.domain.solicitacao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;

    
    @Autowired
    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository){
        this.solicitacaoRepository = solicitacaoRepository;
    }

    //1. Salvar uma nova solicitação
    public Solicitacao createSolicitacao(Solicitacao solicitacao){
        if (solicitacao.getUsuarioId() == null || solicitacao.getEquipamentoId() == null) {
            throw new IllegalArgumentException("Usuário e equipamento são obrigatórios.");
        }

        return solicitacaoRepository.save(solicitacao);
    }
    
    //2. Buscar solicitacao por ID
    public Solicitacao getSolicitacaoById(UUID id){
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);
        //CrudRepository<Solicitacao> solicitacaoOptional;
        //retorna o objeto se existir. caso contrário retorna um objeto nulo.
        return solicitacaoOptional.orElseThrow(() -> 
            new IllegalArgumentException("Solicitação não encontrada para o ID: " + id));
    }
    
    //3. Lista todas as solicitações
    public List<Solicitacao> getAllSolicitacoes(){
        return solicitacaoRepository.findAll();
    }

    //4.  Atualizar solicitacao
    public Solicitacao atualizarSolicitacao(UUID id, Solicitacao updateSolicitacao) {
        // Busca a solicitação existente
        Solicitacao solicitacaoExistente = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada para o ID: " + id));

        // Atualiza os campos necessários
        solicitacaoExistente.setStatus(updateSolicitacao.getStatus());
        solicitacaoExistente.setAdministradorId(updateSolicitacao.getAdministradorId());
        solicitacaoExistente.setMotivoResposta(updateSolicitacao.getMotivoResposta());
        solicitacaoExistente.setDataResposta(updateSolicitacao.getDataResposta());

        // Salva a solicitação atualizada no banco de dados
        return solicitacaoRepository.save(solicitacaoExistente);
    }

    //5. Deletar Solicitacao
    public void deletarSolicitacao(UUID id) {
        try {
            solicitacaoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Solicitação não encontrada para o ID: " + id);
        }
    }
    
}