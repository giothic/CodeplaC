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
    
});