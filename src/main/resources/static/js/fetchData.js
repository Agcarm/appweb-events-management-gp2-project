function venues() {
    fetch("http://localhost:8080/eventRest/all")
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
    })
    .catch((error) => console.error("FETCH ERROR:", error));
}