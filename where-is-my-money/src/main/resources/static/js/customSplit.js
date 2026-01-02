import {messages, utils} from "./messages.js";

const customSplitBtn = document.querySelector('.custom-split');
const expenseDiv = document.querySelector('.expense-members');
const expenseContainer = document.querySelector('.custom-expense-container');
let selectedMembers = [];
const memberMap = new Map();

function createMemberObject(item) {
    return {
        id: item.querySelector('input').getAttribute('id'),
        fullName: item.querySelector('label').innerText
    };
}

function createMemberSplitItem(member) {
    member = createMemberObject(member);
    const div = document.createElement('div');
    div.className = 'd-flex flex-row align-items-center gap-1 justify-content-between align-items-center mt-2';

    div.insertAdjacentHTML('afterbegin', `<label for="${member.id}" class="form-check-label">${member.fullName}</label>
            <div class="d-flex flex-row align-items-center gap-1 justify-content-end">
            <input type="hidden" name="userId" value="${member.id}"/>
            <input id="${member.id}" data-id="${member.id}" name="price" type="number" step="0.01" value="0.00" class="form-control expense-member-selected expense-split-value w-50"/>
            <span>zł</span>
            </div>
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
        let checkbox = e.target;
        let parentItem = checkbox.parentElement;

        if (checkbox.checked) {
            const memberDiv = createMemberSplitItem(parentItem);
            memberMap.set(checkbox, memberDiv);
            expenseContainer.appendChild(memberDiv);
        } else {
            const memberToRemove = memberMap.get(checkbox);
            if (memberToRemove) {
                expenseContainer.removeChild(memberToRemove);
                memberMap.delete(checkbox);
            }
        }
    }
});
// TODO refactor this code to avoid duplication

customSplitBtn.addEventListener('change', (e) => {
    if (e.target.checked) {
        expenseContainer.className = 'mt-3 d-flex flex-column custom-expense-container bg-light p-2 rounded-2';
    } else {
        expenseContainer.className = 'd-none custom-expense-container';
    }
});