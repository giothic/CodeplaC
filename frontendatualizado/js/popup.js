function openPopup() {
    document.getElementById("popup").style.display = "block";
}

function closePopup() {
    document.getElementById("popup").style.display = "none";
}

function redirectToEvent() {
    window.location.href = ""; 
}

window.onload = function() {
    openPopup();
};
