import {api} from './api';

import { Usuario, CriarUsuarioDTO } from '../types/usuario';

//agrupa todas as requisições HTTP relacionadas a usuários em um único objeto, facilitando a manutenção e o uso dessas funções em outras partes do aplicativo.
export const usuarioService = {
//export : funciona como um public.
    //GET /usuarios
    listar: async (): Promise<Usuario[]> => {
        const response = await api.get<Usuario[]>('/usuarios');
        return response.data;
    },

    //POST /usuarios
    criar : async (dados: CriarUsuarioDTO): Promise<Usuario> => {
        const response = await api.post<Usuario>('/usuarios', dados);
        return response.data;
    },
};