window.addEventListener("DOMContentLoaded", getEventData);

function getEventData() {
    fetch("https://codeplac-c7hy.onrender.com/event/list", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Falha na busca de eventos.");
            }
        })
        .then((data) => {
            renderEvents(data);
        })
        .catch((error) => {
            alert(error.message);
        });
}

function renderEvents(data = []) {
    const eventList = document.querySelector("#eventos-lista");

    if (data.length === 0) {
        eventList.innerHTML = `
            <div class="evento-item">
                <p>Não há eventos no momento!</p>
            </div>
        `;
        return;
    }

    eventList.innerHTML = '';

    data.forEach((event) => {
        const eventDiv = document.createElement('div');
        eventDiv.classList.add('evento-item');
        eventDiv.innerHTML = `
            <h4>${event.nome}</h4>
            <p>Data: ${new Date(event.dataEvento).toLocaleDateString("pt-BR")}</p>
            <p>Local: ${event.lugar}</p>
            <p>Horário: ${event.periodo === "MATUTINO" ? "08:00" : "19:00"}</p>
            <p>Descrição: ${event.descricao}</p>
            ${event.tipoEvento === "PALESTRA" 
                ? `<button class="btn" disabled>Inscreva-se em breve!</button>` 
                : `<button class="btn btn-inscrever" data-id="${event.id}">Inscreva sua equipe!</button>`}
        `;

        eventList.appendChild(eventDiv);
    });


    document.querySelectorAll('.btn-inscrever').forEach((button) => {
        button.addEventListener('click', (event) => {
            const eventoId = event.target.getAttribute('data-id');
            console.log("ID do evento selecionado:", eventoId);

            localStorage.setItem('eventoSelecionado', eventoId);

            window.location.href = "https://www.codeplac.com.br/inscricao";
        });
    });
}
