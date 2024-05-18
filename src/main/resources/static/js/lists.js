/*PAGINATION */
var elmtperpage = 8;
var totalPagesTask;
window.currentPageTask = 0;

var totalPagesEvent;
window.currentPageEvent = 0;

var totalPagesVenue;
window.currentPageVenue = 0;

displayPages(currentPageEvent,elmtperpage);

document.getElementById('previous').addEventListener("click",()=>{
    const activeTableId = document.querySelector(".tabTable.active").getAttribute('id');
    switch (activeTableId) {
        case "eventRest":
            if (currentPageEvent > 0) {
                currentPageEvent -= 1;
                printPageNumber(currentPageEvent,totalPagesEvent);
                displayPages(currentPageEvent,elmtperpage);
            }
            break;
        case "taskRest":
            if (currentPageTask > 0) {
                currentPageTask -= 1;
                printPageNumber(currentPageTask,totalPagesTask);
                displayPages(currentPageTask,elmtperpage);
            }
            break;
        case "api/venues":
            if (currentPageVenue > 0) {
                currentPageVenue -= 1;
                printPageNumber(currentPageVenue,totalPagesVenue);
                displayPages(currentPageVenue,elmtperpage);
            }
            break;
        default:
            break;
    }
});
document.getElementById('next').addEventListener("click",()=>{
    const activeTableId = document.querySelector(".tabTable.active").getAttribute('id');
    switch (activeTableId) {
        case "eventRest":
            if (currentPageEvent+1 < totalPagesEvent) {
                currentPageEvent += 1;
                printPageNumber(currentPageEvent,totalPagesEvent);
                displayPages(currentPageEvent,elmtperpage);
            }
            break;
        case "taskRest":
            if (currentPageTask+1 < totalPagesTask) {
                currentPageTask += 1;
                printPageNumber(currentPageTask,totalPagesTask);
                displayPages(currentPageTask,elmtperpage);
            }
            break;
        case "api/venues":
            if (currentPageVenue+1 < totalPagesVenue) {
                currentPageVenue += 1;
                printPageNumber(currentPageVenue,totalPagesVenue);
                displayPages(currentPageVenue,elmtperpage);
            }
            break;
        default:
            break;
    }
});

function printPageNumber(current,total) {
    const page = document.querySelector(".pageNumber");
    page.querySelector('#currentPN').value = current + 1;
    page.querySelector('.totalPN').innerHTML = total;
}

