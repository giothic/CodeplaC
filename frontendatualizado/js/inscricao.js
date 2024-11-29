document.addEventListener('DOMContentLoaded', () => {
    console.log("DOM completamente carregado e analisado.");

    const form = document.getElementById('registrationForm');
    const submitButton = document.getElementById('submitButton');

    if (form) {
        console.log("Formulário encontrado:", form);
    } else {
        console.error("Formulário não encontrado!");
        return; 
    }

    if (submitButton) {
        console.log("Botão de envio encontrado:", submitButton);
    } else {
        console.error("Botão de envio não encontrado!");
        return; 
    }

    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        console.log("Evento de envio do formulário disparado.");
        
        const matriculas = [
            document.getElementById('leaderMatricula')?.value || "",
            document.getElementById('member2')?.value || "",
            document.getElementById('member3')?.value || "",
            document.getElementById('member4')?.value || "",
            document.getElementById('member5')?.value || "",
            document.getElementById('member6')?.value || ""
        ];

        console.log("Matrículas capturadas:", matriculas);

        const nomeEquipe = document.getElementById('teamName')?.value || "";
        console.log("Nome da equipe capturado:", nomeEquipe);

        if (matriculas.some(matricula => !matricula) || !nomeEquipe) {
            console.warn("Alguns campos estão incompletos. Matrículas ou nome da equipe estão faltando.");
            alert("Por favor, preencha todos os campos!");
            return;
        }

        const dadosTime = {
            membros: matriculas, 
            nomeEquipe: nomeEquipe,
            nomeLider: matriculas[0] 
        };
        console.log("Dados formatados para envio:", dadosTime);

        console.log("JSON enviado ao backend:", JSON.stringify(dadosTime));

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
                form.reset();
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

    submitButton.addEventListener('click', (event) => {
        event.preventDefault();
        console.log("Botão de envio clicado.");
        form.dispatchEvent(new Event('submit'));
    });
});
