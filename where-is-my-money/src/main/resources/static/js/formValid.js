import {messages} from "./messages.js";

const form = document.querySelector('.create-group-form');
const groupNameInput = document.getElementById('group-name');
const groupNameDiv = groupNameInput.parentElement;
const groupOwnerNameInput = document.getElementById('owner-name');
const groupOwnerNameDiv = groupOwnerNameInput.parentElement;
const groupNameError = createParagraph(messages.GROUP_ERROR_MESSAGE, messages.CSS_ERROR_CLASS_NAME);
const groupOwnerNameError = createParagraph(messages.GROUP_OWNER_ERROR_MESSAGE, messages.CSS_ERROR_CLASS_NAME);

function createParagraph(text, className = '') {
    const p = document.createElement('p');

    if (text) {
        p.textContent = text;
    }

    if (className) {
        p.className = className;
    }
    return p;

}

form.addEventListener('submit', function (e) {
    const groupName = groupNameInput.value.trim();
    const ownerName = groupOwnerNameInput.value.trim();

    let isValidForm = true;
    if (ownerName === '') {
        appendErrorMessage(ownerName, groupOwnerNameDiv, groupOwnerNameError);
        isValidForm = false;
    }

    if (groupName === '') {
        appendErrorMessage(groupName, groupNameDiv, groupNameError);
        isValidForm = false;
    }

    if (!isValidForm) {
        e.preventDefault();
    }

    return isValidForm;
});

function appendErrorMessage(text, div, paragraph) {
    if (text === '') {
        if (!div.contains(paragraph)) {
            div.appendChild(paragraph);
        }
    } else {
        if (div.contains(paragraph)) {
            div.removeChild(paragraph);
        }
    }
}

groupNameInput.addEventListener('keyup', function (_) {
    const groupName = groupNameInput.value.trim();

    appendErrorMessage(groupName, groupNameDiv, groupNameError);
});

groupOwnerNameInput.addEventListener('keyup', function (_) {
    const ownerName = groupOwnerNameInput.value.trim();

    appendErrorMessage(ownerName, groupOwnerNameDiv, groupOwnerNameError);
});