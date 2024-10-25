package mate.academy.rickandmorty.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    @Override
    public Character getRandomCharacter() {
        long count = characterRepository.count();
        if(count == 0) {
            throw new EntityNotFoundException("No character found");
        }

        long randomNumber = ThreadLocalRandom.current().nextLong(count);
        return characterRepository.findById(randomNumber)
                .orElseThrow(() -> new EntityNotFoundException("No character id: "
                        + randomNumber + " found"));
    }
    @Override
    public List<Character> findCharacterByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name);
    }
}
