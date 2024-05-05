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


/*PAGINATION */
/*For tasks */
var total;
var current = 1;

var totalPagesEvent;
var currentPageEvent = 1;

var totalPagesVenue;
var currentPageVenue = 1;

displayPages(0,1);

document.getElementById('previous').addEventListener("click",()=>{
    displayPages(0,1);
    printPageNumber();
});
document.getElementById('next').addEventListener("click",()=>{
    displayPages(currentPageEvent,1);
    printPageNumber();
});

function printPageNumber() {
    const page = document.querySelector(".pageNumber");
    page.innerHTML = current + "/" + total;
}

function displayPages(pageNo, TotalperPage) {
    const activeTable = document.querySelector(".tabTable.active");
    const id = activeTable.getAttribute('id');
    activeTable.querySelector('tbody').innerHTML = '';
    fetch("http://localhost:8080/"+id+"/paged/"+pageNo+"/"+TotalperPage)
    .then((response)=>{
        if (response.ok) {
            return response.json();
            } else {
            throw new Error("NETWORK RESPONSE ERROR");
        }
    })
    .then((data)=>{
        total = data.totalPages
        printPageNumber();
        switch (id) {
            case "eventRest":
                data.content.forEach(element => {
                    console.log(activeTable.querySelector('tbody'));
                    activeTable.querySelector('tbody').innerHTML += `
                        <tr class="table-primary">
                            <td scope="row" class="check">
                                <label for="row-1"></label>
                                <input type="radio" name="row-1" id="row-1" title="selectRow">
                            </td>
                            <td>`+element.name+`</td>
                            <td>`+element.eventVenue.name+`</td>
                            <td>`+element.status+`</td>
                            <td>`+element.startDate+`</td>
                            <td>`+element.name+`</td>
                            <td>`+element.dateModified+`</td>
                        </tr>
                    `
                });
                break;

            case "task":
                data.content.forEach(element => {
                    console.log(activeTable.querySelector('tbody'));
                    activeTable.querySelector('tbody').innerHTML += `
                        <tr class="table-primary">
                            <td scope="row" class="check">
                                <label for="row-1"></label>
                                <input type="radio" name="row-1" id="row-1" title="selectRow">
                            </td>
                            <td>`+element.title+`</td>
                            <td>`+element.status+`</td>
                            <td>`+element.status+`</td>
                            <td>`+element.deadline+`</td>
                        </tr>
                    `
                });
                break;
            
            case "api/venues":
                data.content.forEach(element => {
                    console.log(activeTable.querySelector('tbody'));
                    activeTable.querySelector('tbody').innerHTML += `
                        <tr class="table-primary">
                            <td scope="row" class="check">
                                <label for="row-1"></label>
                                <input type="radio" name="row-1" id="row-1" title="selectRow">
                            </td>
                            <td>`+element.name+`</td>
                            <td>`+element.country+`</td>
                            <td>`+element.city+`</td>
                            <td>`+element.address+`</td>
                            <td>`+element.longitude+`</td>
                            <td>`+element.latitude+`</td>
                        </tr>
                    `
                });
                break;
        }
    })
}