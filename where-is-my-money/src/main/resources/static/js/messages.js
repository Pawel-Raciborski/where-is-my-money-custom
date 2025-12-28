export const messages = {
    CSS_ERROR_CLASS_NAME: 'text-danger mt-2',
    GROUP_ERROR_MESSAGE: 'Nazwa grupy nie może być pusta!',
    GROUP_OWNER_ERROR_MESSAGE: 'Nazwa właściciela grupy nie moze być pusta!',
    EMAIL_ERROR_MESSAGE: "Adres email nie może być pusty!",
    COPY_MESSAGE: 'Skopiowano!',
    FIELD_NOT_EMPTY: 'To pole nie może być puste!',
};

export const utils = {
    createParagraph: function (text, className = '') {
        const p = document.createElement('p');

        if (text) {
            p.textContent = text;
        }

        if (className) {
            p.className = className;
        }
        return p;

    },

};