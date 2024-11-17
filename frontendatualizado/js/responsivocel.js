const menuIcon = document.querySelector('.menu-icon');
const closeIcon = document.querySelector('.close-icon');
const menu = document.querySelector('.ul');

// Ao clicar no ícone de abrir o menu
menuIcon.addEventListener('click', function() {
  // Mostra o menu e oculta o ícone de abrir
  menu.classList.add('open'); 
  closeIcon.style.display = 'block'; 
  menuIcon.style.display = 'none';  
});

// Ao clicar no ícone de fechar o menu
closeIcon.addEventListener('click', function() {
  // Esconde o menu e o ícone de fechar
  menu.classList.remove('open'); 
  closeIcon.style.display = 'none'; 
  menuIcon.style.display = 'block'; 
});
