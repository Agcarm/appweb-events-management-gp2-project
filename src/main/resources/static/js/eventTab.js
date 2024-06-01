//For smooth transition of page
document.addEventListener('DOMContentLoaded', (event) => {
    document.body.style.opacity = '1';
  });
  
  window.addEventListener('beforeunload', (event) => {
    document.body.style.opacity = '0';
  });
  

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
        
        //from list.js
        const activeTableId = document.querySelector(".tabTable.active").getAttribute('id');
        switch (activeTableId) {
            case "eventRest":
                selectedRow = null;
                displayPages(window.currentPageEvent,elmtperpage); 
                break;
            case "taskRest":
                selectedRow = null;
                displayPages(window.currentPageTask,elmtperpage); 
                break; 
            case "api/venues":
                selectedRow = null;
                displayPages(window.currentPageVenue,elmtperpage); 
                break;       
            default:
                break;
        }
    })
});


/*Aside parameters */
