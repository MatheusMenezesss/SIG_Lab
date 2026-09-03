export interface Categoria {
    id: string;
    nome: string;
  }
  
  export interface Equipamento {
    id: string;
    nome: string;
    descricao?: string;
    patrimonio?: string;
    categoria: Categoria;
    estoqueTotal: number;
    estoqueDisponivel: number;
    ativo: boolean;
  }