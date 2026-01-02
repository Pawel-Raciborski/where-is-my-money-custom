const checkboxes = document.querySelectorAll('.expense-member-checkbox');
const selectAllBtn = document.querySelector('.select-all-btn');
let selected = false;


selectAllBtn.addEventListener('click', () => {
    selected = !selected;

    if (selected) {
        checkboxes.forEach(checkbox => {
            checkbox.checked = true;
        });

        selectAllBtn.textContent = 'Odznacz wszystkich';
        selectAllBtn.className = 'btn btn-outline-danger select-all-btn';
    } else {
        checkboxes.forEach(checkbox => {
            checkbox.checked = false;
        });

        selectAllBtn.textContent = 'Zaznacz wszystkich';
        selectAllBtn.className = "btn btn-outline-primary select-all-btn";
    }

});