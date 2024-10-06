function toggleFormFields() {
    const tipoConta = document.querySelector('.select-selected').getAttribute('data-value'); // Obter o valor selecionado
    const userFields = document.getElementById('user-fields');
    const adminFields = document.getElementById('admin-fields');
    const authFields = document.getElementById('auth-fields');

    userFields.classList.add('hidden');
    adminFields.classList.add('hidden');
    authFields.classList.add('hidden');

    if (tipoConta === 'usuario') {
        userFields.classList.remove('hidden');
        authFields.classList.remove('hidden');
    } else if (tipoConta === 'adm') {
        adminFields.classList.remove('hidden');
        authFields.classList.remove('hidden');
    }
}

document.addEventListener("DOMContentLoaded", function() {
    const selected = document.querySelector(".select-selected");
    const items = document.querySelector(".select-items");
    
    selected.addEventListener("click", function() {
        items.classList.toggle("select-hide");
    });

    items.querySelectorAll("div").forEach(item => {
        item.addEventListener("click", function() {
            selected.textContent = this.textContent; // Altera o texto do selecionado
            selected.setAttribute("data-value", this.getAttribute("data-value")); // Salva o valor selecionado
            items.classList.add("select-hide"); // Fecha as opções

            toggleFormFields(); // Chama a função para mostrar/ocultar os campos
        });
    });

    document.addEventListener("click", function(event) {
        if (!selected.contains(event.target) && !items.contains(event.target)) {
            items.classList.add("select-hide"); // Fecha o dropdown se clicar fora
        }
    });
});

function cadastrar() {
    const tipoConta = document.querySelector('.select-selected').getAttribute('data-value');
    let dadosCadastro = {};

    if (!tipoConta) {
        alert("Por favor, selecione o tipo de conta!");
        return;
    }

    if (tipoConta === "usuario") {
        const matricula = document.getElementById("matricula").value;
        const nome = document.getElementById("nome").value;
        const sobrenome = document.getElementById("sobrenome").value;
        const email = document.getElementById("email").value;
        const cpf = document.getElementById("cpf").value;
        const telefone = document.getElementById("telefone").value;
        const senha = document.getElementById("senha").value;

        if (!matricula || !nome || !sobrenome || !email || !cpf || !telefone || !senha) {
            alert("Por favor, preencha todos os campos!");
            return;
        }

        dadosCadastro = {
            matricula: matricula,
            nome: nome,
            sobrenome: sobrenome,
            tipoUser: "PARTICIPANT",
            cpf: cpf,
            email: email,
            telefone: telefone,
            senha: senha
        };

    } else if (tipoConta === "adm") {
        const matricula = document.getElementById("matriculaAdm").value;
        const nome = document.getElementById("nomeAdm").value;
        const sobrenome = document.getElementById("sobrenomeAdm").value;
        const email = document.getElementById("email").value;
        const cpf = document.getElementById("cpf").value;
        const telefone = document.getElementById("telefone").value;
        const senha = document.getElementById("senha").value;

        if (!nome || !matricula || !email || !cpf || !telefone || !senha) {
            alert("Por favor, preencha todos os campos!");
            return;
        }

        dadosCadastro = {
            matricula: matricula,
            nome: nome,
            sobrenome: sobrenome,
            tipoUser: "ADMIN",
            cpf: cpf,
            email: email,
            telefone: telefone,
            senha: senha
        };
    }

    console.log("Dados de cadastro:", JSON.stringify(dadosCadastro));
    console.log("Enviando requisição para o servidor...");

    fetch("http://localhost:8080/auth/register", {
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

document.getElementById('cadastrarButton').addEventListener('click', function(event) {
    event.preventDefault();
    cadastrar();
});
