// const { data } = require("jquery");

// Get the table and initialize the selected row
var checkboxes = document.querySelectorAll('input[type="radio"]');
var activeTable = document.querySelector('.tabTable.active');
var rows = activeTable.querySelectorAll('tr');
var selectedRow = activeTable.querySelector('.selected');
var rowName;
var description = document.querySelector('.info');

// Highlight the clicked row
fnselect(selectedRow);
checkboxes.forEach(element => {
    element.addEventListener("change", (e) => {
        rows.forEach(row => {
            row.classList.remove('selected');
        });
        e.target.parentNode.parentNode.classList.add('selected');
        selectedRow = e.target.parentNode.parentNode;
        fnselect(selectedRow);
    })
});

// Function to retrieve the first cell value of the selected row
function fnselect(row) {
    let cells = row.querySelectorAll('td');
    rowName = cells[1].textContent;
    fetch("http://localhost:8080/eventRest/"+rowName)
    .then((response) => {
        if (response.ok) {
        return response.json();
        } else {
        throw new Error("NETWORK RESPONSE ERROR");
        }
    })
    .then(data => {
        // console.log(data);
        description.querySelector('.eventTitle').textContent = data.name;
        description.querySelector('.eventDescription').textContent = data.description;
        description.querySelector('.eventImage').innerHTML = '<img src="/manager-images/'+data.imageUrl+'" alt="'+data.imageUrl+'"  height="250px">'
    })
    .catch((error) => console.error("FETCH ERROR:", error));
}


//Pagination
var total;
var current = 1;

function displayEvents(pageNo, TotalperPage){
    fetch("http://localhost:8080/task/paged/"+pageNo+"/"+TotalperPage)
    .then((response)=>{
        if (response.ok) {
            return response.json();
            } else {
            throw new Error("NETWORK RESPONSE ERROR");
        }
    })
    .then((data)=>{
        console.log(data);
    })
}

document.getElementById('previous').addEventListener("click",()=>{
    displayEvents(0,2);
});