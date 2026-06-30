const API_BASE = 'http://localhost:8080';

async function apiFetch(path, options = {}) {
    const url = `${API_BASE}${path}`;
    const defaults = {
        headers: { 'Content-Type': 'application/json' }
    };
    const config = { ...defaults, ...options };
    if (options.headers) {
        config.headers = { ...defaults.headers, ...options.headers };
    }

    try {
        const response = await fetch(url, config);
        if (!response.ok) {
            const error = await response.json().catch(() => ({ message: 'Erro desconhecido' }));
            throw new Error(error.message || `Erro ${response.status}`);
        }
        if (response.status === 204) return null;
        return await response.json();
    } catch (err) {
        throw err;
    }
}

// TipoItem
const TipoItemAPI = {
    listar: () => apiFetch('/api/tipos-item'),
    buscar: (id) => apiFetch(`/api/tipos-item/${id}`),
    salvar: (data) => apiFetch('/api/tipos-item', { method: 'POST', body: JSON.stringify(data) }),
    atualizar: (id, data) => apiFetch(`/api/tipos-item/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    deletar: (id) => apiFetch(`/api/tipos-item/${id}`, { method: 'DELETE' }),
};

// ItemCardapio
const ItemCardapioAPI = {
    listar: () => apiFetch('/api/itens-cardapio'),
    buscar: (id) => apiFetch(`/api/itens-cardapio/${id}`),
    salvar: (data) => apiFetch('/api/itens-cardapio', { method: 'POST', body: JSON.stringify(data) }),
    atualizar: (id, data) => apiFetch(`/api/itens-cardapio/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    deletar: (id) => apiFetch(`/api/itens-cardapio/${id}`, { method: 'DELETE' }),
};

// Configuracao
const ConfiguracaoAPI = {
    listar: () => apiFetch('/api/configuracoes'),
    buscarHoje: () => apiFetch('/api/configuracoes/hoje'),
    salvar: (data) => apiFetch('/api/configuracoes', { method: 'POST', body: JSON.stringify(data) }),
};

// Usuario
const UsuarioAPI = {
    listar: () => apiFetch('/api/usuarios'),
    buscar: (id) => apiFetch(`/api/usuarios/${id}`),
    salvar: (data) => apiFetch('/api/usuarios', { method: 'POST', body: JSON.stringify(data) }),
    atualizar: (id, data) => apiFetch(`/api/usuarios/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    deletar: (id) => apiFetch(`/api/usuarios/${id}`, { method: 'DELETE' }),
};

// Cozinha
const CozinhaAPI = {
    listarSolicitados: () => apiFetch('/api/cozinha/solicitados'),
    listarEmPreparo: () => apiFetch('/api/cozinha/em-preparo'),
    receber: (id) => apiFetch(`/api/cozinha/${id}/receber`, { method: 'PATCH' }),
    entregar: (id) => apiFetch(`/api/cozinha/${id}/entregar`, { method: 'PATCH' }),
};

// Entregas
const EntregaAPI = {
    listarProntos: () => apiFetch('/api/entregas/prontos'),
    listarEmEntrega: () => apiFetch('/api/entregas/em-entrega'),
    receber: (id) => apiFetch(`/api/entregas/${id}/receber`, { method: 'PATCH' }),
    entregar: (id) => apiFetch(`/api/entregas/${id}/entregar`, { method: 'PATCH' }),
};

// Contas
const ContaAPI = {
    listar: () => apiFetch('/api/contas'),
    buscar: (id) => apiFetch(`/api/contas/${id}`),
    salvar: (data) => apiFetch('/api/contas', { method: 'POST', body: JSON.stringify(data) }),
    atualizar: (id, data) => apiFetch(`/api/contas/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    deletar: (id) => apiFetch(`/api/contas/${id}`, { method: 'DELETE' }),
};

// ItensDaConta
const ItensDaContaAPI = {
    listarPorConta: (contaId) => apiFetch(`/api/itens-da-conta/conta/${contaId}`),
    lancar: (data) => apiFetch('/api/itens-da-conta', { method: 'POST', body: JSON.stringify(data) }),
};