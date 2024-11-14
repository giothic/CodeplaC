window.addEventListener("DOMContentLoaded", getUserData);

function getUserData() {
	const userMatricula = localStorage.getItem("matricula");
	const userToken = localStorage.getItem("token");

	fetch(`https://codeplac-c7hy.onrender.com/users/${userMatricula}`, {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
			Authorization: `Bearer ${userToken}`,
		},
	})
		.then((response) => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error("Falha na busca de informações do usuário.");
			}
		})
		.then((data) => {
			renderUserData(data);
		})
		.catch((error) => {
			alert(error.message);
		});
}

function renderUserData({ nome, sobrenome, matricula, email, telefone, cpf }) {
	document.getElementById("userName").textContent = `${nome} ${sobrenome}`;

	document.getElementById("userRegistry").textContent =
		`Matrícula ${matricula}`;

	document.getElementById("userEmail").textContent = `E-mail: ${email}`;

	document.getElementById("userPhone").textContent = `Telefone: ${telefone}`;

	document.getElementById("userCpf").textContent = `CPF: ${cpf}`;
}
