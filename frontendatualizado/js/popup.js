document.addEventListener('DOMContentLoaded', function() {
    const eventModal = new bootstrap.Modal(document.getElementById('eventModal'));
    const openModalBtn = document.getElementById('openModalBtn');
    const modalFooterBtn = document.getElementById('modalFooterBtn');
    
    const lastModalTime = localStorage.getItem('lastModalTime');
    const currentTime = new Date().getTime(); 
    
    const showModalAndButton = () => {
        eventModal.show(); 
    };

    if (!lastModalTime || (currentTime - lastModalTime > 3 * 60 * 60 * 1000)) {
        showModalAndButton();
        localStorage.setItem('lastModalTime', currentTime); 
    }

    modalFooterBtn.style.display = 'none';  

    eventModal._element.addEventListener('hidden.bs.modal', function () {
        modalFooterBtn.style.display = 'block';  
    });

    openModalBtn.addEventListener('click', function() {
        eventModal.show();
        modalFooterBtn.style.display = 'none';  
    });
});
