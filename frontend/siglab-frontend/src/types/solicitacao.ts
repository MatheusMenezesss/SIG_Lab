import { Usuario } from './auth';
import { Equipamento } from './equipamento';

export type StatusSolicitacao = 'PENDENTE' | 'APROVADA' | 'REJEITADA';

export interface Solicitacao {
  id: string;
  usuario: Usuario;
  equipamento: Equipamento;
  administrador?: Usuario;
  status: StatusSolicitacao;
  finalidade: string;
  dataSolicitacao: string;
  dataResposta?: string;
  motivoResposta?: string;
}

export interface SolicitacaoCreateDTO {
  equipamentoId: string;
  finalidade: string;
}