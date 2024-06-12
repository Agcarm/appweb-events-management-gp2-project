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


/*WEEK CALENDAR */
var weekCalendar = new DayPilot.Calendar("weekCalendar");
weekCalendar.viewType = "Week";
weekCalendar.startDate = DayPilot.Date.today().addDays(7);
// ...
weekCalendar.init();


/*DAILY CALENDAR */
var dailyCalendar = new DayPilot.Calendar("dailyCalendar");
dailyCalendar.viewType = "Day";
dailyCalendar.startDate = "2022-03-25";
// ...
dailyCalendar.init();


/*YEARLY */
const yearlyCalendar = new DayPilot.Calendar("yearlyCalendar", {
  viewType: "Resources",
  scale: "Day",
  days: 31,
  heightSpec: "Full",
  columnWidthSpec: "Auto",
  headerLevels: 1,
  headerHeight: 30,
  cellHeight: 30,
  columnMarginLeft: 45,
  columnWidthMin: 200,
  durationBarVisible: false,
  onTimeRangeSelected: async (args) => {
    const modal = await DayPilot.Modal.prompt("Create a new event:", "Event 1");
    const yearlyCalendar = args.control;
    yearlyCalendar.clearSelection();
    if (modal.canceled) { return; }
    yearlyCalendar.events.add({
      start: args.start,
      end: args.end,
      id: DayPilot.guid(),
      text: modal.result,
      resource: args.resource
    });
  },
  onBeforeTimeHeaderRender: args => {
    const day = args.header.date.toString("d");
    args.header.html = day;
  },
  onBeforeCellRender: args => {
    const belongsToCurrentMonth = args.cell.y + 1 === args.cell.start.getDay();

    if (belongsToCurrentMonth) {
      args.cell.properties.areas = [
        { top: 0, left: 2, bottom: 0, width: 40, fontColor: "#666666", text: args.cell.start.toString("d ddd"), verticalAlignment: "center" }
      ];
    }
    else {
      args.cell.properties.backColor = "#dddddd";
    }
  },
});
yearlyCalendar.init();

const app = {
  loadColumns() {
    const columns = [];
    const startDate = DayPilot.Date.today().firstDayOfYear();
    // one column per month
    for (let i = 0; i < 12; i++) {
      const start = startDate.addMonths(i);
      const name = start.toString("MMMM");
      columns.push({name, start});
    }
    yearlyCalendar.update({startDate, columns});
  },
  init() {
    this.loadColumns();
  }
};
app.init();




/*SWITCH CALENDARS */
document.querySelectorAll(".cal").forEach(cal=>{
  cal.style.display = "none";
});
document.querySelector("."+document.querySelector(".calendarButton button.active").textContent).style.display = "block";
document.querySelector(".calendarButton").querySelectorAll("button").forEach(button=>{
  button.addEventListener("click",()=>{
     document.querySelectorAll(".cal").forEach(cal=>{
        cal.style.display = "none";
     });
      console.log("."+button.textContent);

     document.querySelector("."+button.textContent).style.display = "block";
     document.querySelectorAll(".calendarButton button").forEach(butt=>{
        butt.classList.remove("active");
     })
     button.classList.add("active");
  })
})