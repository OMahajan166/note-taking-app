package com.noteapp.service;

import com.noteapp.entity.Note;
import com.noteapp.entity.User;
import com.noteapp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // Save Note
    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    // Get All Notes
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Delete Note
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
    
    // Get Note By ID
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }
    
    // Search Notes
    public List<Note> searchNotes(String keyword) {

        return noteRepository.findByTitleContaining(keyword);

    }
    
 // Get User Notes
    public List<Note> getUserNotes(User user) {

        return noteRepository.findByUser(user);

    }

    // Search User Notes
    public List<Note> searchUserNotes(User user,
                                      String keyword) {

        return noteRepository
                .findByUserAndTitleContaining(user, keyword);

    }
    
}