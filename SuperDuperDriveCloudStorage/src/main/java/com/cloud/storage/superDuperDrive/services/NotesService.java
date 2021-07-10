package com.cloud.storage.superDuperDrive.services;

import com.cloud.storage.superDuperDrive.mapper.NotesMapper;
import com.cloud.storage.superDuperDrive.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private NotesMapper noteMapper;

    @Autowired
    public NotesService(NotesMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(Notes note) {
        return noteMapper.insert(note);
    }

    public int updateNote(Notes note) {
        return noteMapper.update(note);
    }

    public int removeNote(@org.jetbrains.annotations.NotNull Notes note) {
        return noteMapper.remove(note.getNoteId());
    }

    public List<Notes> getNotesByUserId(Integer userId) {
        return noteMapper.getNotesByUsername(userId);
    }
}
