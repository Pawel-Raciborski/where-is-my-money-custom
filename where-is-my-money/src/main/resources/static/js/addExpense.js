const btn = document.querySelector('.add-expense-btn');
const body = document.querySelector('body');
const dialog = document.querySelector('.add-expense-dialog');
const closeDialogBtn = document.querySelector('.close-expense-btn');

closeDialogBtn.addEventListener('click', () => {
    console.log('Closing dialog');
    dialog.close();
});

btn.addEventListener('click', () => {
    console.log('Opening dialog');
    dialog.showModal();
});

