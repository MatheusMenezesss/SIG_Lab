import React, { useState, useEffect } from 'react';
import { usuarioService } from '../services/usuarioService';
import { Usuario, CriarUsuarioDTO, PerfilUsuario } from '../types/usuario';

export const UsuariosPage = () => {
  const [usuarios, setUsuarios] = useState<Usuario[]>([]);
  const [loading, setLoading] = useState(false);
  const [erro, setErro] = useState<string | null>(null);

  // Estado do formulário
  const [formData, setFormData] = useState<CriarUsuarioDTO>({
    nome: '',
    email: '',
    senha: '',
    perfil: 'USER',
  });

  // Carrega os usuários ao abrir a tela
  useEffect(() => {
    carregarUsuarios();
  }, []);

  const carregarUsuarios = async () => {
    try {
      setLoading(true);
      const dados = await usuarioService.listar();
      setUsuarios(dados);
    } catch (err) {
      setErro('Erro ao carregar lista de usuários.');
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setErro(null);

    try {
      await usuarioService.criar(formData);
      // Limpa os campos após o sucesso
      setFormData({ nome: '', email: '', senha: '', perfil: 'USER' });
      // Recarrega a listagem atualizada
      carregarUsuarios();
    } catch (err: any) {
      setErro(err.response?.data?.message || 'Falha ao cadastrar usuário.');
    }
  };

  return (
    <div className="max-w-4xl mx-auto p-6">
      <h1 className="text-2xl font-bold text-gray-800 mb-6">Gerenciamento de Usuários - SIG_Lab</h1>

      {/* Formulário de Cadastro */}
      <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg shadow-md mb-8 border border-gray-100">
        <h2 className="text-lg font-semibold text-gray-700 mb-4">Novo Usuário</h2>
        
        {erro && <div className="p-3 mb-4 bg-red-100 text-red-700 rounded-md text-sm">{erro}</div>}

        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          <div>
            <label className="block text-sm font-medium text-gray-600 mb-1">Nome</label>
            <input
              type="text"
              name="nome"
              value={formData.nome}
              onChange={handleChange}
              required
              className="w-full border border-gray-300 px-3 py-2 rounded-md focus:outline-blue-500"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-600 mb-1">E-mail</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
              className="w-full border border-gray-300 px-3 py-2 rounded-md focus:outline-blue-500"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-600 mb-1">Senha</label>
            <input
              type="password"
              name="senha"
              value={formData.senha}
              onChange={handleChange}
              required
              className="w-full border border-gray-300 px-3 py-2 rounded-md focus:outline-blue-500"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-600 mb-1">Perfil</label>
            <select
              name="perfil"
              value={formData.perfil}
              onChange={handleChange}
              className="w-full border border-gray-300 px-3 py-2 rounded-md focus:outline-blue-500 bg-white"
            >
              <option value="USER">USER</option>
              <option value="ADMIN">ADMIN</option>
              <option value="ALUNO">ALUNO</option>
            </select>
          </div>
        </div>

        <button
          type="submit"
          className="mt-6 w-full bg-blue-600 text-white font-medium py-2 rounded-md hover:bg-blue-700 transition"
        >
          Cadastrar Usuário
        </button>
      </form>

      {/* Tabela de Usuários */}
      <div className="bg-white p-6 rounded-lg shadow-md border border-gray-100">
        <h2 className="text-lg font-semibold text-gray-700 mb-4">Usuários Cadastrados</h2>

        {loading ? (
          <p className="text-gray-500 text-sm">Carregando...</p>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full text-left border-collapse">
              <thead>
                <tr className="border-b border-gray-200 text-sm font-semibold text-gray-600">
                  <th className="py-2">ID</th>
                  <th className="py-2">Nome</th>
                  <th className="py-2">E-mail</th>
                  <th className="py-2">Perfil</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-100 text-sm text-gray-700">
                {usuarios.map((u) => (
                  <tr key={u.id}>
                    <td className="py-3">{u.id}</td>
                    <td className="py-3 font-medium">{u.nome}</td>
                    <td className="py-3 text-gray-500">{u.email}</td>
                    <td className="py-3">
                      <span className="px-2 py-1 bg-blue-50 text-blue-700 rounded-full text-xs font-semibold">
                        {u.perfil}
                      </span>
                    </td>
                  </tr>
                ))}
                {usuarios.length === 0 && (
                  <tr>
                    <td colSpan={4} className="text-center py-6 text-gray-400">
                      Nenhum usuário cadastrado até o momento.
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};