const customSplitBtn = document.querySelector('.custom-split');
const splitExpenseParagraph = document.querySelector('.split-expense-sum');
const expensePriceInput = document.getElementById('expense-price');
const leftSum = splitExpenseParagraph.querySelector('.left-sum');
const rightSum = splitExpenseParagraph.querySelector('.right-sum');
const customExpenseContainer = document.querySelector('.custom-expense-container');
const saveExpenseBtn = document.querySelector('.save-expense-btn');
let isChecked = false;


function checkIsEqualTotalSum(sum) {
    let number = parseFloat(expensePriceInput.value) || 0;
    const sumAsNumber = parseFloat(sum) || 0;

    const difference = Math.abs(number - sumAsNumber);
    const isScaleEqual = difference < 0.01;

    splitExpenseParagraph.classList.remove('text-danger', 'text-success');
    if (!isScaleEqual) {
        splitExpenseParagraph.classList.add('text-danger');
        saveExpenseBtn.disabled = true;
    } else {
        splitExpenseParagraph.classList.add('text-success');
        saveExpenseBtn.disabled = false;
    }
}

function calculateTotal(){
    const memberExpenses = customExpenseContainer.querySelectorAll('.expense-split-value');
    let sum = 0;
    memberExpenses.forEach(inputElem => {
        sum += parseFloat(inputElem.value) || 0;
    });
    leftSum.textContent = sum.toFixed(2);
    checkIsEqualTotalSum(leftSum.textContent);
}

customExpenseContainer.addEventListener('input', (e) => {
    if (e.target.classList.contains('expense-split-value')) {
        calculateTotal();
    }
});

expensePriceInput.addEventListener('input', () => {
    rightSum.textContent = expensePriceInput.value;
    if(isChecked){
        calculateTotal();
    }
});


customSplitBtn.addEventListener('change', (e) => {
    if (e.target.checked) {
        isChecked = true;
        rightSum.textContent = expensePriceInput.value;
    }else {
        isChecked = false;
    }
});