/*Retrieve data for the tables */
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
        switch (id) {
            case "eventRest":
                totalPagesEvent = data.totalPages
                printPageNumber(currentPageEvent,totalPagesEvent);
                data.content.forEach((element, index) => {
                    if (index===0) {
                        activeTable.querySelector('tbody').innerHTML += `
                        <tr class="table-primary selected">
                            <td scope="row" class="check">
                                <label for="row-1"></label>
                                <input type="radio" name="row-1" title="selectRow" checked>
                            </td>
                            <td>`+element.name+`</td>
                            <td>`+element.eventType.name+`</td>
                            <td>`+element.eventVenue.name+`</td>
                            <td>`+element.status+`</td>
                            <td>`+element.startDate+`</td>
                            <td>`+element.estimatedAttendees+`</td>
                            <td>`+element.dateModified+`</td>
                        </tr>
                        `
                        selectedRow = activeTable.querySelector('tbody tr');
                        fnselect(selectedRow);
                    } else {
                        activeTable.querySelector('tbody').innerHTML += `
                        <tr class="table-primary">
                            <td scope="row" class="check">
                                <label for="row-1"></label>
                                <input type="radio" name="row-1" title="selectRow">
                            </td>
                            <td>`+element.name+`</td>
                            <td>`+element.eventType.name+`</td>
                            <td>`+element.eventVenue.name+`</td>
                            <td>`+element.status+`</td>
                            <td>`+element.startDate+`</td>
                            <td>`+element.estimatedAttendees+`</td>
                            <td>`+element.dateModified+`</td>
                        </tr>
                        `
                    }
                });
                break;

            case "taskRest":
                totalPagesTask = data.totalPages
                printPageNumber(currentPageTask,totalPagesTask);
                data.content.forEach(element => {
                    if (element.contacts === null) {
                        element.contacts = {name: "none"}
                    }
                    activeTable.querySelector('tbody').innerHTML += `
                        <tr class="table-primary">
                            <td scope="row" class="check">
                                <label for="row-1"></label>
                                <input type="radio" name="row-1" title="selectRow">
                            </td>
                            <td>`+element.title+`</td>
                            <td>`+element.deadline+`</td>
                            <td>`+element.status+`</td>
                            <td>`+element.contacts.name+`</td>
                            <td>`+element.registrationDate+`</td>
                            <td>`+element.description+`</td>
                        </tr>
                    `
                });
                break;
            
            case "api/venues":
                totalPagesVenue = data.totalPages
                printPageNumber(currentPageVenue,totalPagesVenue);
                data.content.forEach(element => {
                    activeTable.querySelector('tbody').innerHTML += `
                        <tr class="table-primary">
                            <td scope="row" class="check">
                                <label for="row-1"></label>
                                <input type="radio" name="row-1" title="selectRow">
                            </td>
                            <td>`+element.name+`</td>
                            <td>`+element.country+`</td>
                            <td>`+element.city+`</td>
                            <td>`+element.address+`</td>
                            <td>`+element.longitude+`</td>
                            <td>`+element.latitude+`</td>
                            <td>edit</td>
                            <td>delete</td>
                        </tr>
                    `
                });
                break;
        }
        SelectCheckbox();
        remakeSelected();
    })
}

/*ROW SELECTION OPERATIONS*/
var selectedRow;
var checkboxes;
var rows;
const description = document.querySelector('.info');

// Function to select a row
function SelectCheckbox() {
    rows = document.querySelectorAll('.tabTable.active tbody tr');
    checkboxes = document.querySelectorAll('.tabTable tbody input[type="radio"]');
    checkboxes.forEach(element => {
        element.addEventListener("click", (e) => {
            rows.forEach(row => {
                row.classList.remove('selected');
            })
            checkboxes.forEach(checkbox => {
                checkbox.checked = false;
            });
            e.target.checked = true;
            selectedRow = e.target.parentNode.parentNode;
            selectedRow.classList.add('selected');
            fnselect(selectedRow);
        });
    });
}

function remakeSelected() {
    var activeRows = document.querySelectorAll('.tabTable.active tbody tr');
    activeRows.forEach( activeRow => {
        selectedRow.classList.remove('selected')
        if (activeRow.isEqualNode(selectedRow)) {
            const activeCheck = activeRow.querySelector('input[type="radio"]');
            activeRow.classList.add('selected');
            activeCheck.checked = true;
        }
    });
}

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
        description.querySelector('.eventTitle').textContent = data.name;
        description.querySelector('.eventDescription').textContent = data.description;
        description.querySelector('.eventImage').innerHTML = '<img src="/manager-images/'+data.imageUrl+'" alt="'+data.imageUrl+'"  height="250px">';
        progression(data.id);
        description.querySelector('.startDate').innerHTML = data.startDate;
        description.querySelector('.endDate').innerHTML = data.endDate;
        description.querySelector('.venue').innerHTML = data.eventVenue.name;
    })
    .catch((error) => console.error("FETCH ERROR:", error));
}

function progression(params) {
    fetch("http://localhost:8080/eventRest/"+ params +"/progression")
    .then((response) => {
        if (response.ok) {
        return response.json();
        } else {
        throw new Error("NETWORK RESPONSE ERROR");
        }
    })

    .then(data => {
        description.querySelector('.progress-bar-fill').style.width=data+ "%";        
    })

}
