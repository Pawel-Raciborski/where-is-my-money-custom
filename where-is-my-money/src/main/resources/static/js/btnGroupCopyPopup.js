import {messages} from "./messages.js";
const btn = document.querySelector('.btn-group-copy');
const groupLinkInput = document.querySelector('.group-link-input');

function popupP(text, className){
    const p = document.createElement('p');
    p.textContent = text;
    if(className){
        p.className = className;
    }
    return p;
}


btn.addEventListener('click',async function(){
    const popup = popupP(messages.COPY_MESSAGE,"position-absolute top-120 start-50 translate-middle-x py-1 px-2 border border-2 rounded-3 text-color-green");
    const text = groupLinkInput.value;
    try {
        if (text) {
            console.log(text);
            await navigator.clipboard.writeText(text);
        }
        btn.parentElement.append(popup);
        setTimeout(() => {
            popup.style.display = 'none';
            popup.remove();
        }, 3000);
    } catch (e){
        console.error("Something went wrong during copy: ", e);
        popup.remove();
    }
});