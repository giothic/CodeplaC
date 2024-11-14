function openPopup() {
    const popup = document.getElementById("popup");
    popup.style.display = "block"; 
    setTimeout(() => {
        popup.classList.add("show");
    }, 10);
}

function closePopup() {
    const popup = document.getElementById("popup");
    popup.classList.remove("show"); 
    setTimeout(() => {
        popup.style.display = "none"; 
    }, 500); 
}

function redirectToEvent() {
    window.location.href = "https://www.codeplac.com.br/eventos.html"; 
}

window.onload = function() {
    openPopup();
};
