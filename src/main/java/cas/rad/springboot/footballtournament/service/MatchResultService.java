package cas.rad.springboot.footballtournament.service;

import cas.rad.springboot.footballtournament.dto.MatchResponseDto;
import cas.rad.springboot.footballtournament.dto.MatchResultCreationDto;
import cas.rad.springboot.footballtournament.dto.MatchResultResponseDto;
import cas.rad.springboot.footballtournament.entity.Match;
import cas.rad.springboot.footballtournament.entity.MatchResult;
import cas.rad.springboot.footballtournament.repository.MatchRepository;
import cas.rad.springboot.footballtournament.repository.MatchResultRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchResultService {
    private final MatchResultRepository matchResultRepository;
    private final MatchRepository matchRepository;


    public MatchResultResponseDto create(MatchResultCreationDto dto) {
        // Retrieve the match based on the ID provided in the DTO
        Match match = matchRepository.findById(dto.getMatchId())
                .orElseThrow(() -> new EntityNotFoundException("Match not found"));

        // Create a new MatchResult entity based on the DTO
        MatchResult matchResult = dto.toEntity(match);
        matchResult = matchResultRepository.save(matchResult);

        // Save the MatchResult entity
        return MatchResultResponseDto.fromEntity(matchResult);
    }


    //get One match result
    public Optional<MatchResultResponseDto> getOne(Long id){
        return matchResultRepository.findById(id).map(MatchResultResponseDto::fromEntity);
    }

    //Get all Match result
    public List<MatchResultResponseDto> getAll(){
        return  matchResultRepository.findAll().stream().map(MatchResultResponseDto::fromEntity).toList();
    }
}
