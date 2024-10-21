function showEventForm() {
    const eventForm = document.getElementById('event-form');
    eventForm.style.display = eventForm.style.display === 'none' ? 'block' : 'none';
}

function createEvent() {
    const eventDate = document.getElementById('event-date').value;
    const eventDescription = document.getElementById('event-description').value;
    
    if (eventDate && eventDescription) {
        const eventList = document.getElementById('event-list');
        const eventItem = document.createElement('div');
        eventItem.className = 'evento-item';
        
        eventItem.innerHTML = `
            <h4>Em andamento</h4>
            <p>Data: ${eventDate}</p>
            <p>Descrição: ${eventDescription}</p>
            <a href="inscricao.html" class="btn">fechado</a>
        `;
        
        eventList.appendChild(eventItem);
        
        document.getElementById('event-date').value = '';
        document.getElementById('event-description').value = '';
    } else {
        alert('Por favor, preencha todos os campos.');
    }
}

function saveUser(button) {
    const row = button.closest('tr'); 
    const select = row.querySelector('.role-select');
    const statusSpan = row.querySelector('.status');

    if (select.value === 'Admin') {
        statusSpan.classList.remove('member');
        statusSpan.classList.add('admin');
        statusSpan.textContent = 'Admin';
    } else {
        statusSpan.classList.remove('admin');
        statusSpan.classList.add('member');
        statusSpan.textContent = 'Membro';
    }
    
    alert('Alterações salvas com sucesso!');
}


function removeUser(button) {
    const row = button.closest('tr'); 
    row.remove(); 
    alert('Usuário removido com sucesso!');
}

function manageImages() {
    const galleryContainer = document.querySelector('.gallery-container');

    alert('Funcionalidade de gerenciamento de imagens ainda não implementada.');
}

