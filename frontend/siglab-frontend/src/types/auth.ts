export type PerfilUsuario = 'USER' | 'ADMIN';

export interface Usuario {
    id: string;
    nome: string;
    email: string;
    perfil: PerfilUsuario;
    ativo: boolean;
}

export interface AuthResponse {
    token: string;
    usuario: Usuario;
  }