// Função da galeria
const lightbox = document.getElementById("lightbox");
const lightboxImage = document.getElementById("lightbox-img");
const galleryItems = document.querySelectorAll(".gallery-item img");

let currentIndex = 0; // Índice da imagem atual

// Função para abrir a lightbox e exibir a imagem
function openLightbox(index) {
    currentIndex = index; // Definir o índice da imagem atual
    lightboxImage.src = galleryItems[currentIndex].src;
    lightbox.style.display = "flex"; // Mostrar a lightbox
}

// Função para fechar a lightbox
function closeLightbox() {
    lightbox.style.display = "none"; // Ocultar a lightbox
}

// Função para mudar a imagem
function changeImage(direction) {
    currentIndex = (currentIndex + direction + galleryItems.length) % galleryItems.length; // Navegação circular
    lightboxImage.src = galleryItems[currentIndex].src; // Atualizar a imagem exibida
}

// Exibir a imagem clicada na galeria
galleryItems.forEach((item, index) => {
    item.addEventListener("click", function() {
        openLightbox(index); // Abrir a lightbox com a imagem correspondente
    });
});

// Função para alternar a visibilidade da senha
function togglePassword() {
    var passwordField = document.getElementById("password");
    var type = passwordField.getAttribute("type") === "password" ? "text" : "password";
    passwordField.setAttribute("type", type);
}

// Verifica o estado de login ao carregar a página
document.addEventListener("DOMContentLoaded", function() {
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true'; // Verifica se está logado como string

    // Se o usuário estiver logado
    if (isLoggedIn) {
        // Mostra o ícone do usuário
        const userIcon = document.getElementById("userIcon");
        userIcon.classList.remove('hidden'); // Mostra o ícone do usuário
    } else {
        // Se o usuário não estiver logado, mostra o botão de login
        const loginButton = document.querySelector('.btndestaque'); // Usando a classe btndestaque
        loginButton.classList.remove('hidden'); // Mostra o botão de login
    }
});

// Função para logout
function logout() {
    localStorage.removeItem('isLoggedIn');
    location.reload();  // Atualiza a página para aplicar as mudanças
}
