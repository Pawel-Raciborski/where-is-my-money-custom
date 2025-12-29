import {messages, utils} from "./messages.js";

const customSplitBtn = document.querySelector('.custom-split');
const expenseDiv = document.querySelector('.expense-members');
const expenseContainer = document.querySelector('.custom-expense-container');
let selectedMembers = [];

function createMemberObject(item) {
    return {
        id: item.querySelector('input').getAttribute('id'),
        fullName: item.querySelector('label').innerText
    };
}

function createMemberSplitItem(member) {
    member = createMemberObject(member);
    const div = document.createElement('div');

    div.insertAdjacentHTML('afterbegin', `<label for="${member.id}" class="form-check-label">${member.fullName}</label>
        <div class="d-flex flex-row align-items-center gap-1 justify-content-end">
            <input id="${member.id}" type="text" class="form-control expense-member-selected w-25"/>
            <span>zł</span>
        </div>`);


    return div;
}

function createMemberDiv() {
    const div = document.createElement('div');
    div.className = 'mt-3 d-flex flex-column p-3 bg-light rounded-3 expense-members';
    return div;
}

/*
* <div class="mt-3 d-flex flex-column p-3 bg-light rounded-3 expense-members">
                <div class="form-check expense-member" th:each="member : ${groupDetails.members()}">
                    <input th:id="${member.id()}" type="checkbox" class="form-check-input expense-member-checkbox"/>
                    <label th:for="${member.id()}" class="form-check-label" th:text="${member.fullName()}">Brak nazwy</label>
                </div>
            </div>
* */

expenseDiv.addEventListener('change', (e) => {
    if (e.target.classList.contains('expense-member-checkbox')) {
        let isChecked = e.target.checked;
        let item = e.target.parentElement;
        const memberDiv = createMemberSplitItem(item);

        if (isChecked) {
            if (!selectedMembers.includes(item)) {
                expenseContainer.insertAdjacentElement('beforeend', memberDiv);
                selectedMembers.push(item);
            }
        } else {
            if (selectedMembers.includes(item)) {
                selectedMembers = selectedMembers.filter(i => i !== item);
                expenseContainer.removeChild(memberDiv);
            }
        }
    }
});
// TODO refactor this code to avoid duplication

customSplitBtn.addEventListener('change', (e) => {
    if (e.target.checked) {
        const header = utils.createParagraph('Kwoty dla uczestników:', 'mb-2 fw-bold');
        expenseContainer.appendChild(header);
        selectedMembers.forEach(member => {
            expenseContainer.insertAdjacentElement('beforeend', createMemberSplitItem(member));
        });
        expenseContainer.className = 'mt-3 d-flex flex-column custom-expense-container bg-light p-2 rounded-2';
        console.log(expenseContainer);
    } else {
        expenseContainer.replaceChildren();
        expenseContainer.className = 'd-none custom-expense-container';
    }
});