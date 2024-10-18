function cadastrar() {
    const matricula = document.getElementById("matricula").value;
    const nome = document.getElementById("nome").value;
    const sobrenome = document.getElementById("sobrenome").value;
    const email = document.getElementById("email").value;
    const cpf = document.getElementById("cpf").value;
    const telefone = document.getElementById("telefone").value;
    const senha = document.getElementById("senha").value;

    // Verificar se todos os campos estão preenchidos
    if (!matricula || !nome || !sobrenome || !email || !cpf || !telefone || !senha) {
        alert("Por favor, preencha todos os campos!");
        return;
    }

    // Dados para enviar
    const dadosCadastro = {
        matricula: matricula,
        nome: nome,
        sobrenome: sobrenome,
        tipoUser: "PARTICIPANT", // Definido manualmente como participante
        cpf: cpf,
        email: email,
        telefone: telefone,
        senha: senha
    };

    // Exibir no console os dados de cadastro
    console.log("Dados de cadastro:", JSON.stringify(dadosCadastro));
    console.log("Enviando requisição para o servidor...");

    // Fazer a requisição POST para o servidor
    fetch("https://codeplac-c7hy.onrender.com/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dadosCadastro)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            return response.json().then(err => {
                throw new Error(err.message || "Falha no cadastro. Verifique os dados.");
            });
        }
    })
    .then(data => {
        alert("Cadastro bem-sucedido! Redirecionando...");
        window.location.href = "https://www.codeplac.com.br/";
    })
    .catch(error => {
        console.error("Erro na requisição:", error);
        alert(error.message);
    });
}

// Adicionar evento ao botão de cadastro
document.getElementById('cadastrarButton').addEventListener('click', function(event) {
    event.preventDefault();
    cadastrar();
});
