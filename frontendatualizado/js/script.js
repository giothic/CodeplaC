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

// Senha ver, não ver pá
function togglePassword() {
    var passwordField = document.getElementById("password");
    var type = passwordField.getAttribute("type") === "password" ? "text" : "password";
    passwordField.setAttribute("type", type);
}


document.addEventListener("DOMContentLoaded", function() {
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    
    if (isLoggedIn) {
        // Remove a seção de login
        const loginSection = document.getElementById("loginSection"); // Suponha que sua seção de login tenha esse ID
        if (loginSection) {
            loginSection.style.display = 'none'; // Oculta a seção de login
        }
        
        // Adiciona o ícone do usuário
        const userIcon = document.createElement("div");
        userIcon.innerHTML = '<i class="fa fa-user-circle" aria-hidden="true"></i>'; // Altere para o ícone que você quiser
        userIcon.className = "user-icon"; // Adicione uma classe para estilização
        document.querySelector("nav").appendChild(userIcon); // Adicione onde desejar, aqui adiciona ao nav
        
        // Você pode também adicionar um evento de clique para o ícone do usuário
        userIcon.addEventListener("click", function() {
            // Redireciona para a página de perfil ou exibe um menu
            window.location.href = "perfil.html"; // Alterar para a sua página de perfil
        });
    }
});

function logout() {
    localStorage.removeItem('isLoggedIn');
    location.reload();  // Atualiza a página para aplicar as mudanças
}
