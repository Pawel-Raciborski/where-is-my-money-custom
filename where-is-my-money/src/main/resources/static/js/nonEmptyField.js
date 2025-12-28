import {messages, utils} from "./messages.js";

const form = document.querySelector('.form-validated');
const field = document.querySelector(".non-empty-field");
const errorDiv = field.parentElement;
const errorParagraphMessage = utils.createParagraph(messages.FIELD_NOT_EMPTY, messages.CSS_ERROR_CLASS_NAME);

form.addEventListener('submit', function (e) {
    const value = field.value.trim();

    if (value === '') {
        e.preventDefault();
        if (!errorDiv.contains(errorParagraphMessage)) {
            errorDiv.appendChild(errorParagraphMessage);
        }
    }else {
        if (errorDiv.contains(errorParagraphMessage)) {
            errorDiv.removeChild(errorParagraphMessage);
        }
    }
});