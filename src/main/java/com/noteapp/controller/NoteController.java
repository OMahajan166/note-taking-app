package com.noteapp.controller;

import com.noteapp.entity.Note;
import com.noteapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import com.noteapp.entity.User;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    // Home Page
    @GetMapping("/dashboard")
    public String dashboard(Model model,
                            HttpSession session) {

        User user = (User)
                session.getAttribute("loggedInUser");

        // If Not Logged In

        if(user == null) {

            return "redirect:/login";

        }

        model.addAttribute("notes",
                noteService.getUserNotes(user));

        model.addAttribute("note",
                new Note());

        return "index";
    }
    
    
    // Search Notes
    @GetMapping("/search")
    public String searchNotes(@RequestParam String keyword,
                              Model model,
                              HttpSession session) {

        User user = (User)
                session.getAttribute("loggedInUser");

        model.addAttribute("notes",
                noteService.searchUserNotes(user,
                        keyword));

        model.addAttribute("note",
                new Note());

        return "index";
    }
    
    // Edit Note
    @GetMapping("/edit/{id}")
    public String editNote(@PathVariable Long id,
                           Model model) {

        Note note = noteService.getNoteById(id);

        model.addAttribute("note", note);

        model.addAttribute("notes",
                noteService.getAllNotes());

        return "index";
    }

    //save
    @PostMapping("/save")
    public String saveNote(@ModelAttribute Note note,
                           HttpSession session) {

        User user = (User)
                session.getAttribute("loggedInUser");

        note.setUser(user);

        noteService.saveNote(note);

        return "redirect:/dashboard";
    }

    // Delete Note
    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {

        noteService.deleteNote(id);

        return "redirect:/dashboard";
    }
}