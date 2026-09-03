import axios from 'axios';

export const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor para injetar o JWT no cabeçalho Authorization
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('@SIGLab:token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Interceptor para tratar expiração de sessão
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('@SIGLab:token');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);