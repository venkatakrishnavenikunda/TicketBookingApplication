package com.example.demo.theatre.service.serviceImp;

import com.example.demo.theatre.dto.screenrequestdto.ScreenRequestDto;
import com.example.demo.theatre.dto.screenresponsedto.ScreenResponseDto;
import com.example.demo.theatre.entity.Screen;
import com.example.demo.theatre.entity.Theatre;
import com.example.demo.theatre.repository.ScreenRespository;
import com.example.demo.theatre.repository.TheatreRepository;
import com.example.demo.theatre.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRespository screenRespository;
    private final TheatreRepository theatreRepository;


    //Helper method
    private ScreenResponseDto mapToResponse(Screen screen) {
        return ScreenResponseDto.builder()
                .id(screen.getId())
                .name(screen.getName())
                .capacity(screen.getCapacity())
                .theatreId(screen.getTheatre().getId())
                .active(screen.getActive())
                .createdAt(screen.getCreatedAt())
                .build();
    }


    //Create Screen
    @Override
    public ScreenResponseDto createScreen(ScreenRequestDto dto) {
        Theatre theatre = theatreRepository.findById(dto.getTheatreId()).orElseThrow(() -> new RuntimeException("Theatre not found"));
        if (screenRespository.existsByNameAndTheatre_Id(dto.getName(), dto.getTheatreId())) {
            throw new RuntimeException("Screen with same name already exists in this theatre");
        }
        Screen screen = new Screen();
        screen.setName(dto.getName());
        screen.setCapacity(dto.getCapacity());
        screen.setTheatre(theatre);

        return mapToResponse(screenRespository.save(screen));
    }

    //Get Screen by Theatre
    @Override
    @Transactional(readOnly = true)
    public List<ScreenResponseDto> getScreensByTheatre(Long theatreId) {
        return screenRespository.findByTheatre_IdAndActiveTrue(theatreId).stream()
                .map(this::mapToResponse).toList();
    }

    //Get Screen By id
    @Override
    @Transactional(readOnly = true)
    public ScreenResponseDto getScreenById(Long screenId) {
        return screenRespository.findById(screenId).map(this::mapToResponse).orElseThrow(() -> new RuntimeException("Screen not found"));
    }

    //Deactivate screen
    @Override
    public void deactivateScreen(Long screenId) {
        Screen screen = screenRespository.findById(screenId).orElseThrow(() -> new RuntimeException("Screen not found"));
        screen.setActive(false);
    }

}
