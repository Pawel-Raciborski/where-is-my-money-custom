const btn = document.querySelector('.add-member-btn');
const dialog = document.querySelector('.add-member-dialog');
const closeDialogBtn = document.querySelector('.close-member-btn');

closeDialogBtn.addEventListener('click', () => {
    dialog.close();
});

btn.addEventListener('click', () => {
    dialog.showModal();
});

