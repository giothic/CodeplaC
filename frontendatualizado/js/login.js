
function login() {

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    if (!username || !password) {
        alert("Por favor, preencha todos os campos!");
        return;
    }

    fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            matricula: username, 
            password: password  
        })
    })
    .then(response => {
        if (response.ok) {
            return response.json(); 
        } else {
            throw new Error("Falha no login. Verifique suas credenciais.");
        }
    })
    .then(data => {

        alert("Login bem-sucedido! Redirecionando...");
        window.location.href = "https://www.codeplac.com.br/"; 
    })
    .catch(error => {

        alert(error.message);
    });
}

document.getElementById('loginButton').addEventListener('click', function(event) {
    event.preventDefault(); 
    login(); 
});
