@GetMapping
public List<Note> getAllNotes() {
        return noteRepository.findAll();
        }
