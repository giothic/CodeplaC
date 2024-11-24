document.addEventListener('DOMContentLoaded', () => {
    console.log("DOM completamente carregado e analisado."); // Log inicial

    const form = document.getElementById('registrationForm');
    const submitButton = document.getElementById('submitButton');

    if (form) {
        console.log("Formulário encontrado:", form);
    } else {
        console.error("Formulário não encontrado!");
        return; // Não continuar se o formulário não existir
    }

    if (submitButton) {
        console.log("Botão de envio encontrado:", submitButton);
    } else {
        console.error("Botão de envio não encontrado!");
        return; // Não continuar se o botão não existir
    }

    form.addEventListener('submit', async (event) => {
        event.preventDefault(); // Previne envio padrão
        console.log("Evento de envio do formulário disparado.");

        // Obtendo os valores das matrículas
        const matriculas = [
            document.getElementById('leaderMatricula')?.value || "",
            document.getElementById('member2')?.value || "",
            document.getElementById('member3')?.value || "",
            document.getElementById('member4')?.value || "",
            document.getElementById('member5')?.value || "",
            document.getElementById('member6')?.value || ""
        ];

        console.log("Matrículas capturadas:", matriculas);

        // Nome da equipe
        const nomeEquipe = document.getElementById('teamName')?.value || "";
        console.log("Nome da equipe capturado:", nomeEquipe);

        // Validação dos campos
        if (matriculas.some(matricula => !matricula) || !nomeEquipe) {
            console.warn("Alguns campos estão incompletos. Matrículas ou nome da equipe estão faltando.");
            alert("Por favor, preencha todos os campos!");
            return;
        }

        // Dados do time
        const dadosTime = {
            membros: matriculas.slice(1), // Todos exceto o líder
            nomeEquipe: nomeEquipe,
            nomeLider: matriculas[0] // O líder
        };
        console.log("Dados formatados para envio:", dadosTime);

        // Log do JSON enviado ao servidor
        console.log("JSON enviado ao backend:", JSON.stringify(dadosTime));

        // Tentativa de envio para o servidor
        try {
            console.log("Preparando para enviar dados ao servidor...");
            const response = await fetch("https://codeplac-c7hy.onrender.com/group/create", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(dadosTime)
            });

            console.log("Resposta recebida do servidor. Status:", response.status);

            if (response.ok) {
                const data = await response.json();
                console.log("Dados recebidos do servidor:", data);
                alert("Equipe inscrita com sucesso!");
                form.reset(); // Reseta o formulário após sucesso
                console.log("Formulário resetado após sucesso.");
            } else {
                const errorData = await response.json();
                console.error("Erro retornado pelo servidor:", errorData);
                throw new Error(errorData.message || "Falha na inscrição da equipe. Tente novamente.");
            }
        } catch (error) {
            console.error("Erro na tentativa de inscrição:", error);
            alert(`Erro ao enviar dados: ${error.message}`);
        }
    });

    // Evento para rastrear cliques no botão de envio
    submitButton.addEventListener('click', (event) => {
        event.preventDefault(); // Previne comportamento padrão
        console.log("Botão de envio clicado.");
        // Chamando a função principal (se necessário)
        form.dispatchEvent(new Event('submit')); // Simula envio do formulário
    });
});
