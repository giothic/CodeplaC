document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('registrationForm');
    const submitButton = document.getElementById('submitButton');
    const counterInfo = document.getElementById('counterInfo');

    const numberOfTeams = 5; 
    const maxTeams = 6;

    counterInfo.textContent = `Número de inscrições: ${numberOfTeams} de ${maxTeams}`;

    if (numberOfTeams >= maxTeams) {
        submitButton.disabled = true;
        counterInfo.textContent = `Número de inscrições: ${maxTeams} de ${maxTeams} (Limite atingido)`;
    }

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const matriculas = [
            document.getElementById('leaderMatricula').value,
            document.getElementById('member1').value,
            document.getElementById('member2').value,
            document.getElementById('member3').value,
            document.getElementById('member4').value,
            document.getElementById('member5').value,
            document.getElementById('member6').value
        ];

        const matriculaUsada = await checkMatriculas(matriculas);

        if (matriculaUsada) {
            alert('Uma das matrículas já foi usada em outro time!');
        } else {

            alert('Inscrição realizada com sucesso!');
        }
    });

    async function checkMatriculas(matriculas) {
   
        const matriculasExistentes = ['12345', '67890', '11111']; 
        return matriculas.some(matricula => matriculasExistentes.includes(matricula));
    }
});
