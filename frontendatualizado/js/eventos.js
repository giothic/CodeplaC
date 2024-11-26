window.addEventListener("DOMContentLoaded", getEventData);

function getEventData() {
	let eventData;

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
		.then((data) => (eventData = data))
		.catch((error) => {
			alert(error.message);
		})
		.finally(() => {
			renderEvents(eventData);
		});
}

function renderEvents(data = []) {
	const eventList = document.querySelector("#eventos-lista");

	if (data.length == 0) {
		return (eventList.innerHTML = `
			<div class="evento-item">
        <p>Não há eventos no momento!</p>
			</div>
		`);
	}

	data.forEach((event) => {
		eventList.innerHTML += `
      <div class="evento-item">
        <h4>${event.nome}</h4>
        <p>Data: ${new Date(event.dataEvento).toLocaleDateString("pt-BR")}</p>
        <p>
          Descrição: ${event.descricao}
        </p>
        <a href="https://www.codeplac.com.br/inscricao" class="btn">Inscreva sua equipe!</a>
			</div>
    `;
	});
}