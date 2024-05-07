/*Mini calendar */
const nav = new DayPilot.Navigator("miniCalendar");
nav.selectMode = "month";
    nav.onTimeRangeSelected = function(args) {
        calendar.startDate = args.start;
        // load events
        calendar.update();
    };
    // ...
    nav.init();

/*Main Calendar */
const calendar = new DayPilot.Month("calendar", {
    startDate: DayPilot.Date.today(),
  });
calendar.onBeforeEventRender = (args) => {
    console.log(args.data.color)
    args.data.backColor = args.data.color;
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
calendar.events.load("/eventRest/loadCalendar");

/*Scheduler */
var scheduler = new DayPilot.Scheduler("calendar");
scheduler.onBeforeCellRender =  function (args){
  args.cell.cssClass = "customCells";
};

scheduler.init()