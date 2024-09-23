// Função para abrir o modal e exibir a imagem
function openModal(element) {
    var modal = document.getElementById("myModal");
    var modalImg = document.getElementById("modalImage");
    modal.style.display = "block";
    modalImg.src = element.src;
}

// Função para fechar o modal
function closeModal() {
    var modal = document.getElementById("myModal");
    modal.style.display = "none";
}

const lightbox = document.getElementById("lightbox");
const lightboxImage = document.getElementById("lightbox-img");
const closeBtn = document.querySelector(".close");
const prevBtn = document.querySelector(".prev");
const nextBtn = document.querySelector(".next");
const galleryItems = document.querySelectorAll(".gallery-item img");

let currentIndex = 0; // Índice da imagem atual

// Função para abrir a lightbox e exibir a imagem
function openLightbox(index) {
    currentIndex = index;
    lightboxImage.src = galleryItems[index].src;
    lightbox.style.display = "flex"; // Alterado para "flex" para centralizar
}

// Fechar a lightbox
closeBtn.addEventListener("click", function() {
    lightbox.style.display = "none";
});

// Função para navegar para a imagem anterior
prevBtn.addEventListener("click", function() {
    currentIndex = (currentIndex === 0) ? galleryItems.length - 1 : currentIndex - 1;
    lightboxImage.src = galleryItems[currentIndex].src;
});

// Função para navegar para a próxima imagem
nextBtn.addEventListener("click", function() {
    currentIndex = (currentIndex === galleryItems.length - 1) ? 0 : currentIndex + 1;
    lightboxImage.src = galleryItems[currentIndex].src;
});

// Exibir a imagem clicada na galeria
galleryItems.forEach((item, index) => {
    item.addEventListener("click", function() {
        openLightbox(index);
    });
});

// Fechar a lightbox ao clicar fora da imagem
lightbox.addEventListener("click", function(event) {
    if (event.target === lightbox) {
        lightbox.style.display = "none";
    }
});