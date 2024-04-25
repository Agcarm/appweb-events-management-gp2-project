/*Table funstions */
const tabLists = document.querySelectorAll('.tab');
const tables = document.querySelectorAll('.tabTable');

tabLists.forEach((tab, index) => {
    tab.addEventListener('click', (e) => {
        tabLists.forEach(tab=>{tab.classList.remove('active')})
        tab.classList.add('active');
    
        var line = document.querySelector('.line');
    line.style.width = e.target.offsetWidth + "px";
    line.style.left = e.target.offsetLeft + "px";

    tables.forEach(content=>{content.classList.remove('active')})
    tables[index].classList.add('active') 
    })
});

