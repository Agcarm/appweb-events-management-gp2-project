const calendar = new DayPilot.Month("calendar", {
    startDate: "2024-05-22",
    // onBeforeEventRender: args => {
    //     const color = args.data.color;
    //     args.data.backColor = color;
    //     args.data.borderColor = "darker";
    //     args.data.fontColor = "#ffffff";
    //     args.data.areas = [
    //       {
    //         top: 6,
    //         right: 6,
    //         width: 18,
    //         height: 18,
    //         symbol: "../icons/daypilot.svg#minichevron-down-2",
    //         action: "ContextMenu",
    //         backColor: "#ffffff",
    //         fontColor: "#666666",
    //         style: "border: 1px solid #ccc; cursor:pointer; border-radius: 15px;"
    //       }
    //     ];
    //   }
  });

calendar.contextMenu = new DayPilot.Menu({
    items: [
      {text:"Show event ID", onClick: args => {alert("Event value: " + args.source.id());} },
      {text:"Show event text", onClick: args => {alert("Event text: " + args.source.text());} },
      {text:"Show event start", onClick: args => {alert("Event start: " + args.source.start());} },
      {text:"Delete from the calendar", onClick: args => { calendar.events.remove(args.source); } }
]});

calendar.init();
calendar.events.load("/eventRest/loadCalendar");

