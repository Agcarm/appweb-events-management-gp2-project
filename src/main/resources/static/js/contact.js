const tabLists = document.querySelectorAll('.tab');

getLists();

tabLists.forEach((tab, index) => {
    tab.addEventListener('click', (e) => {
        tabLists.forEach(tab=>{tab.classList.remove('active')})
        tab.classList.add('active');
    
        var line = document.querySelector('.line');
        line.style.width = e.target.offsetWidth + "px";
        line.style.left = e.target.offsetLeft + "px";

        getLists();
    });
    
});

function getLists() {
    var activeTab = document.querySelector(".tab.active");
    document.querySelector(".contactCards").innerHTML = '';

    fetch("http://localhost:8080/contactRest/getByType/"+activeTab.textContent)
    .then((response) => {
        if (response.ok) {
        return response.json();
        } else {
        throw new Error("NETWORK RESPONSE ERROR");
        }
    })
    .then(data => {
        console.log(data);
        data.forEach((element,index) => {
            document.querySelector(".contactCards").innerHTML+=`
                <div class="contactCard" style="--order: `+index+`;">
                    <div class="iconContact"><ion-icon name="person-outline"></ion-icon></div>
                    <p class="contactName primary">`+element.contact.name+`</p>
                    <p class="email secondary">`+element.contact.email+`</p>
                    <div class="type primary">`+element.contactType.name+`</div>
                    <p class="phone secondary">`+element.contact.phoneNumber+`</p>
                    <p class="country secondary">`+element.contact.country+`</p>
                    <div class="actions">
                        <button class="edit" id="displayEditFormBtn" type="button"><ion-icon name="pencil"></ion-icon></button>
                        <a href="" class="assign" title="assign task"><ion-icon name="return-down-forward"></ion-icon></a>
                        <a href="/contacts/`+element.contact.contactId+`/delete" class="delete"><ion-icon name="trash"></ion-icon></a>
                    </div>	
                </div>
            `;
            document.querySelector('#displayEditFormBtn').addEventListener("click",()=>{
                document.querySelector("#overlay").style.display = 'flex';
                console.log("hey");
            })
        });
    })
    .catch((error) => console.error("FETCH ERROR:", error));
    console.log(document.querySelector(".contactCards"));
}

var contactTypes = 0;

function addContactType() {
    const container = document.getElementById('contactTypeContainer');
    const newFieldSet = document.createElement('fieldset');
    newFieldSet.className = 'field';
    newFieldSet.innerHTML = `
        <legend>Contact Type</legend>
        <div class="field">
            <label class="label" for="type">Type</label>
            <div class="control">
                <select id="type" name="type" required>
                <option value=Association">Association</option>
                <option value="Club">Club</option>
                <option value="School">School</option>
                <!-- Add more options as needed -->
            </select>
            </div>
        </div>
        <div class="field">
            <label class="label" for="typeName">Type Name</label>
            <div class="control">
                <input class="input" type="text" name="contactTypes[`+contactTypes+`].typeName" placeholder="Enter group name" required>
            </div>
        </div>
        <div class="field">
            <label class="label" for="role">Role</label>
            <div class="control">
                <input class="input" type="text" name="contactTypes[`+contactTypes+`].role" placeholder="Enter role" required>
            </div>
        </div>
    `;
    container.appendChild(newFieldSet);
    contactTypes++;
}
