package com.gp2.appwebeventsmanagementgp2.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gp2.appwebeventsmanagementgp2.configurations.EnvironmentVariables;
import com.gp2.appwebeventsmanagementgp2.models.event;
import com.gp2.appwebeventsmanagementgp2.services.EventService;
import com.gp2.appwebeventsmanagementgp2.services.TaskService;
import com.gp2.appwebeventsmanagementgp2.services.UserService;
import com.gp2.appwebeventsmanagementgp2.services.venueService;
import com.gp2.appwebeventsmanagementgp2.services.typeService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.Optional;
import java.io.IOException;

@RequestMapping("/events")
@Controller
public class eventController {

    @Autowired
    private EventService eventService;
	@Autowired
    private venueService vService;
    @Autowired
	private UserService userService;
    @Autowired
    typeService tService;
    @Autowired
    private TaskService taskService;


    @GetMapping("/{id}/edit")
    public String editEventForm(@PathVariable("id") Long id, Model model) {
        event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "editEvent"; // Assuming you have a view named contact-edit for editing contacts
    }

    @PostMapping("/create")
    public String createEvent(@ModelAttribute("event") event event, @RequestParam("image") Optional<MultipartFile> file)throws IOException {
        if (file.get().getSize()!=0) {
            event.setImageUrl(file.get().getOriginalFilename());
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(EnvironmentVariables.getEventImages(), file.get().getOriginalFilename());
            fileNames.append(file.get().getOriginalFilename());
            Files.write(fileNameAndPath, file.get().getBytes());
        }
        eventService.saveEvent(event);
        return "redirect:/admin-page";
    }

    @PostMapping("/{id}/update")
    public String updateEvent(@PathVariable("id") Long id, @ModelAttribute("event") event updatedEvent) {
        eventService.updateEvent(id, updatedEvent);
        return "redirect:/admin-page";
    }

    @GetMapping("/{id}/delete")
    public String deleteContact(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return "redirect:/admin-page"; // Redirect to the contact list page
    }

    @PostMapping("/{name}")
    public event GetEventByName(@PathVariable String name, Model model){
        model.addAttribute("event",eventService.findByName(name));
        return eventService.findByName(name);
    }

    @GetMapping("/view")
    public String getEventView(Model model) {
        model.addAttribute("eventList", eventService.findAll());
		model.addAttribute("venueList", vService.listAll());
		model.addAttribute("taskList", taskService.findAll());
		model.addAttribute("user",EnvironmentVariables.getUser());
        model.addAttribute("typeList", tService.listAll());
        return "eventTab";
    }
    
    @GetMapping("/CEF")
    public String displayEventForm() {
        return "createEventForm";
    }
    

    @GetMapping("/calendar")
    public String ScheduleTab() {
        return "schedule";
    }

    // @PostMapping("/upload") 
    // public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
    //     StringBuilder fileNames = new StringBuilder();
    //     Path fileNameAndPath = Paths.get(EnvironmentVariables.getEventImages(), file.getOriginalFilename());
    //     fileNames.append(file.getOriginalFilename());
    //     Files.write(fileNameAndPath, file.getBytes());
    //     model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
    //     return "ImageUpload";
    // }
}
