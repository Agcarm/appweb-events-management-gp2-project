const calendar = new DayPilot.Month("calendar", {
    startDate: "2024-04-22"
  });
calendar.init();
calendar.events.load("/eventRest/loadCalendar?start=2024-04-22T13:24:00&end=2024-04-27T13:24:00");

