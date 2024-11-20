document.addEventListener('DOMContentLoaded', function () {
    const eventModal = new bootstrap.Modal(document.getElementById('eventModal'));
    const openModalBtn = document.getElementById('openModalBtn');
    const modalFooterBtn = document.getElementById('modalFooterBtn');


    window.onload = function () {
        eventModal.show();
    };

    const lastModalTime = localStorage.getItem('lastModalTime');
    const currentTime = new Date().getTime();
    const showModalAndButton = () => {
        eventModal.show();
    };
    if (!lastModalTime || (currentTime - lastModalTime > 3 * 60 * 60 * 1000)) {
        showModalAndButton();
        localStorage.setItem('lastModalTime', currentTime);
    }
    eventModal._element.addEventListener('hidden.bs.modal', function () {
        modalFooterBtn.style.display = 'block';
    });
    openModalBtn.addEventListener('click', function () {
        eventModal.show();
        modalFooterBtn.style.display = 'none';
    });
});
// Adicionando a funcionalidade de alternância (ligar/desligar)
const button = document.getElementById('openModalBtn');
button.addEventListener('click', () => {
    button.classList.toggle('active'); // Alterna a classe 'active' no botão
});
document.getElementById('openModalBtn').addEventListener('click', function () {
    // Ativa a classe de explosão
    this.classList.add('exploding');

    // Depois que a animação termina, removemos a classe para permitir outro clique
    setTimeout(() => {
        this.classList.remove('exploding');
    }, 400); // O tempo deve ser igual ao da animação (0.4s)
});