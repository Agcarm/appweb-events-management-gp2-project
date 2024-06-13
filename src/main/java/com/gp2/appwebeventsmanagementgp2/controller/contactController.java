package com.gp2.appwebeventsmanagementgp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gp2.appwebeventsmanagementgp2.configurations.EnvironmentVariables;
import com.gp2.appwebeventsmanagementgp2.models.contact ;
import com.gp2.appwebeventsmanagementgp2.models.contactForm;
import com.gp2.appwebeventsmanagementgp2.models.contactTypeForm;
import com.gp2.appwebeventsmanagementgp2.models.contactTypeMapping;
import com.gp2.appwebeventsmanagementgp2.services.contactService;
import com.gp2.appwebeventsmanagementgp2.services.contactTypeService;
import com.gp2.appwebeventsmanagementgp2.services.contactTypeMappingService;
import com.gp2.appwebeventsmanagementgp2.models.contactType;

@Controller
@RequestMapping("/contacts")
public class contactController {

    @Autowired
    private contactService contactService;

    @Autowired
    private contactTypeMappingService contactTypeMappingService;
    
    @Autowired
    private contactTypeService contactTypeService;

    @GetMapping({"/", ""})
    public String showContactS(Model model) {
        model.addAttribute("user",EnvironmentVariables.getUser());
        model.addAttribute("contactList",contactService.getAllcontacts());
        return "Contact";
    }

    @GetMapping("/{id}/edit")
    public String editContactForm(@PathVariable("id") Long contactId, Model model) {
        contact contact = contactService.getContactById(contactId);
        model.addAttribute("contact", contact);
        return "editCon";
    }

    @PostMapping("/{id}/update")
    public String updateContact(@PathVariable("id") Long contactId, @ModelAttribute("contact") contact updatedContact, Model model) {
        contactService.updateContact(contactId, updatedContact);
        model.addAttribute("contactList",contactService.getAllcontacts());
        return "Contact";
    }


    @PostMapping("/save-project")
    public String saveContact(@ModelAttribute contactForm contactForm, Model model) {
        contact contact = new contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());

        // Save the contact (e.g., to the database)
        contactService.saveContact(contact);

        for (contactTypeForm contactTypeForm : contactForm.getContactTypes()) {
            contactType contactType = new contactType();
            contactType.setName(contactTypeForm.getTypeName());
            contactType.setType(contactTypeForm.getType());
            contactTypeService.saveContactType(contactType);

            contactTypeMapping mapping = new contactTypeMapping();
            mapping.setContact(contact);
            mapping.setContactType(contactType);
            mapping.setRole(contactTypeForm.getRole());
            contactTypeMappingService.saveContactTypeMapping(mapping);
        }
   	    contactService.saveContact(contact);
        model.addAttribute("contactList",contactService.getAllcontacts());
        model.addAttribute("user",EnvironmentVariables.getUser());
    	return "Contact";
    }

    @GetMapping("/{id}/delete")
    public String deleteContact(@PathVariable("id") Long contactId, Model model) {
        System.out.println(contactId);
        contactService.deleteContact(contactId);
        model.addAttribute("contactList",contactService.getAllcontacts());
        model.addAttribute("user",EnvironmentVariables.getUser());
        return "Contact"; // Redirect to the contact list page
    }
}
