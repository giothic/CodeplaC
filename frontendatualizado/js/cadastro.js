function cadastrar() {
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

    const dadosCadastro = {
        matricula: matricula,
        nome: nome,
        sobrenome: sobrenome,
        tipoUser: "PARTICIPANT",
        cpf: cpf,
        email: email,
        telefone: telefone,
        senha: senha
    };

    console.log("Dados de cadastro:", JSON.stringify(dadosCadastro));
    console.log("Enviando requisição para o servidor...");

    fetch("https://codeplac-c7hy.onrender.com/users/register", {
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

document.addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {  
        event.preventDefault();
        cadastrar();
    }
});

function validarEmail(email) {
    const regex = /^[\w.-]+@[a-zA-Z\d.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}

document.getElementById('cpf').addEventListener('input', function(event) {
    let cpf = event.target.value;
    cpf = cpf.replace(/\D/g, ''); // Remove todos os caracteres não numéricos

    if (cpf.length <= 3) {
        cpf = cpf.replace(/(\d{0,3})/, '$1');
    } else if (cpf.length <= 6) {
        cpf = cpf.replace(/(\d{3})(\d{0,3})/, '$1.$2');
    } else if (cpf.length <= 9) {
        cpf = cpf.replace(/(\d{3})(\d{3})(\d{0,3})/, '$1.$2.$3');
    } else {
        cpf = cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{0,2})/, '$1.$2.$3-$4');
    }
    
    // Atualiza o campo
    event.target.value = cpf;

    const erroCpf = document.getElementById('erroCpf');
    const cpfPattern = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;
    if (!cpfPattern.test(cpf)) {
        event.target.classList.add('error');
        erroCpf.style.display = 'block'; // Exibe a mensagem de erro
    } else {
        event.target.classList.remove('error');
        erroCpf.style.display = 'none'; // Esconde a mensagem de erro
    }
});

document.getElementById('telefone').addEventListener('input', function(event) {
    let telefone = event.target.value;
    telefone = telefone.replace(/\D/g, ''); // Remove todos os caracteres não numéricos

    if (telefone.length <= 2) {
        telefone = telefone.replace(/(\d{0,2})/, '($1');
    } else if (telefone.length <= 6) {
        telefone = telefone.replace(/(\d{2})(\d{0,4})/, '($1) $2');
    } else {
        telefone = telefone.replace(/(\d{2})(\d{4})(\d{0,4})/, '($1) $2-$3');
    }

    // Atualiza o campo
    event.target.value = telefone;

    const erroTelefone = document.getElementById('erroTelefone');
    const telefonePattern = /^\(?\d{2}\)?\s?\d{4,5}-\d{4}$/;
    if (!telefonePattern.test(telefone)) {
        event.target.classList.add('error');
        erroTelefone.style.display = 'block'; // Exibe a mensagem de erro
    } else {
        event.target.classList.remove('error');
        erroTelefone.style.display = 'none'; // Esconde a mensagem de erro
    }
});

document.getElementById('matricula').addEventListener('input', function(event) {
    const matricula = event.target.value;
    const erroMatricula = document.getElementById('erroMatricula');
    if (!/^\d{7}$/.test(matricula)) {
        event.target.classList.add('error');
        erroMatricula.style.display = 'block'; // Exibe a mensagem de erro
    } else {
        event.target.classList.remove('error');
        erroMatricula.style.display = 'none'; // Esconde a mensagem de erro
    }
});

document.getElementById('email').addEventListener('input', function(event) {
    const email = event.target.value;
    const erroEmail = document.getElementById('erroEmail');
    const emailPattern = /^[\w.-]+@[a-zA-Z\d.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(email)) {
        event.target.classList.add('error');
        erroEmail.style.display = 'block'; // Exibe a mensagem de erro
    } else {
        event.target.classList.remove('error');
        erroEmail.style.display = 'none'; // Esconde a mensagem de erro
    }
});
