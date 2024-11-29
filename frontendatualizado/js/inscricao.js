document.addEventListener('DOMContentLoaded', () => {
    console.log("DOM completamente carregado e analisado.");

    const form = document.getElementById('registrationForm');
    const submitButton = document.getElementById('submitButton');

    if (!form) {
        console.error("Formulário não encontrado!");
        return;
    }

    if (!submitButton) {
        console.error("Botão de envio não encontrado!");
        return;
    }

    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        console.log("Evento de envio do formulário disparado.");

        // Capturar matrículas dos membros
        const matriculas = [
            document.getElementById('leaderMatricula')?.value || "",
            document.getElementById('member2')?.value || "",
            document.getElementById('member3')?.value || "",
            document.getElementById('member4')?.value || "",
            document.getElementById('member5')?.value || "",
            document.getElementById('member6')?.value || ""
        ];

        console.log("Matrículas capturadas:", matriculas);

        // Capturar nome da equipe
        const nomeEquipe = document.getElementById('teamName')?.value || "";
        console.log("Nome da equipe capturado:", nomeEquipe);

        if (matriculas.some(matricula => !matricula) || !nomeEquipe) {
            console.warn("Campos incompletos. Matrículas ou nome da equipe estão faltando.");
            alert("Por favor, preencha todos os campos!");
            return;
        }

        // Recuperar o ID do evento selecionado do localStorage
        const eventoId = localStorage.getItem('eventoSelecionado');
        if (!eventoId) {
            console.error("ID do evento não encontrado no localStorage!");
            alert("Erro ao identificar o evento. Tente novamente.");
            return;
        }
        console.log("ID do evento selecionado:", eventoId);

        // Dados do grupo a ser enviado
        const dadosTime = {
            membros: matriculas,
            nomeEquipe: nomeEquipe,
            nomeLider: matriculas[0]
        };
        console.log("Dados formatados para envio (grupo):", dadosTime);

        try {
            console.log("Enviando dados do grupo ao servidor...");
            const grupoResponse = await fetch("https://codeplac-c7hy.onrender.com/group/create", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(dadosTime)
            });

            if (!grupoResponse.ok) {
                const errorData = await grupoResponse.json();
                console.error("Erro ao criar grupo:", errorData);
                throw new Error(errorData.message || "Erro ao criar o grupo.");
            }

            const grupoData = await grupoResponse.json();
            const grupoId = grupoData.id; // ID do grupo criado
            console.log("Grupo criado com sucesso. ID:", grupoId);

            // Criar inscrições para cada membro da equipe
            const inscricoes = matriculas.map(matricula => ({
                codigoGrupo: grupoId,
                eventoId: eventoId,
                usuarioMatricula: matricula
            }));

            console.log("Dados de inscrição formatados:", inscricoes);

            console.log("Enviando inscrições ao servidor...");
            const inscricaoResponse = await fetch("https://codeplac-c7hy.onrender.com/registration/createAll", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(inscricoes)
            });

            if (!inscricaoResponse.ok) {
                const errorData = await inscricaoResponse.json();
                console.error("Erro ao criar inscrições:", errorData);
                throw new Error(errorData.message || "Erro ao criar as inscrições.");
            }

            console.log("Inscrições criadas com sucesso!");
            alert("Equipe e inscrições criadas com sucesso!");

            // Resetar o formulário após sucesso
            form.reset();
            console.log("Formulário resetado.");
        } catch (error) {
            console.error("Erro ao processar inscrição:", error);
            alert(`Erro: ${error.message}`);
        }
    });

    submitButton.addEventListener('click', (event) => {
        event.preventDefault();
        console.log("Botão de envio clicado.");
        form.dispatchEvent(new Event('submit'));
    });
});
