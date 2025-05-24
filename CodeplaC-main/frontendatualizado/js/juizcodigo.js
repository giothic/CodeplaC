window.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formJuizCodigo");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        // Limpa mensagens anteriores
        document.querySelectorAll('.error').forEach(span => span.textContent = '');

        const nomeEquipe = document.getElementById('nomeEquipe').value.trim();
        const numeroCodigo = document.getElementById('numeroCodigo').value.trim();
        const nomeLider = document.getElementById('nomeLider').value.trim();
        const codigo = document.getElementById('codigo').value.trim();

        let hasError = false;

        if (!nomeEquipe) {
            showError('nomeEquipe', 'Nome da equipe é obrigatório');
            hasError = true;
        }
        if (!numeroCodigo) {
            showError('numeroCodigo', 'Número do código é obrigatório');
            hasError = true;
        } else if (isNaN(numeroCodigo) || parseInt(numeroCodigo) <= 0) {
            showError('numeroCodigo', 'Número do código deve ser um número válido');
            hasError = true;
        }
        if (!nomeLider) {
            showError('nomeLider', 'Nome do líder é obrigatório');
            hasError = true;
        }
        if (!codigo) {
            showError('codigo', 'O código é obrigatório');
            hasError = true;
        }

        if (hasError) {
            return;
        }

        const formData = {
            nomeEquipe,
            numeroCodigo: parseInt(numeroCodigo),
            nomeLider,
            codigo
        };

        fetch("http://localhost:8080/juizcodigo", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        })
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    return response.json().then(errorData => {
                        throw new Error(errorData.message || "Erro ao enviar o código, tente novamente");
                    });
                }
            })
            .then((data) => {
                alert("Código enviado com sucesso!");
                form.reset();
            })
            .catch((error) => {
                alert(error.message);
                console.error("Erro:", error);
            });
    });

    function showError(fieldId, message) {
        let errorSpan = document.getElementById(fieldId + 'Error');
        if (!errorSpan) {
            errorSpan = document.createElement('span');
            errorSpan.id = fieldId + 'Error';
            errorSpan.className = 'error';
            const field = document.getElementById(fieldId);
            field.parentNode.insertBefore(errorSpan, field.nextSibling);
        }
        errorSpan.textContent = message;
    }
});
