/*Mini calendar */
const nav = new DayPilot.Navigator("miniCalendar");
nav.selectMode = "Day";
    nav.onTimeRangeSelected = function(args) {
        selectDate();
        selectedDate = args.start;
        calendar.startDate = selectedDate;
        // load events
        calendar.update({
          startDate: calendar.startDate
        });
    };
    // ...
    nav.init();

/*Main Calendar */
const calendar = new DayPilot.Month("calendar", {
    startDate: DayPilot.Date.today(),
});

var selectedDate = calendar.startDate;
var nextSelectedDate = new Date(selectedDate);
nextSelectedDate.setFullYear(nextSelectedDate.getFullYear() + 1);

calendar.onBeforeEventRender = (args) => {
  console.log(calendar);
    selectDate();
    args.data.backColor = args.data.color;

    var events = calendar.events.list;
    // Filter events for the current day
    var dayEvents = events.filter(function(e) {
        return new Date(e.start).toDateString() === new Date(args.data.start).toDateString();
    });
    // Sort events by start time
    dayEvents.sort(function(a, b) {
        return new Date(a.start) - new Date(b.start);
    });
    // Check if the current event is the first event of the day
    var isFirstEvent = dayEvents.length > 0 && dayEvents[0].id === args.data.id;

    // Hide all events except the first one of the day
    if (!isFirstEvent) {
        args.data.cssClass = "hidden-event";
    } else {
      if (dayEvents.length>1) {
        args.data.cssClass = "firstEvent";
        args.data.html = `
          <div class="number">+ `+(dayEvents.length-1)+` more</div>
          <div class="eventTitle">`+args.data.text+`</div>
        `;
      } else {
        args.data.html = `
        <div class="eventTitle">`+args.data.text+`</div>
      `;
      }
    }

    // Find the first day of the event
    var firstDay = new Date(args.data.start);
    firstDay.setHours(0, 0, 0, 0);

    // Find the cell corresponding to the first day of the event
    var cell = calendar.cells.find(function(cell) {
        console.log(cell);
        return cell.start.getTime() === firstDay.getTime();
    });
    
    if (cell) {
        // Find all events in the cell
        var eventsInCell = calendar.events.forCell(cell);

        // If there are more than 1 event in the cell, hide the event
        if (eventsInCell.length > 1) {
            args.data.cssClass = "hidden-event";
        }
    }
    // ...
};

calendar.contextMenu = new DayPilot.Menu({
    items: [
      {text:"Show event ID", onClick: args => {alert("Event value: " + args.source.id());} },
      {text:"Show event text", onClick: args => {alert("Event text: " + args.source.text());} },
      {text:"Show event start", onClick: args => {alert("Event start: " + args.source.start());} },
      {text:"Delete from the calendar", onClick: args => { calendar.events.remove(args.source); } }
]});

calendar.init();
calendar.events.load("/eventRest/loadCalendar?start="+selectedDate.getYear()+"-01-01T00:00:00&end="+nextSelectedDate.getFullYear()+"-01-01T00:00:00");



/*Scheduler */
// var scheduler = new DayPilot.Scheduler("calendar");
// scheduler.onBeforeCellRender =  function (args){
//   args.cell.cssClass = "customCells";
// };

// scheduler.init()


document.addEventListener("DOMContentLoaded", function() {
  document.querySelectorAll(".month_default_header_inner").forEach((cell) => {
      if (cell.textContent.toLowerCase() == "saturday") {
        cell.classList.add("calendarHeaderSaturday");  
      }
  });
  // document.querySelectorAll(".month_default_cell_inner").forEach((c, index)=>{
  //   console.log(c.parentNode.parentNode.querySelectorAll(".month_default_header"))
  //   c.node
  //   // if (c.parentNode.parentNode.querySelectorAll(".month_default_header")[index].querySelector(".month_default_header_inner").textContent.toLowerCase() == "saturday") {
  //   //   c.classList.add("newCalendarcell");
  //   // }
  // });
  var cells = document.querySelectorAll(".month_default_cell_inner");
  for (let index = 5; index < cells.length; index+=6) {
    cells[index].classList.add("bottomcell");
  }
  for (let index = 36; index < cells.length; index++) {
    cells[index].classList.add("rightcell");
  }
});

selectDate();

function selectDate() {
  var defaulCellBox = document.querySelectorAll(".navigator_default_cell_box");
  var defaulCells = document.querySelectorAll(".navigator_default_cell");
  defaulCells.forEach(cell=>{
    cell.addEventListener("click",()=>{
        defaulCellBox.forEach(c=>{
            c.classList.remove("selectedCell");
        });
        cell.querySelector(".navigator_default_cell_box").classList.add("selectedCell");
    })
  })
}

/*COLOUR CARD */

var colorcard = document.getElementById("colors");
fetch("http://localhost:8080/typeRest/all")
.then((response) => {
  if (response.ok) {
  return response.json();
  } else {
  throw new Error("NETWORK RESPONSE ERROR");
  }
})
.then(data => {
    colorcard.innerHTML = '';
    data.forEach(type => {
        const singleColorCard = document.createElement('div');

        singleColorCard.classList.add("singleColor");
        singleColorCard.style.backgroundColor = type.colour;
        singleColorCard.setAttribute("title",type.name);
        colorcard.appendChild(singleColorCard);
    });
})
.catch((error) => console.error("FETCH ERROR:", error));