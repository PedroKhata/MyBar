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

        // Se for 204 (No Content - Exclusão), não tenta converter para JSON
        if (response.status === 204) return null;

        const data = await response.json();

        if (!response.ok) {
            const errorMessage = data.message || `Erro ${response.status}`;
            throw new Error(errorMessage);
        }

        return data;
    } catch (err) {
        // Log interno para ajudar no debug do console do navegador
        console.error(`[API Error] ${options.method || 'GET'} ${path}:`, err.message);
        throw err;
    }
}

const TipoItemAPI = {
    listar: () => apiFetch('/api/tipos-item'),
    buscar: (codigo) => apiFetch(`/api/tipos-item/${codigo}`),
    salvar: (data) => apiFetch('/api/tipos-item', { method: 'POST', body: JSON.stringify(data) }),
    atualizar: (codigo, data) => apiFetch(`/api/tipos-item/${codigo}`, { method: 'PUT', body: JSON.stringify(data) }),
    deletar: (codigo) => apiFetch(`/api/tipos-item/${codigo}`, { method: 'DELETE' })
};

const ItemCardapioAPI = {
    listar: () => apiFetch('/api/itens-cardapio'),
    buscar: (codigo) => apiFetch(`/api/itens-cardapio/${codigo}`),
    salvar: (data) => apiFetch('/api/itens-cardapio', { method: 'POST', body: JSON.stringify(data) }),
    atualizar: (codigo, data) => apiFetch(`/api/itens-cardapio/${codigo}`, { method: 'PUT', body: JSON.stringify(data) }),
    deletar: (codigo) => apiFetch(`/api/itens-cardapio/${codigo}`, { method: 'DELETE' })
};

const ConfiguracaoAPI = {
    listarTodas: () => apiFetch('/api/configuracoes'),
    buscarHoje: () => apiFetch('/api/configuracoes/hoje'),
    salvar: (data) => apiFetch('/api/configuracoes', { method: 'POST', body: JSON.stringify(data) })
};

const UsuarioAPI = {
    listar: () => apiFetch('/api/usuarios'),
    buscar: (codigo) => apiFetch(`/api/usuarios/${codigo}`),
    salvar: (data) => apiFetch('/api/usuarios', { method: 'POST', body: JSON.stringify(data) }),
    atualizar: (codigo, data) => apiFetch(`/api/usuarios/${codigo}`, { method: 'PUT', body: JSON.stringify(data) }),
    deletar: (codigo) => apiFetch(`/api/usuarios/${codigo}`, { method: 'DELETE' })
};

const ClienteAPI = {
    listar: () => apiFetch('/api/clientes'),
    cadastrar: (data) => apiFetch('/api/clientes', { method: 'POST', body: JSON.stringify(data) }),
    deletar: (id) => apiFetch(`/api/clientes/${id}`, { method: 'DELETE' })
};

const ContaAPI = {
    listarAbertas: () => apiFetch('/api/contas/abertas'),
    abrir: (data) => apiFetch('/api/contas', { method: 'POST', body: JSON.stringify(data) }),
    fechar: (id) => apiFetch(`/api/contas/${id}/fechar`, { method: 'PATCH' })
};

const ItensDaContaAPI = {
    listarPorConta: (contaId) => apiFetch(`/api/itens-da-conta/conta/${contaId}`),
    lancar: (data) => apiFetch('/api/itens-da-conta', { method: 'POST', body: JSON.stringify(data) }),
    cancelarItem: (id, data) => apiFetch(`/api/itens-da-conta/${id}/cancelar`, { method: 'PATCH', body: JSON.stringify(data) })
};

const PagamentoAPI = {
    listarPorConta: (contaId) => apiFetch(`/api/pagamentos/conta/${contaId}`),
    lancar: (data) => apiFetch('/api/pagamentos', { method: 'POST', body: JSON.stringify(data) }),
    cancelar: (id, data) => apiFetch(`/api/pagamentos/${id}/cancelar`, { method: 'PATCH', body: JSON.stringify(data) })
};

const RelatorioAPI = {
    faturamento: (inicio, fim) => apiFetch(`/api/relatorios/faturamento?inicio=${inicio}&fim=${fim}`)
};

const CozinhaAPI = {
    listarSolicitados: () => apiFetch('/api/cozinha/solicitados'),
    listarEmPreparo: () => apiFetch('/api/cozinha/em-preparo'),
    receber: (id) => apiFetch(`/api/cozinha/${id}/receber`, { method: 'PATCH' }),
    entregar: (id) => apiFetch(`/api/cozinha/${id}/entregar`, { method: 'PATCH' })
};

const EntregaAPI = {
    listarProntos: () => apiFetch('/api/entregas/prontos'),
    listarEmEntrega: () => apiFetch('/api/entregas/em-entrega'),
    receber: (id) => apiFetch(`/api/entregas/${id}/receber`, { method: 'PATCH' }),
    entregar: (id) => apiFetch(`/api/entregas/${id}/entregar`, { method: 'PATCH' })
};