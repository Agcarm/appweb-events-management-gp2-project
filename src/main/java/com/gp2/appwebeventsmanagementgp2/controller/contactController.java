package com.gp2.appwebeventsmanagementgp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gp2.appwebeventsmanagementgp2.models.contact ;
import com.gp2.appwebeventsmanagementgp2.repositories.contactRepository;
import com.gp2.appwebeventsmanagementgp2.services.BudgetService;
import com.gp2.appwebeventsmanagementgp2.services.ExpenseService;
import com.gp2.appwebeventsmanagementgp2.services.TaskService;
import com.gp2.appwebeventsmanagementgp2.services.contactService;

@Controller
//@RequestMapping("api/contacts")

public class contactController {

    @Autowired
    private contactService contactService;

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contact", new contact(null, null, null));
        model.addAttribute("contactList",contactService.getAllcontacts());
        return "Contact"; // Thymeleaf template name
    }

    @GetMapping("/contacts/{id}/edit")
    public String editContactForm(@PathVariable("id") Long contactId, Model model) {
        contact contact = contactService.getContactById(contactId);
        model.addAttribute("contact", contact);
        return "editCon"; // Assuming you have a view named contact-edit for editing contacts
    }

    @GetMapping("/contactDet1")
    public String showContacts(Model model) {
    	List<contact> contacts = contactService.getAllcontacts();
        model.addAttribute("contact", contacts);
        return "contactDet1"; // Thymeleaf template name
    }

    @PostMapping("/contacts/{id}/update")
    public String updateContact(@PathVariable("id") Long contactId, @ModelAttribute("contact") contact updatedContact) {
        contactService.updateContact(contactId, updatedContact);
        return "redirect:/admin-page";
    }


    @PostMapping("/save-project")
    public String saveContact(@ModelAttribute contact contact, Model model) {
   	 contactService.saveContact(contact);
        model.addAttribute("contactList",contactService.getAllcontacts());
    	 return "Contact";
        }

    @GetMapping("/contacts/{id}/delete")
    public String deleteContact(@PathVariable("id") Long contactId) {
        contactService.deleteContact(contactId);
        return "redirect:/admin-page"; // Redirect to the contact list page
    }
}
