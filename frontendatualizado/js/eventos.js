
    // Função para buscar eventos e atualizar o HTML
    async function carregarEventos() {
        try {
            // Fazendo a requisição ao back-end
            const response = await fetch('/api/eventos');
            const eventos = await response.json();

            // Selecionando o container onde os eventos vão aparecer
            const container = document.querySelector('.eventos-lista');
            container.innerHTML = ''; // Limpa o conteúdo antes de adicionar os novos eventos

            // Iterando sobre os eventos e criando os elementos HTML
            eventos.forEach(evento => {
                const eventoHTML = `
                    <div class="evento-item">
                        <h4>${evento.titulo}</h4>
                        <p>Data: ${new Date(evento.data).toLocaleDateString('pt-BR')}</p>
                        <p>${evento.descricao || 'Sem descrição disponível'}</p>
                        <a href="inscricao.html" class="btn">${evento.status === 'aberto' ? 'Inscreva-se' : 'Fechado'}</a>
                    </div>
                `;
                container.innerHTML += eventoHTML; // Adiciona o evento ao container
            });
        } catch (error) {
            console.error('Erro ao carregar eventos:', error);
            const container = document.querySelector('.eventos-lista');
            container.innerHTML = '<p>Não foi possível carregar os eventos. Tente novamente mais tarde.</p>';
        }
    }

    // Carregar eventos ao abrir a página
    window.onload = carregarEventos;

