package com.noteapp.repository;

import com.noteapp.entity.Note;
import com.noteapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Search Notes

    List<Note> findByTitleContaining(String keyword);

    // User Specific Notes

    List<Note> findByUser(User user);

    // User + Search

    List<Note> findByUserAndTitleContaining(User user,
                                            String keyword);
}